package philharmonic.service;

import java.util.List;
import org.springframework.stereotype.Service;
import philharmonic.dao.ConcertDao;
import philharmonic.model.Concert;

@Service
public class ConcertServiceImpl implements ConcertService {
    private final ConcertDao concertDao;

    public ConcertServiceImpl(ConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    @Override
    public Concert add(Concert concert) {
        return concertDao.add(concert);
    }

    @Override
    public List<Concert> getAll() {
        return concertDao.getAll();
    }

    @Override
    public Concert get(Long id) {
        return concertDao.get(id).get();
    }
}
