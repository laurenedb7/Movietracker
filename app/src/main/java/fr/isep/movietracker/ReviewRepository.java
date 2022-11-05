package fr.isep.movietracker;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ReviewRepository {

    private ReviewDao reviewDao;

    private LiveData<List<Review>> allReviews;

    public ReviewRepository(Application application) {
        ReviewsDatabase db = ReviewsDatabase.getInstance(application);
        reviewDao = db.reviewDao();
        allReviews = reviewDao.getAlphabetizedReviews();
    }

    public LiveData<List<Review>> getAllReviews() {
        return allReviews;
    }

    public void insertReview(Review review) {
        ReviewsDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.insertReview(review);
        });
    }
}
