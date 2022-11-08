/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.isep.movietracker.R;
import fr.isep.movietracker.model.Review;
import fr.isep.movietracker.view.HomeViewHolder;

public class HomePageAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    List<Review> items;

    public HomePageAdapter(List<Review> items) {
        super();
        this.items = items;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_home_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.getFilmName().setText(items.get(position).getFilmName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}