package dao.impl;

import dao.CodeDao;
import model.Code;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            logger.error("Add code to db: ", e);
        }
    }

    @Override
    public Optional<Code> getCode(String email) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Code code = session.byNaturalId(Code.class)
                    .using("email", email)
                    .getReference();
            if (Objects.isNull(code)) {
                return Optional.empty();
            } else {
                return Optional.of(code);
            }
        } catch (Exception e) {
            logger.error("Get code : ", e);
        }
        return Optional.empty();
    }
}
