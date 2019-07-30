package dao.impl;

import dao.OrderDao;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Objects;
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
            logger.error("Add order to db: ", e);
        }
    }

    @Override
    public Optional<Order> getUserOrder(User value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Order order = session.byNaturalId(Order.class)
                    .using("idUser", value.getUserID())
                    .getReference();
            if (Objects.isNull(order)) {
                return Optional.empty();
            } else {
                return Optional.of(order);
            }
        } catch (Exception e) {
            logger.error("Get user order : ", e);
        }
        return Optional.empty();
    }
}
