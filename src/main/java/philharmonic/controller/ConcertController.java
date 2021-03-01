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
import philharmonic.model.Concert;
import philharmonic.model.dto.ConcertRequestDto;
import philharmonic.model.dto.ConcertResponseDto;
import philharmonic.service.ConcertService;
import philharmonic.service.mappers.ConcertMapper;

@RestController
@RequestMapping("/concerts")
public class ConcertController {
    private final ConcertService concertService;
    private final ConcertMapper concertMapper;

    @Autowired
    public ConcertController(ConcertService concertService, ConcertMapper concertMapper) {
        this.concertService = concertService;
        this.concertMapper = concertMapper;
    }

    @GetMapping
    public List<ConcertResponseDto> getAll() {
        return concertService.getAll().stream()
                .map(concertMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody @Valid ConcertRequestDto concertRequestDto) {
        Concert concert = concertMapper.mapToEntity(concertRequestDto);
        concertService.add(concert);
    }
}
