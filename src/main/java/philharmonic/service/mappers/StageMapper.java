package philharmonic.service.mappers;

import org.springframework.stereotype.Component;
import philharmonic.model.Stage;
import philharmonic.model.dto.StageRequestDto;
import philharmonic.model.dto.StageResponseDto;

@Component
public class StageMapper {
    public Stage mapToEntity(StageRequestDto stageRequestDto) {
        Stage stage = new Stage();
        stage.setCapacity(stageRequestDto.getCapacity());
        stage.setDescription(stageRequestDto.getDescription());
        return stage;
    }

    public StageResponseDto mapToDto(Stage stage) {
        StageResponseDto stageResponseDto = new StageResponseDto();
        stageResponseDto.setId(stage.getId());
        stageResponseDto.setCapacity(stageResponseDto.getCapacity());
        stageResponseDto.setDescription(stageResponseDto.getDescription());
        return stageResponseDto;
    }
}
