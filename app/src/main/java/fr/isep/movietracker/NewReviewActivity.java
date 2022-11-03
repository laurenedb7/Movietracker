package fr.isep.movietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Calendar;

public class NewReviewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);
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
        Review review = new Review(filmName, filmDate, null, filmDescription, filmRating);
        System.out.println(review.toString());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateFormat = dayOfMonth + "/" + (month+1) + "/" + year;
        datePicker.setText(dateFormat);
    }
}