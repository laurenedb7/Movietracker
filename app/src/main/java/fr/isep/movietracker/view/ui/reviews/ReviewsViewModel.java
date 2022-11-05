package fr.isep.movietracker.view.ui.reviews;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.isep.movietracker.model.Review;
import fr.isep.movietracker.controller.ReviewRepository;

/**
 * Provide data
 */
public class ReviewsViewModel extends AndroidViewModel {

    /** The repository to manage the data */
    private final ReviewRepository repository;

    /** The list of all the reviews in the database */
    private final LiveData<List<Review>> allReviews;

    public ReviewsViewModel(Application application) {
        super(application);
        repository = new ReviewRepository(application);
        allReviews = repository.getAllReviews();
    }

    /**
     * Get all the reviews
     * @return a list of reviews
     */
    LiveData<List<Review>> getAllReviews() {
        return allReviews;
    }

    /**
     * Insert a review in the database
     * @param review
     *          the review to add
     */
    public void insertReview(Review review) {
        repository.insertReview(review);
    }

    /**
     * Update a review in the database
     * @param review
     *          the review to update
     */
    public void updateReview(Review review) {
        repository.updateReview(review);
    }

    /**
     * Delete a review from the database
     * @param review
     *          the review to delete
     */
    public void deleteReview(Review review) {
        repository.deleteReview(review);
    }
}