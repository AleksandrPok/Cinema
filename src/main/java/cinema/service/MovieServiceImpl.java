package cinema.service;

import cinema.dao.MovieDao;
import cinema.lib.Inject;
import cinema.lib.ServiceImpl;
import cinema.model.Movie;
import java.util.List;

@ServiceImpl
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
