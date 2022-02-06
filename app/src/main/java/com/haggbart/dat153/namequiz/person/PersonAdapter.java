package com.haggbart.dat153.namequiz.person;

import static com.haggbart.dat153.namequiz.helper.ImageHelper.getUri;

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
import com.haggbart.dat153.namequiz.database.People;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements PersonTouchHelperAdapter {

    private static final String TAG = "PersonAdapter";

    private final People database = People.getInstance();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_record, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonEntry person = database.get(position);
        holder.tvForename.setText(String.format("%s %s", person.getForename(), person.getSurname()));
        holder.ivImage.setImageURI(person.getImageUri() == null ? getUri(R.drawable.placeholder) : person.getImageUri());
    }

    @Override
    public int getItemCount() {
        return database.getPeople().size();
    }

    @Override
    public void onItemDismiss(View view, int position) {
        Log.d(TAG, "onItemDismiss: position: " + position);
        PersonEntry person = database.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(view.getContext(), String.format("Removed %s %s", person.getForename(), person.getSurname()), Toast.LENGTH_SHORT).show();
    }

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
