package philharmonic.dao;

import java.util.List;
import java.util.Optional;
import philharmonic.model.Stage;

public interface StageDao {
    Stage add(Stage stage);

    List<Stage> getAll();

    Optional<Stage> get(Long id);
}
