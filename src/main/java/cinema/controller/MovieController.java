package cinema.controller;

import cinema.model.Movie;
import cinema.model.dto.MovieRequestDto;
import cinema.model.dto.MovieResponseDto;
import cinema.service.MovieService;
import cinema.service.mappers.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(movieMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody @Valid MovieRequestDto movieRequestDto) {
        Movie movie = movieMapper.mapToEntity(movieRequestDto);
        movieService.add(movie);
    }
}
