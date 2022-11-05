package fr.isep.movietracker.view.ui.reviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.isep.movietracker.controller.ReviewListAdapter;
import fr.isep.movietracker.databinding.FragmentReviewsBinding;

/**
 * The reviews fragment with all the reviews we have
 */
public class ReviewsFragment extends Fragment {

    private FragmentReviewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ReviewsViewModel reviewsViewModel = new ViewModelProvider(this).get(ReviewsViewModel.class);

        binding = FragmentReviewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView;
        recyclerView = binding.recyclerview;
        final ReviewListAdapter adapter = new ReviewListAdapter(new ReviewListAdapter.ReviewDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        reviewsViewModel.getAllReviews().observe(getViewLifecycleOwner(), adapter::submitList);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}