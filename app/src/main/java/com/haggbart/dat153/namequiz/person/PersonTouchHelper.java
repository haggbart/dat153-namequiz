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

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START | ItemTouchHelper.END);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        Log.d(TAG, "onMove: called");
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        Log.d(TAG, "onSwiped: direction: " + direction);
        personAdapter.onItemDismiss(viewHolder.itemView, viewHolder.getAdapterPosition());
    }
}
