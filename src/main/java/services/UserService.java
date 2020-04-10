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

    public void createTable() {
        userDAO.createTable();
    }

    public boolean delete(Long id) {
        return userDAO.delete(id);
    }

    public boolean editUser(User user, String newName, String newPassword) {
        return userDAO.editUser(user, newName, newPassword);
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }






//    private static Connection getMysqlConnection() {
//        try {
//            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
//
//            StringBuilder url = new StringBuilder();
//
//            url.
//                    append("jdbc:mysql://").        //db type
//                    append("localhost:").           //host name
//                    append("3306/").                //port
//                    append("db_example?").          //db name
//                    append("user=root&").          //login
//                    append("password=787898").       //password
//                    append("&serverTimezone=UTC");   //setup server time)
//
//            Connection connection = DriverManager.getConnection(url.toString());
//            connection.setAutoCommit(false);
//            return connection;
//        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new IllegalStateException();
//        }
//    }
//
//    public static UserJdbcDAO getUserJdbsDAO () {
//        return new UserJdbcDAO(getMysqlConnection());
//    }
}
