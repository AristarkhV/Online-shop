package dao.impl;

import dao.RoleDao;
import model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Objects;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {

    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);

    @Override
    public void addRole(Role role) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            logger.info("Added to db: " + role);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't add role:" + role, e);
        }
    }

    @Override
    public Optional<Role> getRoleByName(String value) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Role WHERE name = :value");
            query.setParameter("value", value);
            Role role = (Role) query.uniqueResult();
            return Optional.of(role);
        } catch (Exception e) {
            logger.error("Can't find role by name  = " + value, e);
        }
        return Optional.empty();
    }
}
