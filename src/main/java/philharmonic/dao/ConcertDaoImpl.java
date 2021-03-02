package philharmonic.dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import philharmonic.exception.DataProcessException;
import philharmonic.model.Concert;

@Repository
public class ConcertDaoImpl implements ConcertDao {
    private final SessionFactory sessionFactory;

    public ConcertDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Concert add(Concert concert) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(concert);
            transaction.commit();
            return concert;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't save concert to database " + concert, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Concert> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Concert> getAll = session.createQuery("FROM Concert", Concert.class);
            return getAll.getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Can't get all concerts from database: ", e);
        }
    }

    @Override
    public Optional<Concert> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Concert.class, id));
        } catch (Exception e) {
            throw new DataProcessException("Can't get concert with id: " + id, e);
        }
    }
}
