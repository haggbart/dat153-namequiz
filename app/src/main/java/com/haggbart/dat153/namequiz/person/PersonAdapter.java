package com.haggbart.dat153.namequiz.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.haggbart.dat153.namequiz.R;

import java.util.List;

public class PersonAdapter<T extends PersonEntry> extends ArrayAdapter<T> {

    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private final List<T> people;

    public PersonAdapter(@NonNull Context context, int resource, List<T> people) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.people = people;
    }

    @Override
    public int getCount() {
        return people.size();
    }


    // View Holder pattern (using recycled view passed into this method as the second parameter) for smoother scrolling
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        T person = people.get(position);
        viewHolder.tvForename.setText(person.getForename());
        viewHolder.tvSurname.setText(person.getSurname());
        return convertView;
    }

    private static class ViewHolder {
        final TextView tvForename;
        final TextView tvSurname;

        ViewHolder(View view) {
            this.tvForename = view.findViewById(R.id.tvForename);
            this.tvSurname = view.findViewById(R.id.tvSurname);
        }
    }
}
