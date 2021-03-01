package philharmonic.dao;

import java.util.List;
import java.util.Optional;
import philharmonic.model.Concert;

public interface ConcertDao {
    Concert add(Concert concert);

    List<Concert> getAll();

    Optional<Concert> get(Long id);
}
