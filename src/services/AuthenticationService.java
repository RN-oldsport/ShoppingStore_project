package services;

import common.OperationResult;
import common.PasswordHasher;
import model.Customer;
import model.User;
import model.Role;
import repository.IUserRepository;

import java.util.List;

public class AuthenticationService {

    private final IUserRepository userRepo;

    public AuthenticationService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }


    public OperationResult<User> login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            return new OperationResult<>(null, false, "Username or password cannot be empty!");

        String hashedPassword = PasswordHasher.hasher(password);

        // Finding user
        List<User> users = userRepo.loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(hashedPassword)) {
                return new OperationResult<>(user, true, "Login successful!");
            }
        }

        return new OperationResult<>(null, false, "Invalid username or password!");
    }


    public OperationResult<Customer> signup(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            return new OperationResult<>(false, "Username or password cannot be empty!");

        List<User> users = userRepo.loadUsers();

        // Check if username exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return new OperationResult<>(false, "Username already exists!");
            }
        }

        String hashedPassword = PasswordHasher.hasher(password);

        Customer newCustomer = new Customer(username, hashedPassword);

        int newId = generateNewUserId(users);
        newCustomer.setId(newId);

        // Add to users list and save
        users.add(newCustomer);
        boolean saved = userRepo.saveUsers(users);

        if (!saved) {
            return new OperationResult<>(false, "Failed to save user to storage!");
        }

        return new OperationResult<>(newCustomer, true, "Signup successful!");
    }


    // generate ID
    private int generateNewUserId(List<User> users) {
        int maxId = 0;
        for (User u : users) {
            if (u.getId() > maxId) maxId = u.getId();
        }
        return maxId + 1;
    }
}
