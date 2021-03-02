package philharmonic.service;

import java.util.List;
import org.springframework.stereotype.Service;
import philharmonic.dao.StageDao;
import philharmonic.model.Stage;

@Service
public class StageServiceImpl implements StageService {
    private final StageDao stageDao;

    public StageServiceImpl(StageDao stageDao) {
        this.stageDao = stageDao;
    }

    @Override
    public Stage add(Stage stage) {
        return stageDao.add(stage);
    }

    @Override
    public List<Stage> getAll() {
        return stageDao.getAll();
    }

    @Override
    public Stage get(Long id) {
        return stageDao.get(id).get();
    }
}
