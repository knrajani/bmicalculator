package com.example.rajanikaparthy.bmicalculator;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

/**
 * Created by rajanikaparthy on 2017-12-19.
 */

@Entity(tableName = "bmi_entries")
public class BMIValues {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    private static final String TAG = "BMIValues";
    @ColumnInfo(name = "height")
    String height;
    @ColumnInfo(name = "weight")
    String Weight;
    @ColumnInfo(name = "date")
    String date;

    public int getId() {
        Log.d(TAG, "getId");
        return mId;
    }

    public void setId(final int id) {
        Log.d(TAG, "getId");
        mId = id;
    }

    public BMIValues() {
        Log.d(TAG, "BMIValues");
    }

    public String getHeight() {
        Log.d(TAG, "getHeight");
        return height;
    }

    public void setHeight(final String height) {
        Log.d(TAG, "setHeight");
        this.height = height;
    }

    public String getWeight() {
        Log.d(TAG, "getWeight");
        return Weight;
    }

    public void setWeight(final String weight) {
        Log.d(TAG, "setWeight");
        Weight = weight;
    }

    public String getDate() {
        Log.d(TAG, "getDate");
        return date;
    }

    public void setDate(final String date) {
        Log.d(TAG, "setDate");
        this.date = date;
    }
}
