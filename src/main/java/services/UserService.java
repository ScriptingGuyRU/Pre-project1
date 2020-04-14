package services;

import dao.UserDAO;
import dao.UserDaoFactory;
import entities.User;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    private static UserService userService;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(new UserDaoFactory().getUserDao());
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    public boolean delete(Long id) {
        return userDAO.delete(id);
    }

    public boolean editUser(User user) {
        return userDAO.editUser(user);
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public User getUserByNameAndPassword(String name, String password) {
        return userDAO.getUserByNameAndPassword(name,password);
    }


}
