/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.view.ui.home;

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

import fr.isep.movietracker.controller.HomePageAdapter;
import fr.isep.movietracker.databinding.FragmentHomeBinding;

/**
 * The home fragment with the last reviews and a dashboard
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final TextView nameCardView = binding.nameCardView;
        final TextView descriptionCardView = binding.descriptionCardView;
        final TextView cowatchersCardview = binding.cowatchersCardview;
        final TextView ratingCardview = binding.ratingCardview;
        homeViewModel.getLastReview().observe(getViewLifecycleOwner(), x -> {
            if (x != null) {
                nameCardView.setText(x.getFilmName());
                descriptionCardView.setText(x.getFilmDescription());
                cowatchersCardview.setText(x.getWatchers());
                ratingCardview.setText(String.valueOf(x.getFilmRating()));
            }
            else {
                binding.topMovieLayout.setVisibility(View.INVISIBLE);
            }
        });

        homeViewModel.getTopThreeReviews().observe(getViewLifecycleOwner(), list -> {
            if (!list.isEmpty()) {
                RecyclerView recyclerView;
                recyclerView = binding.recyclerviewHome;
                final HomePageAdapter adapter = new HomePageAdapter(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }
            else {
                binding.lastMovieLayout.setVisibility(View.INVISIBLE);
            }

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}