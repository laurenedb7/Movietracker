/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.isep.movietracker.model.Review;
import fr.isep.movietracker.view.ui.reviews.ReviewsViewModel;

/**
 * The activity used to add a new review
 */
public class NewReviewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    /** The View model */
    private ReviewsViewModel reviewsViewModel;

    /** The name of the movie */
    EditText name;

    /** The description of the movie */
    EditText description;

    /** The rating of the movie */
    RatingBar ratingBar;

    /** The date we watch the movie on */
    EditText date;

    /** The date we watch the movie with */
    EditText datePicker;

    /** The list of people we watch the movie with */
    List<String> cowatchersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);
        reviewsViewModel = new ViewModelProvider(this).get(ReviewsViewModel.class);
    }

    /**
     * To pick a date when we click on the date input
     * @param view
     *          the view
     */
    public void showDateDialog(View view) {
        datePicker = (EditText) view;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * Add a new cowatcher
     * @param view
     *          the view
     */
    public void addCowatcher(View view) {
        EditText cowatcher = findViewById(R.id.cowatcher);
        if (!cowatcher.getText().toString().isEmpty()) {
            cowatchersList.add(cowatcher.getText().toString());
            cowatcher.getText().clear();
        }
    }

    /**
     * The submit button to add the review in the database
     * @param view
     *          the view
     */
    public void submit(View view) throws ParseException {
        //Get the input
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        ratingBar = findViewById(R.id.ratingBar);
        date = findViewById(R.id.date);

        String filmName = name.getText().toString();
        String filmDate = date.getText().toString();
        Date filmNewDate = null;
        if (!filmDate.isEmpty()) {
            filmNewDate = new SimpleDateFormat("dd/MM/yyyy").parse(date.getText().toString());
        }
        String filmDescription = description.getText().toString();
        float filmRating = ratingBar.getRating();

        //Set the cowatchers as a string
        String cowatchers = setCowatchers(cowatchersList);

        //Create a review and add it in the database
        Review review = new Review(filmName, filmNewDate, cowatchers, filmDescription, filmRating);
        if (checkReviewAttributes(review)) {
            //add the review in the database
            reviewsViewModel.insertReview(review);
            Snackbar snackbar = Snackbar.make(view, "Adding your review", Snackbar.LENGTH_LONG);
            snackbar.setAction("Cancel", v -> {
                reviewsViewModel.deleteReview(review.getFilmName());
                Snackbar snackbar1 = Snackbar.make(view, "Your review hasn't been added", Snackbar.LENGTH_LONG);
                snackbar1.show();
                clear();
            });
            snackbar.show();
            clear();
        }
        else {
            //error message
            String messageToDisplay = "Missing fields";
            if (review.getFilmName().isEmpty()) {
                messageToDisplay = "The film name is missing";
            }
            else if (review.getDate().toString().isEmpty()) {
                messageToDisplay = "The date is missing";
            }
            else if (review.getFilmDescription().isEmpty()) {
                messageToDisplay = "The description is missing";
            }
            else if (review.getFilmRating() == 0) {
                messageToDisplay = "You haven't rate the film yet";
            }
            Snackbar snackbar = Snackbar.make(view, messageToDisplay, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    /**
     * Come back to home page on click button
     * @param view
     *          the view
     */
    public void onClickBackButton(View view) {
        //go on home page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Check if the review contains all the attributes
     * @param review
     *          the object review
     * @return {@link boolean}
     */
    private boolean checkReviewAttributes(Review review) {
        return !review.getFilmName().isEmpty()
                || !review.getFilmDescription().isEmpty()
                || review.getDate() != null
                || review.getFilmRating() != 0;
    }

    /**
     * Set the cowatchers list as a string
     * @param cowatchersList
     *              the list of cowatchers
     * @return a string with all the cowatchers separate with ","
     */
    private String setCowatchers(List<String> cowatchersList) {
        String cowatchers;
        if (!cowatchersList.isEmpty()) {
            cowatchers = cowatchersList
                    .toString()
                    .replace("[", "")
                    .replace("]", "");
        }
        else {
            cowatchers = "I watched this movie alone !";
        }
        return cowatchers;
    }

    /**
     * Clear the form
     */
    private void clear() {
        name.getText().clear();
        description.getText().clear();
        ratingBar.setRating(0F);
        date.getText().clear();
        cowatchersList.clear();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateFormat = dayOfMonth + "/" + (month+1) + "/" + year;
        datePicker.setText(dateFormat);
    }
}