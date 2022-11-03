package fr.isep.movietracker.ui.reviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReviewsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReviewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Check your last reviews !");
    }

    public LiveData<String> getText() {
        return mText;
    }
}