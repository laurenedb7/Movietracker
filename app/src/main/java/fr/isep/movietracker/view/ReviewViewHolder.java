/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.view;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import fr.isep.movietracker.R;

/**
 * Recycler view to display the data
 */
public class ReviewViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardView;
    private final TextView reviewName;
    private final ImageButton arrow;
    private final LinearLayout hiddenView;
    private final TextView reviewDescription;
    private final TextView reviewCowatchers;
    private final TextView reviewRating;
    private final Button deleteButton;
    private final Button updateButton;

    private ReviewViewHolder(View itemView) {
        super(itemView);

        reviewName = itemView.findViewById(R.id.nameCardView);
        cardView = itemView.findViewById(R.id.base_cardview);
        arrow = itemView.findViewById(R.id.arrow_button);
        hiddenView = itemView.findViewById(R.id.hidden_view);
        reviewDescription = itemView.findViewById(R.id.descriptionCardView);
        reviewCowatchers = itemView.findViewById(R.id.cowatchersCardview);
        reviewRating = itemView.findViewById(R.id.ratingCardview);
        deleteButton = itemView.findViewById(R.id.deleteButton);
        updateButton = itemView.findViewById(R.id.updateBbutton);

        //Expand the cardview to show the data
        arrow.setOnClickListener(view -> {
            // If the CardView is already expanded, set its visibility
            // to gone and change the expand less icon to expand more.
            if (hiddenView.getVisibility() == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.GONE);
                arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            }

            // If the CardView is not expanded, set its visibility to visible and change the expand more icon to expand less.
            else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.VISIBLE);
                arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            }
        });


    }

    /**
     * Set the name in the card view
     * @param text
     *          the text to set
     */
    public void setName(String text) {
        reviewName.setText(text);
    }

    /**
     * Set the description in the card view
     * @param text
     *          the text to set
     */
    public void setDescription(String text) {
        reviewDescription.setText(text);
    }

    /**
     * Set the cowatchers in the card view
     * @param text
     *          the text to set
     */
    public void setCowatchers(String text) {
        reviewCowatchers.setText(text);
    }

    /**
     * Set the rate in the card view
     * @param rate
     *          the rate to set
     */
    public void setRating(float rate) {
        reviewRating.setText(String.valueOf(rate));
    }

    public static ReviewViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ReviewViewHolder(view);
    }
}