package cinema.dao;

import cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);

    Optional<MovieSession> get(Long id);

    MovieSession update(MovieSession movieSession);

    void delete(Long id);
}
