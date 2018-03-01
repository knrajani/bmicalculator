package com.example.rajanikaparthy.bmicalculator;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BMIActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "BMIActivity";
    Spinner spinH,spinW;
    BMIDetail mBMIDetail;
    ListView bmiList;
    String[] bmiValues;
    int selectedIndex = -1;
    String[] spinAH;
    String[] spinAW;
    String wUnit,hUnit;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        setUpSpinner();
        setUpList();
        mToolbar = findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("BMI Calculator");
        mToolbar.setNavigationIcon(R.drawable.ic_refresh_black_24dp);

       /* final ActionBar actionBar = getSupportActionBar();
        Log.d(TAG, "onCreate: " + actionBar);
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/
       // getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "createmenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setUpSpinner()
    {
        Log.d(TAG, "setUpSpinner");
        spinH = findViewById(R.id.spinnerHeight);
        spinW = findViewById(R.id.spinnerWeight);
        spinAH = getArrayValues("height");
        spinAW = getArrayValues("weight");
        wUnit = "(in Kgs)";
        hUnit = "(in Cms)";

       // spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaH = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinAH);
        aaH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinH.setAdapter(aaH);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaW = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinAW);
        aaH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinW.setAdapter(aaW);



       /* spinW.setOnItemClickListener((new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                wUnit = spinAW[position];
                Toast.makeText(BMIActivity.this, spinAW[position], Toast.LENGTH_SHORT).show();
            }
        }));

        spinH.setOnItemClickListener((new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                hUnit = spinAH[position];
                Toast.makeText(BMIActivity.this, spinAH[position], Toast.LENGTH_SHORT).show();
            }
        }));*/

    }

    public void setUpList(){
        Log.d(TAG, "setUpList");
        bmiList = (ListView) findViewById(R.id.bmiList);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
         //       android.R.layout.simple_list_item_1, R.id.listText, getArrayValues("bmi"));
        bmiValues = getArrayValues("bmi");
    //    ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bmiValues);
        bmiList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
       // bmiList.setAdapter(adapter);


        bmiList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item,bmiValues)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                Log.d(TAG, "getView");
                final View renderer = super.getView(position, convertView, parent);
                if (position == selectedIndex)
                {
                    //TODO: set the proper selection color here:
                    //renderer.setBackgroundResource(android.R.color.darker_gray);
                    renderer.setBackgroundResource(android.R.color.holo_green_light);
                }
                else
                {
                    renderer.setBackgroundResource(android.R.color.white);
                }
                return renderer;
            }
        });


     /*   bmiList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
         @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(BMIActivity.this, bmiValues[position], Toast.LENGTH_SHORT).show();
            }
        });*/

       // bmiList.performItemClick(bmiList, 3, bmiList.getItemIdAtPosition(3));
    }


    public void calculateBMI() {

        Log.d(TAG, "calculateBMI");



        Float val = 0.0f;
        mBMIDetail = new BMIDetail();
        mBMIDetail.sethUnit(String.valueOf(spinH.getSelectedItem()));
        mBMIDetail.setwUnit(String.valueOf(spinW.getSelectedItem()));
        String heightStr = "0";
        String weightStr = "0";
        heightStr = ((EditText)findViewById(R.id.heightEV)).getText().toString();
        weightStr = ((EditText)findViewById(R.id.weightEV)).getText().toString();

        InputMethodManager imm = (InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((EditText)findViewById(R.id.weightEV)).getWindowToken(), 0);
        imm.hideSoftInputFromWindow(((EditText)findViewById(R.id.heightEV)).getWindowToken(), 0);

        if(TextUtils.isEmpty(heightStr)){
            heightStr = "0";
        }else
        if(TextUtils.isEmpty(weightStr)){
            weightStr = "0";
        }else {
            mBMIDetail.setHeight(heightStr);
            mBMIDetail.setWeight(weightStr);
            val = mBMIDetail.calculateValue();
            ((TextView) findViewById(R.id.bmiValue)).setText("BMI : " + Float.toString(val));
            selectedIndex = mBMIDetail.displayBMI(val);
            setUpList();
        }
    }

    public String[] getArrayValues(String arVal)
    {
        Log.d(TAG, "getArrayValues");
        int arrayName_ID= getResources().getIdentifier(arVal , "array",this.getPackageName());
        return getResources().getStringArray(arrayName_ID);
    }

    @Override
    public void onClick(final View view) {
        Log.d(TAG, "onClick");
        switch(view.getId())
        {
            case R.id.calculateBV :
                calculateBMI();
                break;
            /*case R.id.settingsButton :
                showSetting();
                break;
            case R.id.historyButton:
                showHistory();
                break;*/
            default:
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                clearAllValues();
                return true;

            case R.id.history_main:
                showHistory();
                return true;

           /* case R.id.setting_main:
                showSetting();
                return true;*/

            case R.id.about_main:
                showAbout();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    public void clearAllValues(){
        Log.d(TAG, "clearAllValues");
        ((EditText)findViewById(R.id.heightEV)).setText("");
        ((EditText)findViewById(R.id.weightEV)).setText("");
        ((TextView)findViewById(R.id.bmiValue)).setText("BMI : ");
        selectedIndex = -1;
        setUpList();
    }

    public void showSetting(){
        Log.d(TAG, "showSetting");
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
    }

    public void showAbout(){
        Log.d(TAG, "showAbout");
        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
    }

    public void showHistory(){
        Log.d(TAG, "showHistory");
        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
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
