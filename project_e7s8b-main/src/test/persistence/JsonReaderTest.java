package persistence;


import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieList movieList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMovieList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList.json");
        try {
            MovieList movieList = reader.read();
            assertEquals("My movielist", movieList.getName());
            assertEquals(0, movieList.numMovies());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMovieList() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralMovieList.json");
        try {
            MovieList movieList = reader.read();
            assertEquals("My movielist", movieList.getName());
            List<Movie> thingies = movieList.getMovies();
            assertEquals(2, thingies.size());
            checkMovie("Joker" ,7, "...",thingies.get(0));
            checkMovie("Batman", 9, "...", thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}