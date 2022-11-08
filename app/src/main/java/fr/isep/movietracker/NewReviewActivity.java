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

public class NewReviewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ReviewsViewModel reviewsViewModel;

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
            TextView textView = findViewById(R.id.textView3);
            textView.setText(cowatchersList
                    .toString()
                    .replace("[", "")
                    .replace("]", ""));
        }
    }

    /**
     * The submit button to add the review in the database
     * @param view
     *          the view
     */
    public void submit(View view) throws ParseException {
        //Get the input
        EditText name = findViewById(R.id.name);
        EditText description = findViewById(R.id.description);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        EditText date = findViewById(R.id.date);

        String filmName = name.getText().toString();
        Date filmDate = new SimpleDateFormat("dd/MM/yyyy").parse(date.getText().toString());
        String filmDescription = description.getText().toString();
        float filmRating = ratingBar.getRating();

        //Set the cowatchers as a string
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

        //Create a review and add it in the database
        Review review = new Review(filmName, filmDate, cowatchers, filmDescription, filmRating);
        if (checkReviewAttributes(review)) {
            //add the review in the database
            reviewsViewModel.insertReview(review);
            Snackbar snackbar = Snackbar.make(view, "Adding your review", Snackbar.LENGTH_LONG);
            snackbar.setAction("Cancel", v -> {
                reviewsViewModel.deleteReview(review.getFilmName());
                Snackbar snackbar1 = Snackbar.make(view, "Your review hasn't been added", Snackbar.LENGTH_LONG);
                snackbar1.show();
            });
            snackbar.show();

            //go on home page
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
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
     * Check if the review contains all the attributes
     * @param review
     *          the object review
     * @return {@link boolean}
     */
    private boolean checkReviewAttributes(Review review) {
        return !review.getFilmName().isEmpty() || !review.getFilmDescription().isEmpty() || review.getFilmRating() != 0;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateFormat = dayOfMonth + "/" + (month+1) + "/" + year;
        datePicker.setText(dateFormat);
    }
}