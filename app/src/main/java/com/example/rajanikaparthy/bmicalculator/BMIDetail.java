package com.example.rajanikaparthy.bmicalculator;


import android.util.Log;

/**
 * Created by rajanikaparthy on 2017-12-10.
 */

public class BMIDetail {

    private static final String TAG = "BMIDetail";
    private float mHeight;
    private float mWeight;
    private float mValue;
    private String bmiValue;
    private String wUnit;
    private String hUnit;


    public BMIDetail() {
        Log.d(TAG, "BMIDetail");
    }

    public String getwUnit() {
        Log.d(TAG, "getwUnit");
        return wUnit;
    }

    public void setwUnit(final String wUnit) {
        Log.d(TAG, "setwUnit");
        this.wUnit = wUnit;
    }

    public String gethUnit() {
        Log.d(TAG, "gethUnit");
        return hUnit;
    }

    public void sethUnit(final String hUnit) {
        Log.d(TAG, "sethUnit");
        this.hUnit = hUnit;
    }

    public float getHeight() {
        Log.d(TAG, "getHeight");
        return mHeight;
    }

    public void setHeight(final String height) {
        Log.d(TAG, "setHeight");
        mHeight = Float.parseFloat(height)/100;
        if(this.hUnit.equals("(in Inches)"))
        {
            mHeight = mHeight * 2.54f;
        }
    }

    public float getWeight() {
        Log.d(TAG, "getWeight");
        return mWeight;
    }

    public void setWeight(final String weight) {
        Log.d(TAG, "setWeight");
        mWeight = Float.parseFloat(weight);
        if(this.wUnit.equals("(in Lbs)"))
        {
            mWeight = mWeight * 0.453592f;
        }
    }

    public Float calculateValue()
    {
        Log.d(TAG, "calculateValue");

        mValue = mWeight / (mHeight * mHeight);
       // displayBMI(mValue);
       // return bmiValue;

        return mValue;
    }


    public int displayBMI(float bmi) {
        Log.d(TAG, "displayBMI");
       // bmiValue = "";
        int k = -1;
        if (Float.compare(bmi, 15f) <= 0) {
            k = 0;
            //bmiValue = "Very severely underweight";//String.valueOf((R.string.very_severely_underweight));
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            //bmiValue = "Severely underweight";//String.valueOf((R.string.severely_underweight));
            k = 1;
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
           // bmiValue = "Underweight";//String.valueOf((R.string.underweight));
            k = 2;
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            //bmiValue = "Normal";//String.valueOf((R.string.normal));
            k = 3;
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            //bmiValue = "Overweight";//String.valueOf((R.string.overweight));
            k = 4;
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            //bmiValue = "Obese class I";//String.valueOf((R.string.obese_class_i));
            k = 5;
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
           // bmiValue = "Obese class II";//String.valueOf((R.string.obese_class_ii));
            k = 6;

        } else {
           // bmiValue = "Obese class III";//String.valueOf((R.string.obese_class_iii));
            k = 7;

        }
        return k;
       // bmiValue = bmi + "\n\n" + bmiValue;
    }



}
