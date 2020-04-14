package dao;

import entities.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> carsList = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return carsList;
    }

    @Override
    public boolean addUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    @Override
    public boolean delete(Long id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "Delete from User WHERE id = :id";
            session.createQuery(hql).setParameter("id",id).executeUpdate();
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "Update User SET name = :name, password = :password, role = :role WHERE id = :id";
            session.createQuery(hql).
                    setParameter("name", user.getName()).
                    setParameter("password", user.getPassword()).
                    setParameter("role", user.getRole()).
                    setParameter("id", user.getId()).
                    executeUpdate();
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validateUser(User user) {
        try {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM User where id = :id, name = :name, password = :password";
        Query query = session.createQuery(hql).
                setParameter("id", user.getId()).
                setParameter("name", user.getName()).
                setParameter("password", user.getPassword());

        if(query.list().isEmpty()) { //get
            transaction.commit();
            session.close();
            return true;
        }

            transaction.commit();
            session.close();
            return false;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE id = :id")
                .setParameter("id", id).uniqueResult();

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = (User) session.createQuery("FROM User WHERE name = :name and password = :password").
                    setParameter("name", name).
                    setParameter("password", password).
                    uniqueResult();

            transaction.commit();
            session.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
