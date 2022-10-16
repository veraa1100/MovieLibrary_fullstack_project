package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MovieListTest {
    Movie m1,m2,m3,m4;
    MovieList list;


    @BeforeEach

    void runBefore(){
        list = new MovieList("ml1");
        m1 = new Movie("Interstellar",9,"...");
        m2 = new Movie("The Grand Budapest Hotel",8,"...");
        m3 = new Movie("Green Book",9,"...");
        m4 = new Movie("Annihilation",6,"...");

    }

       @Test

    void testAddMovie() {
           assertTrue(list.addMovie(m1));
           assertTrue(list.addMovie(m2));
           assertFalse(list.addMovie(m1));
       }

    @Test

    void testRemoveMovie() {
        list.addMovie(m1);
        list.addMovie(m2);
        list.addMovie(m3);


    }

    @Test

    void TestFindMovie() {
        list.addMovie(m1);
        list.addMovie(m2);
        list.addMovie(m3);

        assertEquals(list.findMovie("The Grand Budapest Hotel"),m2);
        assertEquals(list.findMovie("Avengers"),null);

    }

    @Test

    void TestViewMovie() {
        list.addMovie(m1);

        assertEquals(list.viewMovie(),"<html>Interstellar 9 [...]\n" +
                "<br></html>");

    }










}