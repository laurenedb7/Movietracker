/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.controller;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.isep.movietracker.view.ReviewsViewHolder;
import fr.isep.movietracker.model.Review;

public class ReviewsPageAdapter extends ListAdapter<Review, ReviewsViewHolder> {

    /**
     * Delete review interface
     */
    public interface OnDeleteClickListener {
        void onDeleteClickListener(String filmName);
    }

    /** The event listener */
    private final OnDeleteClickListener onDeleteClickListener;

    public ReviewsPageAdapter(@NonNull DiffUtil.ItemCallback<Review> diffCallback, OnDeleteClickListener listener) {
        super(diffCallback);
        this.onDeleteClickListener = listener;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReviewsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, int position) {
        Review current = getItem(position);
        holder.setName(current.getFilmName());
        holder.setDescription(current.getFilmDescription());
        holder.setCowatchers(current.getWatchers());
        holder.setRating(current.getFilmRating());
        holder.setDate(current.getDate());

        holder.getDeleteButton().setOnClickListener(view -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClickListener(current.getFilmName());
            }
        });
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
