package fr.isep.movietracker.controller;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.isep.movietracker.view.ReviewViewHolder;
import fr.isep.movietracker.model.Review;

public class ReviewListAdapter extends ListAdapter<Review, ReviewViewHolder> {

    public ReviewListAdapter(@NonNull DiffUtil.ItemCallback<Review> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReviewViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        Review current = getItem(position);
        holder.setName(current.getFilmName());
        holder.setDescription(current.getFilmDescription());
        holder.setCowatchers(current.getWatchers());
        holder.setRating(current.getFilmRating());
    }

    public static class ReviewDiff extends DiffUtil.ItemCallback<Review> {

        @Override
        public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.getFilmName().equals(newItem.getFilmName())
                    && oldItem.getFilmDescription().equals(newItem.getFilmDescription())
                    && oldItem.getDate().equals(newItem.getDate())
                    && oldItem.getWatchers().equals(newItem.getWatchers())
                    && oldItem.getFilmRating() == newItem.getFilmRating();
        }
    }
}
