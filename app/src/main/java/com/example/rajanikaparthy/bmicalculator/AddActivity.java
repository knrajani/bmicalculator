package com.example.rajanikaparthy.bmicalculator;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.GregorianCalendar;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "AddActivity";
    DatePicker picker;

    private BMIDatabase db;

    EditText heightText, weightText;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        picker = findViewById(R.id.picker);

        heightText = findViewById(R.id.height_add);
        weightText = findViewById(R.id.weight_add);

        toolbar = findViewById(R.id.toolbar2); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        toolbar.setTitle("Add BMI Values");
        db = Room.databaseBuilder(
                this,
                BMIDatabase.class,
                "database.db").build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "createmenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;

            case R.id.save:
                createAndSaveEntry();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    private void createAndSaveEntry() {
        Log.d(TAG, "createAndSaveEntry");
        BMIValues entry = new BMIValues();
        entry.setHeight(heightText.getText().toString());
        entry.setWeight(weightText.getText().toString());
        entry.setDate(getCurrentDate());

        new CreateDatabase(db.getBMIEntryDao()).execute(entry);
    }

    public String getCurrentDate(){
        Log.d(TAG, "getCurrentDate");
        StringBuilder builder=new StringBuilder();
       // builder.append("Current Date: ");
        builder.append((picker.getMonth() + 1)+"/");//month is 0 based
        builder.append(picker.getDayOfMonth()+"/");
        builder.append(picker.getYear());

       /* GregorianCalendar gc = new GregorianCalendar(picker.getYear(),picker.getMonth() + 1,picker.getDayOfMonth());
        long timeStamp = gc.getTimeInMillis();// .getTimeInMillies();

        return Long.toString(timeStamp);*///
        return builder.toString();
    }

    private class CreateDatabase extends AsyncTask<BMIValues, Void, Void> {

        private BMIEntryDAO     dao;
        private MyAdapter adapter;

        public CreateDatabase(final BMIEntryDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final BMIValues... entries) {
            dao.insert(entries);
            return null;
        }

        @Override
        protected void onPostExecute(final Void notUsed) {
            finish();
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }
}
