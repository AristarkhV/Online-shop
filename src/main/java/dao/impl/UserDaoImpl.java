package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addUser(User user) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.info("Added to db: " + user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't add user:", e);
        }
    }

    @Override
    public void deleteUser(User value) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (getUserById(value.getUserID()).isPresent()) {
                session.delete(getUserById(value.getUserID()).get());
            }
            transaction.commit();
            logger.info("Deleted from db: " + getUserById(value.getUserID()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't delete user: ", e);
        }
    }

    @Override
    public void editUser(User value) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(value);
            transaction.commit();
            logger.info("Updated: " + value.getUserID());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't edit user: ", e);
        }
    }

    @Override
    public List<User> getAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE email = :email");
            query.setParameter("email", email);
            User user = (User) query.uniqueResult();
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("Can't find user by email  = " + email, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("Can't find user: " + userId, e);
        }
        return Optional.empty();
    }
}
