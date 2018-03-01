package com.example.rajanikaparthy.bmicalculator;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fabAdd;
    Toolbar toolbar;
    String sortOrder = "none";//height,weight,date

    private BMIDatabase db;
    private MyAdapter   adapter;
    BMIValues deleteEntry;

    private static final String TAG = "HistoryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Get database reference
        db = Room.databaseBuilder(
                this,
                BMIDatabase.class,
                "database.db").build();


        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "floating action button onclick");
                Snackbar.make(view, "Add new entry to history", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                addDetail();


            }
        });


        toolbar = findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        toolbar.setTitle("BMI Values");


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter();

        adapter.setOnBMIEntryClickListener(new OnbmiEntryClickedListener() {
            @Override
            public void OnbmiEntryClicked(final BMIValues entry) {
                Log.d(TAG, "onTimeEntryClicked() called with: entry = [" + entry + "]");
                deleteValues(entry);

            }
        });
        recyclerView.setAdapter(adapter);




     /*   List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test");
        }// define an adapter


        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "createmenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;

            case R.id.weight_item:
                sortWeight();
                return true;

            case R.id.date_item:
                sortDate();
                return true;

            case R.id.height_item:
                sortHeight();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void closeHistory(){
        Log.d(TAG, "closeHistory");
    }

    private void sortWeight() {

        Log.d(TAG, "sortWeight");
        sortOrder = "weight";//height,weight,date
        refreshList();
    }

    private void sortDate() {
        Log.d(TAG, "sortDate");
        sortOrder = "date";//height,weight,date
        refreshList();
    }

    private void sortHeight() {
        Log.d(TAG, "sortHeight");
        sortOrder = "height";//height,weight,date
        refreshList();
    }

    private void deleteValues(BMIValues entry) {
        Log.d(TAG, "deleteValues");
        deleteEntry = entry;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to delete ? ");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                sortOrder = "delete";//height,weight,date
                                refreshList();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }



    public void addDetail()
    {
        Log.d(TAG, "addDetail");

        startActivity(new Intent(getApplicationContext(),AddActivity.class));
    }

    private void refreshList() {
        Log.d(TAG, "addDetail");
        new ReadDatabase(db.getBMIEntryDao(), adapter, sortOrder).execute();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        refreshList();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
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

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    public void onClick(final View view) {
        Log.d(TAG, "onRestart");
        switch(view.getId())
        {
            case R.id.height_click :
                sortHeight();
                break;
            case R.id.weight_click:
                sortWeight();
                break;
            case R.id.date_click:
                sortDate();
                break;
            default:
                break;
        }
    }


    private class ReadDatabase extends AsyncTask<Void, Void, List<BMIValues>> {

        private BMIEntryDAO     dao;
        private MyAdapter adapter;
        String sortOrder;

        public ReadDatabase(final BMIEntryDAO dao, final MyAdapter adapter, String sortOrder) {
            this.dao = dao;
            this.adapter = adapter;
            this.sortOrder = sortOrder;
        }

        @Override
        protected List<BMIValues> doInBackground(final Void... voids) {
            if (sortOrder.equals("height")){
                return dao.readHeight();
            }
            if (sortOrder.equals("weight")){
                return dao.readWeight();
            }
            if (sortOrder.equals("date")){
                return dao.readDate();
            }
            if (sortOrder.equals("delete")){
                dao.delete(deleteEntry);
            }
            return dao.read();
        }

        @Override
        protected void onPostExecute(final List<BMIValues> entries) {
            adapter.addBMIEntries(entries);
        }
    }
}
