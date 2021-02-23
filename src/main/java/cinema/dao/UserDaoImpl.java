package cinema.dao;

import cinema.exception.DataProcessException;
import cinema.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't add User:" + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM User u "
                    + "JOIN FETCH u.roles WHERE u.email = :email", User.class)
                    .setParameter("email", email).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessException("Can't find user by email: " + email, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM User u "
                    + "JOIN FETCH u.roles WHERE u.id = :id", User.class)
                    .setParameter("id", id).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessException("Can't get user with id: " + id, e);
        }
    }
}
