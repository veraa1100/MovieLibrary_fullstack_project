package persistence;

import model.Movie;
import model.MovieList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(String name, int rating, String review, Movie movie) {
        assertEquals(name, movie.getName());
        assertEquals(rating, movie.getRating());
        assertEquals(review, movie.getReview());
    }
}
