package services;

import dao.UserDAO;
import entities.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService {

    public UserService() {
    }

    public static List<User> getAllUsers() {
        try {
            return getUserDAO().getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addUser(User user) {
        return getUserDAO().addUser(user);
    }

    public static void createTable() {
        getUserDAO().createTable();
    }

    public static boolean delete(User user) {
        return getUserDAO().delete(user);
    }

    public static boolean editUserById(Long id, String newName, String newPassword) {
        return getUserDAO().editUserById(id, newName, newPassword);
    }

    public static User getUserById(Long id) {
        return getUserDAO().getUserById(id);
    }






    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=787898").       //password
                    append("&serverTimezone=UTC");   //setup server time)

            Connection connection = DriverManager.getConnection(url.toString());
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static UserDAO getUserDAO () {
        return new UserDAO(getMysqlConnection());
    }
}
