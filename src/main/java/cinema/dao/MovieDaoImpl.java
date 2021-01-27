package cinema.dao;

import cinema.exception.DataProcessException;
import cinema.lib.DaoImpl;
import cinema.model.Movie;
import cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@DaoImpl
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction == null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't save movie to database " + movie, e);
        } finally {
            if (session == null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getAll = session.createQuery("FROM Movie", Movie.class);
            return getAll.getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Can't get all movies from database: ", e);
        }
    }
}
