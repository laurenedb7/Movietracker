package fr.isep.movietracker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.isep.movietracker.ui.reviews.ReviewsFragment;
import fr.isep.movietracker.ui.reviews.ReviewsViewModel;

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
            textView.setText(cowatchersList.toString().replace("[", "").replace("]", ""));
        }
    }

    /**
     * The submit button to add the review in the database
     * @param view
     *          the view
     */
    public void submit(View view) {
        EditText name = findViewById(R.id.name);
        EditText description = findViewById(R.id.description);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        EditText date = findViewById(R.id.date);
        String filmName = name.getText().toString();
        String filmDate = date.getText().toString();
        String filmDescription = description.getText().toString();
        float filmRating = ratingBar.getRating();

        String cowatchers;
        if (!cowatchersList.isEmpty()) {
            cowatchers = cowatchersList.toString();
        }
        else {
            cowatchers = "I watched this movie alone !";
        }

        Review review = new Review(filmName, filmDate, cowatchers, filmDescription, filmRating);
        if (checkReviewAttributes(review)) {
            reviewsViewModel.insertReview(review);
            name.getText().clear();
            description.getText().clear();
            ratingBar.setRating(0F);
            date.getText().clear();
            cowatchersList.clear();
        }
        else {

        }
    }

    private boolean checkReviewAttributes(Review review) {
        return !review.getFilmName().isEmpty() || !review.getFilmDescription().isEmpty() || review.getFilmRating() != 0;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateFormat = dayOfMonth + "/" + (month+1) + "/" + year;
        datePicker.setText(dateFormat);
    }
}