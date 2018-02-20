package com.example.android.habittrackingapp.data;

import android.provider.BaseColumns;

/**
 * Created by Addi_ola on 20/02/2018.
 */

public final class HabitContract {


    private HabitContract(){}

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_HABIT_COUNTER = "times";

    }
}


