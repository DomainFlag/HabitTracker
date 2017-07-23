package com.example.cchiv.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cchiv.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Cchiv on 23/07/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public HabitDbHelper(Context context) {
        super(context, "habits.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
