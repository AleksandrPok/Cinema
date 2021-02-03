package cinema.dao;

import cinema.exception.DataProcessException;
import cinema.lib.DaoImpl;
import cinema.model.MovieSession;
import cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@DaoImpl
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> getByDate = session.createQuery("SELECT ms FROM MovieSession ms "
                    + "LEFT JOIN FETCH ms.movie LEFT JOIN FETCH ms.cinemaHall "
                    + "WHERE ms.movie.id = :movie_id "
                    + "AND date_format(ms.showTime, '%Y-%m-%d') = :date", MovieSession.class);
            getByDate.setParameter("movie_id", movieId);
            getByDate.setParameter("date", date.toString());
            return getByDate.getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Can't get sessions on " + date.toString(), e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't add movie session: " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
