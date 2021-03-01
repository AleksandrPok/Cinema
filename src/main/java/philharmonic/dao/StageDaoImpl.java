package philharmonic.dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import philharmonic.exception.DataProcessException;
import philharmonic.model.Stage;

@Repository
public class StageDaoImpl implements StageDao {
    private final SessionFactory sessionFactory;

    public StageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Stage add(Stage stage) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(stage);
            transaction.commit();
            return stage;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Can't add stage: " + stage, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Stage> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Stage", Stage.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Can't get stages", e);
        }
    }

    @Override
    public Optional<Stage> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Stage.class, id));
        } catch (Exception e) {
            throw new DataProcessException("Can't get stage with id: " + id, e);
        }
    }
}
