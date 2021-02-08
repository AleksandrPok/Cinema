package cinema.dao;

import cinema.exception.DataProcessException;
import cinema.lib.DaoImpl;
import cinema.model.Order;
import cinema.model.User;
import cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

@DaoImpl
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't add order: " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "LEFT JOIN FETCH o.tickets WHERE o.user = :user", Order.class)
                    .setParameter("user", user).getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Can't get user's orders history: " + user, e);
        }
    }
}
