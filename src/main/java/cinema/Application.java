package cinema;

import cinema.lib.Injector;
import cinema.model.Movie;
import cinema.service.MovieService;

public class Application {
    private static Injector injector = Injector.getInstance("cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("The Adventures of Buckaroo Banzai Across the 8th Dimension");
        movieService.add(movie);
        for (Movie m : movieService.getAll()) {
            System.out.println(m);
        }
    }
}
