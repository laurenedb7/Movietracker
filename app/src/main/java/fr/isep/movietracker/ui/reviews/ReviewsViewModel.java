package fr.isep.movietracker.ui.reviews;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.isep.movietracker.Review;
import fr.isep.movietracker.ReviewRepository;

public class ReviewsViewModel extends AndroidViewModel {

    private ReviewRepository repository;

    private final LiveData<List<Review>> allReviews;

    private final MutableLiveData<String> mText;

    public ReviewsViewModel(Application application) {
        super(application);
        repository = new ReviewRepository(application);
        allReviews = repository.getAllReviews();
        mText = new MutableLiveData<>();
        mText.setValue("Check your last reviews !");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void insertReview(Review review) {
        repository.insertReview(review);
    }
}