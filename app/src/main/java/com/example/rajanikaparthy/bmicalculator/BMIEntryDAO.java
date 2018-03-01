package com.example.rajanikaparthy.bmicalculator;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by rajanikaparthy on 2017-12-20.
 */


@Dao
public interface BMIEntryDAO {
    // crud

    @Insert
    void insert(BMIValues ... entries);

    @Query("SELECT * FROM bmi_entries")
    List<BMIValues> read();


    @Query("SELECT * FROM bmi_entries "+
            "ORDER BY height ASC" )
    List<BMIValues> readHeight();

    @Query("SELECT * FROM bmi_entries "+
            "ORDER BY weight ASC" )
    List<BMIValues> readWeight();

    @Query("SELECT * FROM bmi_entries "+
            "ORDER BY date ASC" )
    List<BMIValues> readDate();


    @Update
    void update(BMIValues ... entries);

    @Delete
    void delete(BMIValues ... entries);

}