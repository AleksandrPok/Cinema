package philharmonic.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import philharmonic.model.ConcertSession;
import philharmonic.model.dto.ConcertSessionRequestDto;
import philharmonic.model.dto.ConcertSessionResponseDto;
import philharmonic.service.ConcertSessionService;
import philharmonic.service.mappers.ConcertSessionMapper;

@RestController
@RequestMapping("/concert-sessions")
public class ConcertSessionController {
    private final ConcertSessionService concertSessionService;
    private final ConcertSessionMapper concertSessionMapper;

    @Autowired
    public ConcertSessionController(ConcertSessionService concertSessionService,
                                    ConcertSessionMapper concertSessionMapper) {
        this.concertSessionService = concertSessionService;
        this.concertSessionMapper = concertSessionMapper;
    }

    @GetMapping("/available")
    public List<ConcertSessionResponseDto> getAvailableSessions(@RequestParam Long concertId,
                            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return concertSessionService.findAvailableSessions(concertId, date).stream()
                .map(concertSessionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody @Valid ConcertSessionRequestDto concertSessionRequestDto) {
        ConcertSession concertSession = concertSessionMapper.mapToEntity(concertSessionRequestDto);
        concertSessionService.add(concertSession);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                        @RequestBody @Valid ConcertSessionRequestDto concertSessionRequestDto) {
        ConcertSession concertSession = concertSessionMapper.mapToEntity(concertSessionRequestDto);
        concertSession.setId(id);
        concertSessionService.update(concertSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        concertSessionService.delete(id);
    }
}
