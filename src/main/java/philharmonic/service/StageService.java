package philharmonic.service;

import java.util.List;
import philharmonic.model.Stage;

public interface StageService {
    Stage add(Stage stage);

    List<Stage> getAll();

    Stage get(Long id);
}
