/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.view.ui.home;

import android.app.Application;
import android.view.View;
import android.widget.ListView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.isep.movietracker.controller.ReviewRepository;
import fr.isep.movietracker.model.Review;

public class HomeViewModel extends AndroidViewModel {

    /** The repository to manage the data */
    private final ReviewRepository repository;

    /** The list of all the reviews in the database */
    private final LiveData<List<Review>> topThreeReviews;

    /** The last movie we saw */
    private final LiveData<Review> lastReview;

    /** The title of the pahe */
    private final MutableLiveData<String> mText;

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Welcome on your personal movie tracker !");
        repository = new ReviewRepository(application);
        topThreeReviews = repository.getTopThreeReviews();
        lastReview = repository.getLastReview();
    }

    /**
     * Get the title of the page
     * @return the title of the page
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Get the top three reviews
     * @return a list of reviews
     */
    LiveData<List<Review>> getTopThreeReviews() {
        return topThreeReviews;
    }

    /**
     * Get the last review
     * @return the last review
     */
    LiveData<Review> getLastReview() {
        return lastReview;
    }
}