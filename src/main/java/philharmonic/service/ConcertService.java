package philharmonic.service;

import java.util.List;
import philharmonic.model.Concert;

public interface ConcertService {
    Concert add(Concert concert);

    List<Concert> getAll();

    Concert get(Long id);
}
