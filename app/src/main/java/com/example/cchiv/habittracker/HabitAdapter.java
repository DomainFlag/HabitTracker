package com.example.cchiv.habittracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cchiv on 23/07/2017.
 */

public class HabitAdapter extends ArrayAdapter<Habit> {

    public HabitAdapter(@NonNull Context context, @NonNull List<Habit> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.habit_layout, parent, false);

        Habit habit = getItem(position);

        TextView textView;

        textView = (TextView) convertView.findViewById(R.id.habit_text);
        textView.setText(habit.getHabit());

        textView = (TextView) convertView.findViewById(R.id.habit_date);
        textView.setText(habit.getDate());

        return convertView;
    }
}
