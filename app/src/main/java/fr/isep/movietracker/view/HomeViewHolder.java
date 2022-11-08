package fr.isep.movietracker.view;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import fr.isep.movietracker.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    private final TextView filmName;

    public HomeViewHolder(View view) {
        super(view);
        filmName = view.findViewById(R.id.nameOfTheMovie);
    }

    public TextView getFilmName() {
        return filmName;
    }
}