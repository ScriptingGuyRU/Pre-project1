package dao;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {
    @Override
    public List<User> getAllUsers() throws SQLException {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public void createTable() {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean editUser(User user, String newName, String newPassword) {
        return false;
    }

    @Override
    public boolean validateUser(User user) {
        return false;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }
}
