package dao.impl;

import dao.CodeDao;
import model.Code;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Objects;
import java.util.Optional;

public class CodeDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addCode(Code value) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(value);
            transaction.commit();
            logger.info("Added to db: " + value.toString());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't add code to db: ", e);
        }
    }

    @Override
    public Optional<Code> getCode(String email) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Code WHERE email = :email");
            query.setParameter("email", email);
            Code code = (Code) query.uniqueResult();
            return Optional.of(code);
        } catch (Exception e) {
            logger.error("Can't get code : ", e);
        }
        return Optional.empty();
    }
}
