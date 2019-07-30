package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);

    @Override
    public void addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            logger.info("Add to db: " + product);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Add product to db: ", e);
        }
    }

    @Override
    public void deleteProduct(Product value) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (getProductById(value.getProductID()).isPresent()) {
                session.delete(getProductById(value.getProductID()).get());
            }
            transaction.commit();
            logger.info("Deleted from db: " + getProductById(value.getProductID()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Delete product from db: ", e);
        }
    }

    @Override
    public void editProduct(Product value) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update("product", value);
            transaction.commit();
            logger.info("Edit: " + value.getProductID());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Edit product: ", e);
        }
    }

    @Override
    public List<Product> getAll() {
        //TODO: getAll()
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Product", Product.class).list();
//        } catch (Exception e) {
//            logger.error("Get all products from db: ", e);
//        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, id);
            if (Objects.isNull(product)) {
                return Optional.empty();
            } else {
                return Optional.of(product);
            }
        } catch (Exception e) {
            logger.error("Get product by ID: ", e);
        }
        return Optional.empty();
    }
}