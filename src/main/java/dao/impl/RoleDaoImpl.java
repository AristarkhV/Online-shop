package dao.impl;

import dao.RoleDao;
import model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            logger.error("Add role:", e);
        }
    }

    @Override
    public Optional<Role> getRoleByName(String value) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Role role = session.byNaturalId(Role.class)
                    .using("name", value)
                    .getReference();
            if (Objects.isNull(role)) {
                return Optional.empty();
            } else {
                return Optional.of(role);
            }
        }
    }
}
