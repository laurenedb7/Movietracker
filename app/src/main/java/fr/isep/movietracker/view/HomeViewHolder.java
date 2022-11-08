package fr.isep.movietracker.view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import fr.isep.movietracker.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    /** The film name */
    private final TextView filmName;

    public HomeViewHolder(View view) {
        super(view);
        filmName = view.findViewById(R.id.nameOfTheMovie);
    }

    /**
     * Get the film name
     * @return the film name
     */
    public TextView getFilmName() {
        return filmName;
    }
}