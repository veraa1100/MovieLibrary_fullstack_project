package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieList implements Writable {
    private String name;
    private List<Movie> movielist;

    public MovieList(String name) {
        this.name = name;
        this.movielist = new ArrayList<Movie>();


    }

    public String getName() {
        return name;
    }


    // MODIFY: this
    // EFFECT:adds a new movie to the end of the list and returns true, if movie has already existed, return false.
    public boolean addMovie(Movie m) {
        Movie movieTooAdd = findMovie(m.getName());
        if (movieTooAdd == null) {
            movielist.add(m);
            Event addmovie = new Event("A new movie added to the list");
            EventLog.getInstance().logEvent(addmovie);
            return true;

        }
        return false;
    }




    public String viewMovie() {
        String s = "";
        String print = "";
        for (Movie m : movielist) {
            s += (m.getName() + " " + m.getRating() + " " + "[" + m.getReview() + "]" + "\n");
            s += "<br>";
            print = "<html>" + s + "</html>";
        }
        Event viewmovie = new Event("Movie list is printed");
        EventLog.getInstance().logEvent(viewmovie);
        return print;
    }

    // EFFECTS: returns an unmodifiable list of movies in the list
    public List<Movie> getMovies() {
        return Collections.unmodifiableList(movielist);
    }




    // EFFECT: Find a movie with its name.
    public Movie findMovie(String name) {
        for (Movie movie : movielist) {
            if (movie.getName().equals(name)) {
                Event findmovie = new Event("A movie is found.");
                EventLog.getInstance().logEvent(findmovie);
                return movie;
            }
        }

        return null;
    }

    // EFFECTS: returns number of movies in this list
    public int numMovies() {
        return movielist.size();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("movie", movieToJson());
        return json;
    }

    // EFFECTS: returns movies in the list as a JSON array
    private JSONArray movieToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie t : movielist) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}







