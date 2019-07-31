package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.Objects;
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
            logger.error("Add user:", e);
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
            logger.error("Delete user: ", e);
        }
    }

    @Override
    public void editUser(User value) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update("user", value);
            transaction.commit();
            logger.info("Updated: " + value.getUserID());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Update user: ", e);
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
            User user = session.byNaturalId(User.class)
                    .using("email", email)
                    .getReference();
            if (Objects.isNull(user)) {
                return Optional.empty();
            } else {
                return Optional.of(user);
            }
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.load(User.class, id);
            if (Objects.isNull(user)) {
                return Optional.empty();
            } else {
                return Optional.of(user);
            }
        }
    }
}
