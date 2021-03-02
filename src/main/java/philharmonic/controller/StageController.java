package philharmonic.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import philharmonic.model.Stage;
import philharmonic.model.dto.StageRequestDto;
import philharmonic.model.dto.StageResponseDto;
import philharmonic.service.StageService;
import philharmonic.service.mappers.StageMapper;

@RestController
@RequestMapping("/stages")
public class StageController {
    private final StageService stageService;
    private final StageMapper stageMapper;

    @Autowired
    public StageController(StageService stageService,
                           StageMapper stageMapper) {
        this.stageService = stageService;
        this.stageMapper = stageMapper;
    }

    @GetMapping
    public List<StageResponseDto> getAll() {
        return stageService.getAll().stream()
                .map(stageMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody @Valid StageRequestDto stageRequestDto) {
        Stage stage = stageMapper.mapToEntity(stageRequestDto);
        stageService.add(stage);
    }
}
