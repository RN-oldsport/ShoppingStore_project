package services;

import common.OperationResult;
import model.Customer;
import model.User;
import repository.IUserRepository;

import java.util.List;

public class CustomerServices {

    private final IUserRepository userRepository;

    public CustomerServices(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ==========================
    // Get Customer by ID
    // ==========================
    public Customer getCustomerById(int id) {
        List<User> users = userRepository.loadUsers();
        for (User u : users) {
            if (u instanceof Customer && u.getId() == id) {
                return (Customer) u;
            }
        }
        return null;
    }

    // ==========================
    // Update Customer (save changes to JSON)
    // ==========================
    public OperationResult<Void> updateCustomer(Customer customer) {
        List<User> users = userRepository.loadUsers();
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u instanceof Customer && u.getId() == customer.getId()) {
                users.set(i, customer);
                found = true;
                break;
            }
        }

        if (!found) {
            return new OperationResult<>(false, "Customer not found");
        }

        boolean saved = userRepository.saveUsers(users);
        if (!saved) {
            return new OperationResult<>(false, "Failed to save customer data");
        }

        return new OperationResult<>(true, "Customer updated successfully");
    }

    // ==========================
    // Increase Customer Balance
    // ==========================
    public OperationResult<Void> increaseBalance(int customerId, double amount) {
        Customer customer = getCustomerById(customerId);

        if (customer == null)
            return new OperationResult<>(false, "Customer not found");

        if (amount <= 0)
            return new OperationResult<>(false, "Invalid amount");

        customer.increaseBalance(amount);
        return updateCustomer(customer);
    }

    // ==========================
    // Decrease Customer Balance
    // ==========================
    public OperationResult<Void> decreaseBalance(int customerId, double amount) {
        Customer customer = getCustomerById(customerId);

        if (customer == null)
            return new OperationResult<>(false, "Customer not found");

        if (amount <= 0)
            return new OperationResult<>(false, "Invalid amount");

        if (customer.getBalance() < amount)
            return new OperationResult<>(false, "Insufficient balance");

        customer.setBalance(customer.getBalance() - amount);
        return updateCustomer(customer);
    }

}
