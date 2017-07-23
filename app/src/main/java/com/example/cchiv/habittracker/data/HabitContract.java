package com.example.cchiv.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Cchiv on 23/07/2017.
 */

public class HabitContract {

    public HabitContract() {};

    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_OCCURRENCES = "occurrences";
        public static final String COLUMN_HABIT_DATE = "date";
    }
}
