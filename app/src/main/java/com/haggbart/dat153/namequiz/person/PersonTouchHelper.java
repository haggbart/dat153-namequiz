package com.haggbart.dat153.namequiz.person;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class PersonTouchHelper extends ItemTouchHelper.Callback {

    private static final String TAG = "PersonTouchHelper";

    private final PersonTouchHelperAdapter personAdapter;

    public PersonTouchHelper(PersonAdapter personAdapter) {
        this.personAdapter = personAdapter;
    }

    /**
     * Enables swipe
     *
     * @return true, the TouchHelper should start a swipe operation if a pointer is swiped over the View
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * Controls flags that should be returned triggered by touch behaviours
     *
     * @param recyclerView that was interacted with
     * @param viewHolder contains the data
     * @return 1 for either left or right swipe
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START | ItemTouchHelper.END);
    }

    /**
     * Not used
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        Log.d(TAG, "onMove: called");
        return false;
    }

    /**
     * Called when an item is swiped, calls onItemDismiss in the adapter with the view and position
     *
     * @param viewHolder that was swiped
     * @param direction of the swipe
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        Log.d(TAG, "onSwiped: direction: " + direction);
        personAdapter.onItemDismiss(viewHolder.itemView, viewHolder.getAdapterPosition());
    }
}
