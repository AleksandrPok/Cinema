package philharmonic.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import philharmonic.exception.DataProcessException;
import philharmonic.model.Role;
import philharmonic.model.RoleName;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't add role " + role.getRoleName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Role WHERE roleName = :roleName", Role.class)
                    .setParameter("roleName", RoleName.valueOf(roleName)).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessException("Can't get role: " + roleName, e);
        }
    }
}
