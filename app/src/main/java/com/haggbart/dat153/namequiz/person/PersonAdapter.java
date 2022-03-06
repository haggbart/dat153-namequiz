package com.haggbart.dat153.namequiz.person;

import static com.haggbart.dat153.namequiz.helper.ImageHelper.getUri;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haggbart.dat153.namequiz.R;
import com.haggbart.dat153.namequiz.data.AppDatabase;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements PersonTouchHelperAdapter {

    private static final String TAG = "PersonAdapter";

    private final List<PersonEntry> people;
    private final PersonDao dao;

    public PersonAdapter(Context applicationContext, List<PersonEntry> people) {
        this.dao = AppDatabase.getINSTANCE(applicationContext).personDao();
        this.people = people;
    }

    /**
     * Called by RecyclerView when it needs to create a new ViewHolder.
     * This will only be called a few times as it will recycle old views (Viewholder pattern)
     *
     * @param parent parent view
     * @param viewType type of view
     * @return new ViewHolder object
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_record, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to fetch and fill the ViewHolder's fields
     *
     * @param holder the viewholder
     * @param position position in the list
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonEntry person = people.get(position);
        holder.tvForename.setText(String.format("%s %s", person.getForename(), person.getSurname()));
        holder.ivImage.setImageURI(person.getImageUri() == null ? getUri(R.drawable.placeholder) : person.getImageUri());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    /**
     * Called by TouchHelper when an element is swiped left or right
     *
     * @param view the view that was swiped
     * @param position the position of the element
     */
    @Override
    public void onItemDismiss(View view, int position) {
        Log.d(TAG, "onItemDismiss: position: " + position);
        PersonEntry person = people.remove(position);
        dao.delete(person);
        notifyItemRemoved(position);
        Toast.makeText(view.getContext(), String.format("Removed %s %s", person.getForename(), person.getSurname()), Toast.LENGTH_SHORT).show();
    }

    /**
     * ViewHolder used in the viewholder pattern
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivImage;
        final TextView tvForename;

        ViewHolder(final View view) {
            super(view);
            this.ivImage = view.findViewById(R.id.ivImage);
            this.tvForename = view.findViewById(R.id.tvFullName);
        }
    }
}
