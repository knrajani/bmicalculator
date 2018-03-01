package com.example.rajanikaparthy.bmicalculator;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by rajanikaparthy on 2017-12-20.
 */


@Database(entities = {BMIValues.class}, version = 1, exportSchema = false)
public abstract class BMIDatabase extends RoomDatabase {
    public abstract BMIEntryDAO getBMIEntryDao();
}
