package dao;

import entities.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        System.out.println("Конструктор =" + sessionFactory.isClosed());
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        System.out.println("GetAll 1) "+session.isConnected());
        Transaction transaction = session.beginTransaction();
        System.out.println("GetAll 2) "+session.isConnected());
        List<User> carsList = session.createQuery("FROM User").list();
        System.out.println("GetAll 3) "+session.isConnected());
        transaction.commit();
        System.out.println("GetAll 4) "+session.isConnected());
        session.close();
        System.out.println("GetAll 5) "+session.isConnected());
        return carsList;
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
