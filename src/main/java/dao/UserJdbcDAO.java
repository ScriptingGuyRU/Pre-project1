package dao;

import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO{

    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }


    public List<User> getAllUsers() {
        try {
            Statement statement = connection.createStatement();
            List<User> listUsers = new ArrayList<>();
            String sql = "SELECT * FROM pre_project_crud.userstable";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                listUsers.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }
            connection.commit();
            statement.close();
            return listUsers;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user)  {
        try{
            String sql ="INSERT INTO pre_project_crud.userstable SET name = ?, password = ?, role = ?";  //Уникальность User
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getRole());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS pre_project_crud.UsersTable (id bigint auto_increment, name varchar(50), password varchar(50), role varchar(50), primary key(id))";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(Long id) {
        try {
            validateUser(getUserById(id));

            String sql = "DELETE FROM pre_project_crud.userstable WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            ps.executeUpdate();
            connection.commit();
            ps.close();
            return true;
        } catch (NullPointerException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean editUser(User user) {
        try {
            String sql = "UPDATE pre_project_crud.userstable SET name = ?, password = ?, role = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getPassword());
            ps.setLong(4,user.getId());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateUser(User user) {
        try {
            String sql = "SELECT id FROM pre_project_crud.userstable WHERE name = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                preparedStatement.close();
                return true;
            }

            connection.commit();
            preparedStatement.close();
            return false;
        }catch (SQLException e) {
            return false;
        }
    }

    public User getUserById(Long id) {
        try {

            String sql = "SELECT * from pre_project_crud.userstable where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                User user = new User(id, name, password, role);
                connection.commit();
                ps.close();
                return user;
            }

            connection.commit();
            ps.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        try{
            String sql = "Select * from pre_project_crud.userstable where name = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String role = resultSet.getString("role");
                User user = new User(id,name,password,role);
                connection.commit();
                ps.close();
                return user;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                return null;
            } catch (SQLException ex) {
                return null;
            }
        }
        return null;
    }
}
