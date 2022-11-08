/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.controller;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.isep.movietracker.model.Review;
import fr.isep.movietracker.model.ReviewDao;

/**
 * Repository to manage queries and data
 */
public class ReviewRepository {

    /** The Review DAO */
    private final ReviewDao reviewDao;

    /** The list of all reviews in the database */
    private final LiveData<List<Review>> allReviews;

    /** The list of all reviews in the database */
    private final LiveData<List<Review>> topThreeReviews;

    private final LiveData<Review> lastReview;

    public ReviewRepository(Application application) {
        ReviewsRoomDatabase db = ReviewsRoomDatabase.getInstance(application);
        reviewDao = db.reviewDao();
        allReviews = reviewDao.getAlphabetizedReviews();
        topThreeReviews = reviewDao.getTopThreeReviews();
        lastReview = reviewDao.getLastReview();
    }

    /**
     * Get all the reviews
     * @return a list of all the reviews
     */
    public LiveData<List<Review>> getAllReviews() {
        return allReviews;
    }

    /**
     * Get the top 3 reviews
     * @return a list of the top 3 reviews
     */
    public LiveData<List<Review>> getTopThreeReviews() {
        return topThreeReviews;
    }

    /**
     * Get the last movie I see
     * @return the last review
     */
    public LiveData<Review> getLastReview() {
        return lastReview;
    }

    /**
     * Insert a review in the database
     * @param review
     *          the review to add
     */
    public void insertReview(Review review) {
        ReviewsRoomDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.insertReview(review);
        });
    }

    /**
     * Update a review in the database
     * @param review
     *          the review to update
     */
    public void updateReview(Review review) {
        ReviewsRoomDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.updateReview(review);
        });
    }

    /**
     * Delete a review from the database
     * @param filmName
     *          the review to delete
     */
    public void deleteReview(String filmName) {
        ReviewsRoomDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.deleteReview(filmName);
        });
    }
}
