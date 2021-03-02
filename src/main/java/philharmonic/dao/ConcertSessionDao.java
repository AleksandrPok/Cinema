package philharmonic.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import philharmonic.model.ConcertSession;

public interface ConcertSessionDao {
    List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date);

    ConcertSession add(ConcertSession concertSession);

    Optional<ConcertSession> get(Long id);

    ConcertSession update(ConcertSession concertSession);

    void delete(Long id);
}
