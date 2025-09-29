package be.ucll.backend2.controller;

import be.ucll.backend2.model.Movie;
import be.ucll.backend2.service.MovieService;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getMovies(@RequestParam Optional<@Min(1888) Integer> startYear,
                                 @RequestParam Optional<@Min(1888) Integer> endYear) {
        if (startYear.isPresent() || endYear.isPresent()) {
            return movieService.getMoviesBetween(startYear, endYear);
        } else {
            return movieService.getAllMovies();
        }
    }
}
