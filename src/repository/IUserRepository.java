package repository;

import model.User;

import java.util.List;

public interface IUserRepository {

    List<User> loadUsers();

    boolean saveUsers(List<User> users);

}
