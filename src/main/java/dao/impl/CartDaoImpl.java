package dao.impl;

import dao.CartDao;
import model.Cart;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Objects;
import java.util.Optional;

public class CartDaoImpl implements CartDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addCart(Cart value) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(value);
            transaction.commit();
            logger.info("Added to db: " + value);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Add cart to db: ", e);
        }
    }

    @Override
    public Optional<Cart> getCart(User value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cart cart = session.byNaturalId(Cart.class)
                    .using("idUser", value.getUserID())
                    .getReference();
            if (Objects.isNull(cart)) {
                return Optional.empty();
            } else {
                return Optional.of(cart);
            }
        } catch (Exception e) {
            logger.error("Get cart : ", e);
        }
        return Optional.empty();
    }

    @Override
    public void addProductToCart(Cart cart, Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            cart.getProducts().add(product);
            session.update(cart);
            transaction.commit();
            logger.info("Add to db: " + product + cart);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Add product to cart: " + e);
        }
    }

    @Override
    public void cleanCart(Cart value) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(value);
            transaction.commit();
            logger.info("Cleaned: " + value);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Clean cart: ", e);
        }
    }
}
