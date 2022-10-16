package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MovieLibrary {
    private static final String JSON_STORE = "./data/movielist.json";
    private MovieList movieList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public MovieLibrary() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMovieLibrary();

    }


    private void runMovieLibrary() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes list

    private void init() {
        movieList = new MovieList("ml1");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the MovieLibrary");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add new movie");
        System.out.println("\te -> edit movie");
        System.out.println("\tp -> print movie-list");
        System.out.println("\tf -> find movies");
        //System.out.println("\tl -> load movies");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("f")) {
            findMovie();
        } else if (command.equals("e")) {
            editMovie();
        } else if (command.equals("a")) {
            addMovie();
            saveMovieList();
        } else if (command.equals("p")) {
            loadMovieList();
            printMovie();
        } else {
            System.out.println("Selection not valid...");
        }
    }




    // MODIFIES: this
    // EFFECTS: add movie to the list
    private void addMovie() {
        System.out.print("Name:");
        String name = input.next();
        System.out.print("Rating (1-10):");
        int rating = input.nextInt();
        System.out.print("Review:");
        String review = input.next();
        Movie newmovie = new Movie(name, rating, review);
        movieList.addMovie(newmovie);
        System.out.print("Successfully added a new movie!");


    }
    // MODIFIES: this
    // EFFECTS: edit a movie

    private void editMovie() {
        System.out.print("Which movie would you like to modify? ");
        String name = input.next();
        Movie movieToEdit = movieList.findMovie(name);
        if (movieToEdit == null) {
            System.out.print("Sorry, couldn't find this movie.");
        } else {
            System.out.print("New Rating:");
            int newRating = input.nextInt();
            movieToEdit.modifyRating(newRating);

            System.out.print("New Review:");
            String newReview = input.next();
            movieToEdit.modifyReview(newReview);
            System.out.print("Successfully updated");

        }
    }
    // EFFECTS: search for a movie and print it.

    private void findMovie() {
        System.out.print("Please type in the movie name you want to search.");
        String name = input.next();
        Movie movie1 = movieList.findMovie(name);
        if (movie1 == null) {
            System.out.print("Sorry, couldn't find this movie.");
        } else {
            System.out.println(movie1.getName() + " " + movie1.getRating() + " " + "'" + movie1.getReview() + "'");


        }


    }

    // EFFECTS: prints all the movies in list to the console
    private void printMovie() {
        List<Movie> movielist = movieList.getMovies();
        for (Movie m : movielist) {
            System.out.println(m.getName() + " " + m.getRating() + " " + "'" + m.getReview() + "'");
        }
    }


    // EFFECTS: saves the movielist to file
    private void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            System.out.println("Saved " + movieList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movielist from file
    private void loadMovieList() {
        try {
            movieList = jsonReader.read();
            System.out.println("Loaded " + movieList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}
