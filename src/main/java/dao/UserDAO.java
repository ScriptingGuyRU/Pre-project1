package dao;

import entities.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    public boolean addUser(User user);

    public void createTable();

    public boolean delete(Long id);

    public boolean editUser(User user, String newName, String newPassword, String newRole);

    public boolean validateUser(User user);

    public User getUserById(Long id);

    public User getUserByNameAndPassword(String name, String password);
}
