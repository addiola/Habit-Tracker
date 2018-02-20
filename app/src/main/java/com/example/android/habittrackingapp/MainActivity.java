package com.example.android.habittrackingapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.android.habittrackingapp.data.HabitContract.HabitEntry;
import com.example.android.habittrackingapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    //EditText for user input on habit name
    private EditText nameEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    private void insertHabit() {
        mDbHelper = new HabitDbHelper(this) ;

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String nameText = nameEditText.getText().toString().trim();


        //Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, nameText);
        values.put(HabitEntry.COLUMN_HABIT_COUNTER, 1);


        // creates a new entry in habit table
        db.insert(HabitEntry.TABLE_NAME, null, values);

        db.close();

    }


    // Get the count
    //use to update count of an existing habit
    public int getHabitCount(String habitName) {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //query habits
        // to get a Cursor that contains all rows from the pets table.
        Cursor c = db.query (HabitEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);



        //gets index of columns
        int nameColumnIndex = c.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
        int countColumnIndex = c.getColumnIndex(HabitEntry.COLUMN_HABIT_COUNTER);

        //initializes a habit count
        int habitCount = 0;

        //Move through rows
        //for each row check if the habit name matches user Input
        //if so get the count so you can update count
        while (c.moveToNext()){
            if (habitName.equals( c.getString(nameColumnIndex))){
                habitCount = c.getInt(countColumnIndex);
            }
        }


        return habitCount;
    }


    // Increment count by 1
    public void updateHabitCount() {

        //set up db for updating
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        //Existing habit gotten from spinner
        String habitName = "";
        //call method to get habit count for the selected habit
        int habitCount =  getHabitCount(habitName);
        habitCount++;

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_COUNTER, habitCount);

        //Update habit count
        // insert new count into db
        db.update(HabitEntry.TABLE_NAME, values,
                HabitEntry.COLUMN_HABIT_NAME + " = ?",
                new String[]{habitName});

        db.close();

    }
}
