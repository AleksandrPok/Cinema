package philharmonic.service;

import java.time.LocalDate;
import java.util.List;
import philharmonic.model.ConcertSession;

public interface ConcertSessionService {
    List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date);

    ConcertSession add(ConcertSession concertSession);

    ConcertSession update(ConcertSession concertSession);

    void delete(Long id);

    ConcertSession get(Long id);
}
