/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Class for the object Review
 */
@Entity(tableName = "review_table")
public class Review {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "filmName")
    private String filmName;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "watchers")
    private String watchers;

    @ColumnInfo(name = "filmDescription")
    private String filmDescription;

    @ColumnInfo(name = "filmRating")
    private float filmRating;

    public Review(int id, String filmName, String date, String watchers, String filmDescription, float filmRating) {
        this.id = id;
        this.filmName = filmName;
        this.date = date;
        this.watchers = watchers;
        this.filmDescription = filmDescription;
        this.filmRating = filmRating;
    }

    @Ignore
    public Review(String filmName, String date, String watchers, String filmDescription, float filmRating) {
        this.filmName = filmName;
        this.date = date;
        this.watchers = watchers;
        this.filmDescription = filmDescription;
        this.filmRating = filmRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
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
