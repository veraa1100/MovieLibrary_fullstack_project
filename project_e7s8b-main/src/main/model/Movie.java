package model;

import org.json.JSONObject;
import persistence.Writable;

public class Movie implements Writable {
    private String name;
    private int rating;
    private String review;



    public Movie(String name, int rating, String review) {
        this.name = name;
        this.rating = rating;
        this.review = review;
    }

    // MODIFY: this
    //EFFECT: change rating.
    public void modifyRating(int rating) {
        this.rating = rating;
    }


    // MODIFY: this
    //EFFECT: change review.
    public void modifyReview(String review) {
        this.review = review;

    }

    public void editMovie(int rating, String review) {
        this.rating = rating;
        this.review = review;
        Event editmovie = new Event("A movie has been edited");
        EventLog.getInstance().logEvent(editmovie);
    }


    // EFFECT: returns movie name.
    public String getName() {
        return name;
    }

    // EFFECT: returns movie rating.
    public int getRating() {
        return rating;
    }

    // EFFECT: returns movie review.
    public String getReview() {
        return review;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rating", rating);
        json.put("review",review);
        return json;
    }
}





