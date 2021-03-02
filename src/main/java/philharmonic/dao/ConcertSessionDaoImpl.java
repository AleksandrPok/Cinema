package philharmonic.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import philharmonic.exception.DataProcessException;
import philharmonic.model.ConcertSession;

@Repository
public class ConcertSessionDaoImpl implements ConcertSessionDao {
    private final SessionFactory sessionFactory;

    public ConcertSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<ConcertSession> getByDate = session.createQuery(
                    "SELECT cs FROM ConcertSession cs "
                    + "LEFT JOIN FETCH cs.concert LEFT JOIN FETCH cs.stage "
                    + "WHERE cs.concert.id = :concert_id "
                    + "AND date_format(cs.showTime, '%Y-%m-%d') = :date", ConcertSession.class);
            getByDate.setParameter("concert_id", concertId);
            getByDate.setParameter("date", date.toString());
            return getByDate.getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Can't get sessions on " + date.toString(), e);
        }
    }

    @Override
    public ConcertSession add(ConcertSession concertSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(concertSession);
            transaction.commit();
            return concertSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't add concert session: " + concertSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<ConcertSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(ConcertSession.class, id));
        } catch (Exception e) {
            throw new DataProcessException("Can't get concert session with id: " + id, e);
        }
    }

    @Override
    public ConcertSession update(ConcertSession concertSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(concertSession);
            transaction.commit();
            return concertSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't update concert session: " + concertSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            ConcertSession deletableSession = session.load(ConcertSession.class, id);
            session.delete(deletableSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't delete concert session: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
