package philharmonic.service.mappers;

import org.springframework.stereotype.Component;
import philharmonic.model.Concert;
import philharmonic.model.dto.ConcertRequestDto;
import philharmonic.model.dto.ConcertResponseDto;

@Component
public class ConcertMapper {
    public Concert mapToEntity(ConcertRequestDto concertRequestDto) {
        Concert concert = new Concert();
        concert.setTitle(concertRequestDto.getTitle());
        concert.setDescription(concertRequestDto.getDescription());
        return concert;
    }

    public ConcertResponseDto mapToDto(Concert concert) {
        ConcertResponseDto concertResponseDto = new ConcertResponseDto();
        concertResponseDto.setId(concert.getId());
        concertResponseDto.setTitle(concert.getTitle());
        concertResponseDto.setDescription(concert.getDescription());
        return concertResponseDto;
    }
}
