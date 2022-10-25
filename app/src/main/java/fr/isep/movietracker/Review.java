package fr.isep.movietracker;

import java.util.List;

public class Review {

    private String filmName;
    private String date;
    private List<String> watchers;
    private String filmDescription;
    private float filmRating;

    public Review(String filmName, String date, List<String> watchers, String filmDescription, float filmRating) {
        this.filmName = filmName;
        this.date = date;
        this.watchers = watchers;
        this.filmDescription = filmDescription;
        this.filmRating = filmRating;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<String> watchers) {
        this.watchers = watchers;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public float getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(float filmRating) {
        this.filmRating = filmRating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "filmName='" + filmName + '\'' +
                ", date='" + date + '\'' +
                ", watchers=" + watchers +
                ", filmDescription='" + filmDescription + '\'' +
                ", filmRating=" + filmRating +
                '}';
    }
}
