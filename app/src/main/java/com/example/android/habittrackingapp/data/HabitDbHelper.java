package com.example.android.habittrackingapp.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habittrackingapp.data.HabitContract.HabitEntry;

/**
 * Created by Addi_ola on 20/02/2018.
 */

public class HabitDbHelper  extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "habit.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     * */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     * @param context of the app
     */

    public HabitDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This sis called when the database is created for the first time
     */

    @Override
    public void onCreate(SQLiteDatabase db){
        //Creates a STring that conatins the SQL statement to create the pets table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_COUNTER + " INTEGER NOT NULL DEFAULT 0); ";


        Log.e(LOG_TAG, SQL_CREATE_HABITS_TABLE);

        //Execute Statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }



    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        //The database is still at version 1, so there's nothing to be done here
    }
}
