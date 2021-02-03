package cinema.service;

import cinema.dao.CinemaHallDao;
import cinema.lib.Inject;
import cinema.lib.ServiceImpl;
import cinema.model.CinemaHall;
import java.util.List;

@ServiceImpl
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
