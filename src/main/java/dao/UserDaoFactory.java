package dao;

import util.DBHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class UserDaoFactory {

    public static UserDAO getUserDao () {
        try {
            Properties properties = new Properties();
            InputStream in = UserDaoFactory.class.getClassLoader().getResourceAsStream("TypeDB.properties");
            properties.load(in);

            switch (properties.getProperty("type")) {
                case "JDBC":
                    return DBHelper.getUserJdbsDAO();
                case "Hibernate":
                    return new UserHibernateDAO(DBHelper.getSessionFactory());
                default:
                    System.out.println("Все плохо");
                    throw new Exception();
            }
        } catch (FileNotFoundException e) {
            System.out.println("********************Файл не найден***********************");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
