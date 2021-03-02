package philharmonic.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import philharmonic.dao.ConcertSessionDao;
import philharmonic.model.ConcertSession;

@Service
public class ConcertSessionServiceImpl implements ConcertSessionService {
    private final ConcertSessionDao concertSessionDao;

    public ConcertSessionServiceImpl(ConcertSessionDao concertSessionDao) {
        this.concertSessionDao = concertSessionDao;
    }

    @Override
    public List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date) {
        return concertSessionDao.findAvailableSessions(concertId, date);
    }

    @Override
    public ConcertSession add(ConcertSession concertSession) {
        return concertSessionDao.add(concertSession);
    }

    @Override
    public ConcertSession update(ConcertSession concertSession) {
        return concertSessionDao.update(concertSession);
    }

    @Override
    public void delete(Long id) {
        concertSessionDao.delete(id);
    }

    @Override
    public ConcertSession get(Long id) {
        return concertSessionDao.get(id).get();
    }
}
