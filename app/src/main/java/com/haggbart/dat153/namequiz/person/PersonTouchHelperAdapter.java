package com.haggbart.dat153.namequiz.person;

import android.view.View;

public interface PersonTouchHelperAdapter {

    /**
     * An item was dismissed by swiping
     *
     * @param view that was swiped
     * @param position of the view
     */
    void onItemDismiss(View view, int position);
}
