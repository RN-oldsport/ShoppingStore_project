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
    private final CustomerServices customerServices;

    public AuthenticationService(IUserRepository userRepo, CustomerServices customerServices) {
        this.userRepo = userRepo;
        this.customerServices = customerServices;
    }

    // =====================
    // Login
    // =====================
    public OperationResult<User> login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            return new OperationResult<>(null, false, "Username or password cannot be empty!");

        List<User> users = userRepo.loadUsers();
        String hashedPassword = PasswordHasher.hasher(password);

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(hashedPassword)) {
                return new OperationResult<>(user, true, "Login successful!");
            }
        }

        return new OperationResult<>(null, false, "Invalid username or password!");
    }

    // =====================
    // Signup (Customer)
    // =====================
    public OperationResult<Customer> signup(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            return new OperationResult<>(null, false, "Username or password cannot be empty!");

        List<User> users = userRepo.loadUsers();

        // Check if username exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return new OperationResult<>(null, false, "Username already exists!");
            }
        }

        // Create new customer
        Customer newCustomer = new Customer(username, PasswordHasher.hasher(password));

        // Generate new ID
        int newId = generateNewUserId(users);
        newCustomer.setId(newId);

        // Add to users list and save ONCE
        users.add(newCustomer);
        boolean saved = userRepo.saveUsers(users);

        if (!saved) {
            return new OperationResult<>(null, false, "Failed to save user to storage!");
        }

        return new OperationResult<>(newCustomer, true, "Signup successful!");
    }


    // =====================
    // Helper: generate unique numeric ID
    // =====================
    private int generateNewUserId(List<User> users) {
        int maxId = 0;
        for (User u : users) {
            if (u.getId() > maxId) maxId = u.getId();
        }
        return maxId + 1;
    }
}
