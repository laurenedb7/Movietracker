/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.view.ui.reviews;

import android.app.AlertDialog;
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

import fr.isep.movietracker.controller.ReviewsPageAdapter;
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

        // Implements the event listener
        ReviewsPageAdapter.OnDeleteClickListener onDeleteClickListener = filmName -> {
            // The alert dialog to confirm the deletion
            AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
            builder.setMessage("Do you really want to delete your review ?")
                    .setPositiveButton("Yes", (dialog, which) -> reviewsViewModel.deleteReview(filmName))
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();

        };

        // Set the title of the page
        final TextView textView = binding.textReviews;
        reviewsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Set the recycler view with all the reviews retrieved from the database
        RecyclerView recyclerView;
        recyclerView = binding.recyclerviewReviews;
        final ReviewsPageAdapter adapter = new ReviewsPageAdapter(new ReviewsPageAdapter.ReviewDiff(), onDeleteClickListener);
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