package persistence;

import model.Movie;
import model.MovieList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads movie list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads movielist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses movielist from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MovieList ml = new MovieList(name);
        addMovies(ml, jsonObject);
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: parses movies from JSON object and adds them to movielist
    private void addMovies(MovieList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movie");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(ml, nextMovie);
        }
    }

    // MODIFIES: movielist
    // EFFECTS: parses movie from JSON object and adds it to movielist
    private void addMovie(MovieList ml, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rating = jsonObject.getInt("rating");
        String review = jsonObject.getString("review");
        Movie movie = new Movie(name, rating, review);
        ml.addMovie(movie);
    }
}
