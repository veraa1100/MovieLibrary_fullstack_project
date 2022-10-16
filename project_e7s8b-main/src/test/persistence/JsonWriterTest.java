package persistence;

import model.MovieList;
import model.Movie;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            MovieList movieList = new MovieList("My movielist");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMovieList() {
        try {
            MovieList movieList = new MovieList("My movielist");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieList.json");
            writer.open();
            writer.write(movieList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList.json");
            movieList = reader.read();
            assertEquals("My movielist", movieList.getName());
            assertEquals(0, movieList.numMovies());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMovieList() {
        try {
            MovieList movieList1 = new MovieList("My movielist");
            movieList1.addMovie(new Movie("Joker", 7,"..."));
            movieList1.addMovie(new Movie("Batman", 9,"..."));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieList.json");
            writer.open();
            writer.write(movieList1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovieList.json");
            movieList1 = reader.read();
            assertEquals("My movielist", movieList1.getName());
            List<Movie> movieList = movieList1.getMovies();
            assertEquals(2, movieList.size());
            checkMovie("Joker", 7,"...", movieList.get(0));
            checkMovie("Batman", 9,"...", movieList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}