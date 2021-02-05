package cinema;

import cinema.exception.AuthenticationException;
import cinema.lib.Injector;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.Order;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.security.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import java.time.LocalDateTime;

public class Application {
    private static Injector injector = Injector.getInstance("cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("The Adventures of Buckaroo Banzai Across the 8th Dimension");
        movieService.add(movie);

        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        LocalDateTime showTime = LocalDateTime.of(2021, 2, 5, 17, 30);
        movieSession.setShowTime(showTime);
        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService.findAvailableSessions(movie.getId(),
                showTime.toLocalDate()));

        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        User firstUser = authenticationService.register("first@gmail.com", "firstPassword");
        User secondUser = authenticationService.register("second@gmail.com", "secondPassword");
        try {
            System.out.println(authenticationService.login("first@gmail.com", "firstPassword"));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Incorrect email or password", e);
        }
        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, firstUser);
        shoppingCartService.addSession(movieSession, secondUser);
        System.out.println(shoppingCartService.getByUser(firstUser));
        System.out.println(shoppingCartService.getByUser(secondUser));

        ShoppingCart shoppingCart = shoppingCartService.getByUser(firstUser);
        System.out.println(shoppingCart);

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        Order order = orderService.completeOrder(shoppingCart);
        System.out.println(order);
        System.out.println(orderService.getOrdersHistory(firstUser));
    }
}
