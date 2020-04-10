package dao;

import entities.User;
import util.DBHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO getUserDao () {
        try {
            Properties properties = new Properties();
            String path = "C:\\Users\\pc\\Desktop\\JM\\PreProject_CRUD\\PreProject_CRUD\\src\\main\\resources\\TypeDB.properties";
            FileInputStream in = new FileInputStream(path);
            properties.load(in);

            switch (properties.getProperty("type")) {
                case "JDBS":
                    return DBHelper.getUserJdbsDAO();
                case "Hibernate":
                    return new UserHibernateDAO(DBHelper.getSessionFactory());
                default:
                    System.out.println("Все плохо");
                    throw new Exception();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
