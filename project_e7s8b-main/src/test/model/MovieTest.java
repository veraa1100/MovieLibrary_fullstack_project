package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    Movie movie;

    @BeforeEach

    void runBefore(){
        movie = new Movie("Interstellar",9,"...");

    }

    @Test

    void testMovie(){
        assertEquals(movie.getName(),"Interstellar");
        assertEquals(movie.getRating(),9);
        assertEquals(movie.getReview(),"...");


    }

    @Test

    void testModifyMovie(){
        movie.modifyRating(8);
        movie.modifyReview("Great");
        assertEquals(movie.getRating(),8);
        assertEquals(movie.getReview(),"Great");
    }


    @Test

    void testEditMovie(){
        movie.editMovie(8,"Great");
        assertEquals(movie.getRating(),8);
        assertEquals(movie.getReview(),"Great");
    }




}
