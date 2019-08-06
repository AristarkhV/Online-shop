package dao.impl;

import dao.OrderDao;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addOrder(Order order) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            logger.info("Added to db: " + order.toString());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't add order to db: ", e);
        }
    }

    @Override
    public Optional<Order> getUserOrder(User value) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Order WHERE user = :user");
            query.setParameter("user", value);
            Order order = (Order) query.uniqueResult();
            return Optional.of(order);
        } catch (Exception e) {
            logger.error("Can't get user order : ", e);
        }
        return Optional.empty();
    }
}
