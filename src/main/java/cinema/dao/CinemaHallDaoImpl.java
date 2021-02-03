package cinema.dao;

import cinema.exception.DataProcessException;
import cinema.lib.DaoImpl;
import cinema.model.CinemaHall;
import cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

@DaoImpl
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't add cinema hall: " + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM CinemaHall", CinemaHall.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Can't get cinema halls", e);
        }
    }
}
