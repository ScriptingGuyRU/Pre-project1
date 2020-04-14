package dao;

import entities.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    boolean addUser(User user);

    boolean delete(Long id);

    boolean editUser(User user);

    boolean validateUser(User user);

    User getUserById(Long id);

    User getUserByNameAndPassword(String name, String password);
}
