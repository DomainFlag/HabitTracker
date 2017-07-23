package com.example.cchiv.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cchiv.habittracker.data.HabitContract.HabitEntry;
import com.example.cchiv.habittracker.data.HabitDbHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private HabitAdapter habitAdapter;
    private ListView listView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner_habit);
        Integer[] list = {1, 2, 3, 4, 5, 6};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout
                .support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(arrayAdapter);

        listView = (ListView) findViewById(R.id.list_item);
        ArrayList<Habit> arrayList = new ArrayList<>();
        habitAdapter = new HabitAdapter(this, arrayList);
        listView.setAdapter(habitAdapter);

        parseDataDb(fetchDataDb());

        Button button = (Button) findViewById(R.id.submit_habit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitDbHelper habitDbHelper = new HabitDbHelper(getBaseContext());
                SQLiteDatabase db = habitDbHelper.getWritableDatabase();

                TextView textView;

                textView = (EditText) findViewById(R.id.edit_habit);
                String habitText = textView.getEditableText().toString();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String habitDate = dateFormat.format(date);

                int occurrences = (int) spinner.getSelectedItem();

                ContentValues contentValues = new ContentValues();
                contentValues.put(HabitEntry.COLUMN_HABIT_NAME, habitText);
                contentValues.put(HabitEntry.COLUMN_HABIT_OCCURRENCES, occurrences);
                contentValues.put(HabitEntry.COLUMN_HABIT_DATE, habitDate);

                db.insert(HabitEntry.TABLE_NAME, null, contentValues);

                parseDataDb(fetchDataDb());
            }
        });
    }

    public Cursor fetchDataDb() {

        habitAdapter.clear();

        HabitDbHelper habitDbHelper = new HabitDbHelper(getBaseContext());
        SQLiteDatabase db = habitDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_OCCURRENCES,
                HabitEntry.COLUMN_HABIT_DATE
        };

        return db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }

    public void parseDataDb(Cursor cursor) {
        try {
            int habitTextId = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int habitOccurrencesId = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_OCCURRENCES);
            int habitDateId = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);

            while (cursor.moveToNext()) {
                habitAdapter.add(new Habit(cursor.getString(habitTextId), cursor.getInt
                        (habitOccurrencesId), cursor.getString(habitDateId)));
            }
        } finally {
            cursor.close();
        }
    }
}
