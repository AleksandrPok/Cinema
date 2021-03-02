package philharmonic.service.mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import philharmonic.model.ConcertSession;
import philharmonic.model.dto.ConcertSessionRequestDto;
import philharmonic.model.dto.ConcertSessionResponseDto;
import philharmonic.service.ConcertService;
import philharmonic.service.StageService;

@Component
public class ConcertSessionMapper {
    private final ConcertService concertService;
    private final StageService stageService;

    @Autowired
    public ConcertSessionMapper(ConcertService concertService, StageService stageService) {
        this.concertService = concertService;
        this.stageService = stageService;
    }

    public ConcertSession mapToEntity(ConcertSessionRequestDto concertSessionRequestDto) {
        ConcertSession concertSession = new ConcertSession();
        concertSession.setConcert(concertService.get(concertSessionRequestDto.getConcertId()));
        LocalDateTime localDateTime = LocalDateTime.parse(concertSessionRequestDto.getShowTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        concertSession.setShowTime(localDateTime);
        concertSession.setStage(stageService.get(concertSessionRequestDto.getStageId()));
        return concertSession;
    }

    public ConcertSessionResponseDto mapToDto(ConcertSession concertSession) {
        ConcertSessionResponseDto concertSessionResponseDto = new ConcertSessionResponseDto();
        concertSessionResponseDto.setId(concertSession.getId());
        concertSessionResponseDto.setConcertId(concertSession.getConcert().getId());
        concertSessionResponseDto.setStageId(concertSession.getStage().getId());
        concertSessionResponseDto.setShowTime(concertSession.getShowTime().toString());
        return concertSessionResponseDto;
    }
}
