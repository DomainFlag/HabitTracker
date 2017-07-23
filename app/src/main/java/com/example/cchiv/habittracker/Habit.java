package com.example.cchiv.habittracker;

/**
 * Created by Cchiv on 23/07/2017.
 */

public class Habit {

    private String habit;
    private String date;

    public Habit(String string1, String string2) {
        habit = string1;
        date = string2;
    }

    public String getHabit() {
        return habit;
    }

    public String getDate() {
        return date;
    }
}
