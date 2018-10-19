package com.example.abautista.perfectsummer3;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static int ProgressBarValueWeather    = 0;
    private static int ProgressBarValueSeaWeather = 0;
    private static int ProgressBarRainfall        = 0;
    private static int ProgressBarSunshine        = 0;
    private static int ProgressBarPrices          = 0;
    private static int ProgressBarStdLiving       = 0;


    //see the weather temperature
    private static SeekBar weatherSeekBar;
    private static TextView weatherTextView;

    //See the water temperature
    private static SeekBar seaWeatherSeekBar;
    private static TextView seaWeatherTextView;

    //see the rainfall values
    private static SeekBar seeRainfallSeekBar;
    private static TextView rainfallTextView;

    //see the sunshine values
    private static SeekBar seeSunshineSeekBar;
    private static TextView sunshineTextView;

    //see the prices values
    private static SeekBar seePricesSeekBar;
    private static TextView seePricesTextView;

    //see the StdLiving values
    private static SeekBar seeStdValuesSeekBar;
    private static TextView seeStdValuesTextView;

    //create buttons, radiogroups, textviews...
    private static Button showResultsMonthsAndCities, showResultsMonths, showResultsCities, showResultsConsumerPrices,
            showResultsAvgStdOfLiving, showResultsWeather, showResultsRainfall, showResultsSeaTemp,
            showResultsSunshine, showResults ;

    private static RadioGroup selectedMonth, selectedSunshine, selectedPrices, selectedAvgStd, selectedRainfall;
    private static RadioButton selectedMonthRb, selectedRainfallRb, selectedSunshineRb, selectedPriceRb, selectedAvgStdRb;
    private static TextView viewWhichMonthIsSelected;

    //create the database in the main activity
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seeTheWeatherTemp();
        seeTheSeaWeather();
        seeTheRainfall();
        seeTheSunshine();
        seeThePrices();
        seeTheStdValues();

        //this calls the constructor of the database
        myDb = new DatabaseHelper(this);

        showResultsMonthsAndCities  = (Button)findViewById(R.id.showResultsMonthsAndCities);
        showResultsMonths           = (Button)findViewById(R.id.showResultsMonths);
        showResultsCities           = (Button)findViewById(R.id.showResultsCities);
        showResultsConsumerPrices   = (Button)findViewById(R.id.showResultsConsumerPrices);
        showResultsAvgStdOfLiving   = (Button)findViewById(R.id.showResultsAvgStdOfLiving);
        showResultsWeather          = (Button)findViewById(R.id.showResultsWeather);
        showResultsRainfall         = (Button)findViewById(R.id.showResultsRainfall);
        showResultsSeaTemp          = (Button)findViewById(R.id.showResultsSeaTemp);
        showResultsSunshine         = (Button)findViewById(R.id.showResultsSunshine);
        showResults                 = (Button)findViewById(R.id.showResults);

        viewResults();
        onClickListenerButton();

    }

    //get and set methods for SeaWeather
    public int getProgressBarValueSeaWeather(){
        return ProgressBarValueSeaWeather;
    }
    public void setProgressBarValueSeaWeather(int progressBarValueSeaWeather){
        this.ProgressBarValueSeaWeather = progressBarValueSeaWeather;
    }

    //get and set methods for Weather
    public int getProgressBarValueWeather(){
        return ProgressBarValueWeather;
    }
    public void setProgressBarValueWeather(int progressBarValueWeather){
        this.ProgressBarValueWeather = progressBarValueWeather;
    }

    //get and set methods for Rainfall
    public int getProgressBarRainfall(){
        return ProgressBarRainfall;
    }
    public void setProgressBarRainfall(int progressBarRainfall){
        this.ProgressBarRainfall = progressBarRainfall;
    }

    //get and set methods for Sunshine
    public int getProgressBarSunshine(){
        return ProgressBarSunshine;
    }
    public void setProgressBarSunshine(int progressBarSunshine){
        this.ProgressBarSunshine = progressBarSunshine;
    }

    //get and set methods for Prices compared with occidental world
    public int getProgressBarPrices(){
        return ProgressBarPrices;
    }
    public void setProgressBarPrices(int progressBarPrices){
        this.ProgressBarPrices = progressBarPrices;
    }

    //get and set methods for Standard living values
    public int getProgressBarStdLiving(){
        return ProgressBarStdLiving;
    }
    public void setProgressBarStdLiving(int progressBarStdLiving){
        this.ProgressBarStdLiving = progressBarStdLiving;
    }

    //Progress bars
    public void seeTheWeatherTemp (){

        weatherSeekBar  = (SeekBar)findViewById(R.id.seekTheWeatherTemp);
        weatherTextView = (TextView)findViewById(R.id.seeTheWeatherTemp);
        //This line helps us to see the initial value of the seekbar once it gets initialized.
        weatherTextView.setText("Current weather: "+weatherSeekBar.getProgress() +"ºC" );
        //weatherTextView.setText("Current weather: "+weatherSeekBar.getProgress()+" / " + weatherSeekBar.getMax() +"ºC" );

        //This piece of code helps us to see the current progress of the seekbar when you start moving the seekbar.
        weatherSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        setProgressBarValueWeather(progress_value);
                        weatherTextView.setText("Desired weather: " + progress  +"ºC");
                        //Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        weatherTextView.setText("Current: " + progress_value +"ºC");
                        //Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }

        );// End of the piece of code: see the current progress of the seekbar.
    }

    public void seeTheSeaWeather(){

        seaWeatherSeekBar = (SeekBar) findViewById(R.id.seekTheSeaWeatherTemp);
        seaWeatherTextView = (TextView) findViewById(R.id.seeTheSeaWeatherTemp);

        seaWeatherTextView.setText("Current sea weather: " + seaWeatherSeekBar.getProgress() + "ºC");


        seaWeatherSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        setProgressBarValueSeaWeather(progress_value);
                        seaWeatherTextView.setText("Desired sea weather: " + progress + "ºC");
                        //Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seaWeatherTextView.setText("Current: " + progress_value + "ºC");
                        //Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void seeTheRainfall(){

        seeRainfallSeekBar = (SeekBar) findViewById(R.id.seekTheRainfallValue);
        rainfallTextView = (TextView) findViewById(R.id.seeTheRainfallValues);

        rainfallTextView.setText("Current rainfall values: " + seeRainfallSeekBar.getProgress() + " mm over month");

        seeRainfallSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        setProgressBarRainfall(progress_value);
                        rainfallTextView.setText("Rainfall value: " + progress + " mm over month");
                        //Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        rainfallTextView.setText("Current: " + progress_value + " mm over month");
                        //Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void seeTheSunshine(){

        seeSunshineSeekBar = (SeekBar) findViewById(R.id.seekTheSunshineValue);
        sunshineTextView = (TextView) findViewById(R.id.seeTheSunshineValues);

        sunshineTextView.setText("Current sunshine daylight hours: ");

        seeSunshineSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        setProgressBarSunshine(progress_value);
                        sunshineTextView.setText("Current sunshine daylight hours: " + seeSunshineSeekBar.getProgress() + " average hours");
                        //Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        sunshineTextView.setText("Current sunshine daylight hours: " + seeSunshineSeekBar.getProgress() + " average hours");
                        //Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void seeThePrices(){
        seePricesSeekBar = (SeekBar) findViewById(R.id.seekThePricesValues);
        seePricesTextView = (TextView) findViewById(R.id.seeThePriceValues);

        seePricesTextView.setText("Prices compared with occidental world: ");

        seePricesSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        setProgressBarPrices(progress_value);
                        seePricesTextView.setText("Current prices: " + seePricesSeekBar.getProgress());
                        //Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seePricesTextView.setText("Current prices: " + seePricesSeekBar.getProgress());
                        //Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void seeTheStdValues(){
        seeStdValuesSeekBar = (SeekBar) findViewById(R.id.seekTheStdValues);
        seeStdValuesTextView = (TextView) findViewById(R.id.seeTheStdOfLivingValues);

        seeStdValuesTextView.setText("Index comparison of standard of living: ");

        seeStdValuesSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        setProgressBarStdLiving(progress_value);
                        seeStdValuesTextView.setText("Current standard: " + seeStdValuesSeekBar.getProgress());
                        //Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seeStdValuesTextView.setText("Current standard: " + seeStdValuesSeekBar.getProgress());
                        //Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }
        );


    }

    public void viewResults (){
        showResultsMonthsAndCities.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsMonthsAndCities();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("Code :" + results.getString(0) + "\n");
                                buffer.append("City code :" + results.getString(1) + "\n");
                                buffer.append("Month code :" + results.getString(2) + "\n\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );

        showResultsMonths.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsMonths();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("Code :" + results.getString(0) + "\n");
                                buffer.append("Month :" + results.getString(1) + "\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );

        showResultsCities.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsCities();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("Code :" + results.getString(0) + "\n");
                                buffer.append("City :" + results.getString(1) + "\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );

        showResultsConsumerPrices.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsConsumerPrices();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("City code :" + results.getString(0) + "\n");
                                buffer.append("Value :" + results.getString(1) + "\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );

        showResultsAvgStdOfLiving.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsAvgStdOfLiving();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("City code :" + results.getString(0) + "\n");
                                buffer.append("Value :" + results.getString(1) + "\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );


        showResultsWeather.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsWeather();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("CityMonth code :" + results.getString(0) + "\n");
                                buffer.append("Month :" + results.getString(1) + "\n");
                                buffer.append("Value :" + results.getString(2) + "\n\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );

        showResultsSeaTemp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsSeaWeather();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("CityMonth code :" + results.getString(0) + "\n");
                                buffer.append("Month :" + results.getString(1) + "\n");
                                buffer.append("Value :" + results.getString(2) + "\n\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                });

        showResultsSunshine.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsSunshine();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("CityMonth code :" + results.getString(0) + "\n");
                                buffer.append("Month :" + results.getString(1) + "\n");
                                //buffer.append("Value :" + results.getString(2) + "\n\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                });

        showResultsRainfall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResultsRainfall();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("CityMonth code :" + results.getString(0) + "\n");
                                buffer.append("Month :" + results.getString(1) + "\n");
                                buffer.append("Value :" + results.getString(2) + "\n\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public List<Integer> checkSeaAndWeatherTemp(List<Integer> listOfValues, Integer valueToCheck){

        List<Integer> accumulatedPoints = new ArrayList<Integer>();
        Integer tempValue =0;

        for (int i=0; i<listOfValues.size(); i++){

            tempValue = listOfValues.get(i);

            if(java.lang.Math.abs(tempValue-valueToCheck)==0)
                accumulatedPoints.add(5);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==1)
                accumulatedPoints.add(4);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==2)
                accumulatedPoints.add(3);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==3)
                accumulatedPoints.add(2);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==4)
                accumulatedPoints.add(1);
            else
                accumulatedPoints.add(0);
        }

        return accumulatedPoints;
    }

    //Systems for doing the scoring system
    public List<Integer> checkOtherValues(List<Integer> listOfValues, Integer valueToCheck, Integer numberOfPoints){

        List<Integer> accumulatedPoints = new ArrayList<Integer>();
        Integer tempValue  = 0;
        //Integer tempValueForTheSecondPoints = 0;

        for (int i=0; i<listOfValues.size(); i++){

            tempValue = listOfValues.get(i);

            if((java.lang.Math.abs(tempValue-valueToCheck)==0) && (numberOfPoints==4))
                accumulatedPoints.add(5*4);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==1 && (numberOfPoints==4))
                accumulatedPoints.add(4*4);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==2 && (numberOfPoints==4))
                accumulatedPoints.add(3*4);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==3 && (numberOfPoints==4))
                accumulatedPoints.add(2*4);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==4 && (numberOfPoints==4))
                accumulatedPoints.add(1*4);

            else if (java.lang.Math.abs(tempValue-valueToCheck)==0 && (numberOfPoints==3))
                accumulatedPoints.add(5*3);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==1 && (numberOfPoints==3))
                accumulatedPoints.add(4*3);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==2 && (numberOfPoints==3))
                accumulatedPoints.add(3*3);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==3 && (numberOfPoints==3))
                accumulatedPoints.add(2*3);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==4 && (numberOfPoints==3))
                accumulatedPoints.add(1*3);

            else if (java.lang.Math.abs(tempValue-valueToCheck)==0 && (numberOfPoints==2))
                accumulatedPoints.add(5*2);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==1 && (numberOfPoints==2))
                accumulatedPoints.add(4*2);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==2 && (numberOfPoints==2))
                accumulatedPoints.add(3*2);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==3 && (numberOfPoints==2))
                accumulatedPoints.add(2*2);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==4 && (numberOfPoints==2))
                accumulatedPoints.add(1*2);

            else if (java.lang.Math.abs(tempValue-valueToCheck)==0 && (numberOfPoints==1))
                accumulatedPoints.add(5*1);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==1 && (numberOfPoints==1))
                accumulatedPoints.add(4*1);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==2 && (numberOfPoints==1))
                accumulatedPoints.add(3*1);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==3 && (numberOfPoints==1))
                accumulatedPoints.add(2*1);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==4 && (numberOfPoints==1))
                accumulatedPoints.add(1*1);

            else if (java.lang.Math.abs(tempValue-valueToCheck)==0 && (numberOfPoints==0))
                accumulatedPoints.add(0);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==1 && (numberOfPoints==0))
                accumulatedPoints.add(0);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==2 && (numberOfPoints==0))
                accumulatedPoints.add(0);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==3 && (numberOfPoints==0))
                accumulatedPoints.add(0);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==4 && (numberOfPoints==0))
                accumulatedPoints.add(0);

        }

        return accumulatedPoints;
    }

    public HashMap<String, Integer> insertValuesIntoHashMap(List<String> cities,List<Integer> numericValues){

        HashMap<String,Integer> myMap = new HashMap<>();

        for (int i = 0; i < cities.size(); i++) {

            myMap.put(cities.get(i), numericValues.get(i));
        }
        return myMap;
    }

    //Function for doing the scoring system handling
    //public void doTheCalculationsForEachMonth(){}

    public void onClickListenerButton(){

        viewWhichMonthIsSelected    = (TextView)findViewById(R.id.displayMonth);
        selectedMonth               = (RadioGroup)findViewById(R.id.controlTheMonths);
        selectedRainfall            = (RadioGroup)findViewById(R.id.importanceOfYourChoiceRainfall);
        selectedSunshine            = (RadioGroup)findViewById(R.id.importanceOfYourChoiceSunshine);
        selectedPrices              = (RadioGroup)findViewById(R.id.importanceOfYourChoicePrices);
        selectedAvgStd              = (RadioGroup)findViewById(R.id.importanceOfYourChoiceStdValues);


        showResults.setOnClickListener(

                new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {

                        int weatherValueProgress      = getProgressBarValueWeather();
                        int weatherValueSeaProgress   = getProgressBarValueSeaWeather();
                        int sunshineValueProgress     = getProgressBarSunshine();
                        int rainfallValueProgress     = getProgressBarRainfall();
                        int pricesValueProgress       = getProgressBarPrices();
                        int averageStdLiving          = getProgressBarStdLiving();

                        //Check the month that corresponds with his selection
                        int monthNumber               = selectedMonth.getCheckedRadioButtonId();
                        selectedMonthRb               = (RadioButton) findViewById(monthNumber);
                        String monthText              = (String) selectedMonthRb.getText();

                        //Check the tag for the new value of the prices of the cities
                        int selectedPrice             = selectedPrices.getCheckedRadioButtonId();
                        selectedPriceRb               = (RadioButton) findViewById(selectedPrice);
                        int priceOfRadioButton        =  Integer.parseInt(selectedPriceRb.getTag().toString());

                        //Check the tag for the new value of the average standards of living
                        int selectedStdLiving         = selectedAvgStd.getCheckedRadioButtonId();
                        selectedAvgStdRb              = (RadioButton) findViewById(selectedStdLiving);
                        int avgStdRadioButton         = Integer.parseInt(selectedAvgStdRb.getTag().toString());

                        //Check the tag for the new value of the rainfall
                        int selectedRainfallValue     =  selectedRainfall.getCheckedRadioButtonId();
                        selectedRainfallRb            = (RadioButton) findViewById(selectedRainfallValue);
                        int rainfallValueRadioButton  = Integer.parseInt(selectedRainfallRb.getTag().toString());

                        //Check the tag for the new value of sunshine
                        int selectedSunshineValue     = selectedSunshine.getCheckedRadioButtonId();
                        selectedSunshineRb            = (RadioButton) findViewById(selectedSunshineValue);
                        int sunshineValueRadioButton  = Integer.parseInt(selectedSunshineRb.getTag().toString());

                        HashMap<String, Integer> myWeatherMap = new HashMap<>();
                        HashMap<String, Integer> mySeaWeatherMap = new HashMap<>();
                        HashMap<String, Integer> myRainfallMap = new HashMap<>();
                        HashMap<String, Integer> mySunshineMap = new HashMap<>();
                        HashMap<String, Integer> myConsumerPricesMap = new HashMap<>();
                        HashMap<String, Integer> myAvgStdLivingMap = new HashMap<>();

                        List<String> citiesWeather = new ArrayList<String>();
                        List<Integer> weatherValueCities = new ArrayList<Integer>();

                        List<String> citiesSeaWeather = new ArrayList<String>();
                        List<Integer> seaWeatherValueCities = new ArrayList<Integer>();

                        List<String> citiesRainfall = new ArrayList<String>();
                        List<Integer> valuesOfRainfall = new ArrayList<Integer>();

                        List<String> citiesSunshine = new ArrayList<String>();
                        List<Integer> valuesOfSunshine = new ArrayList<Integer>();

                        List<String> citiesConsumerPrices = new ArrayList<String>();
                        List<Integer> valuesConsumerPrices = new ArrayList<Integer>();

                        List<String> citiesAvgStdLiving = new ArrayList<String>();
                        List<Integer> valuesAvgStdLiving = new ArrayList<Integer>();

                        List<Integer> winnerValuesOfWeather          = new ArrayList<Integer>();
                        List<Integer> winnerValuesOfSeaWeather       = new ArrayList<Integer>();
                        List<Integer> winnerValuesOfRainfall         = new ArrayList<Integer>();
                        List<Integer> winnerValuesOfSunshine         = new ArrayList<Integer>();
                        List<Integer> winnerValuesOfConsumerPrices   = new ArrayList<Integer>();
                        List<Integer> winnerValuesOfAvgStdLiving     = new ArrayList<Integer>();

                        switch (monthText) {

                            case "January":

                                Cursor resultsOfWeather        = myDb.showResultsWeatherJanuary();
                                Cursor resultsOfSeaWeather     = myDb.showResultsSeaWeatherJanuary();
                                Cursor resultsOfRainfall       = myDb.showResultsRainfallJanuary();
                                Cursor resultsOfSunshine       = myDb.showResultsSunshineJanuary();
                                Cursor resultsOfConsumerPrices = myDb.showResultsPrices();
                                Cursor resultsOfAvgStdLiving   = myDb.showAverageOfStdLiving();

                                //StringBuffer buffer = new StringBuffer();

                                if (resultsOfWeather.getCount() == 0) {

                                    showMessage("Error: ", "Nothing found.");

                                    return;

                                } else {

                                    //create a function that can verify if the Lists contain data
                                    //This generates the values for the weather and sea weather
                                    while (resultsOfWeather.moveToNext() && resultsOfSeaWeather.moveToNext()) {

                                        citiesWeather.add(resultsOfWeather.getString(0));
                                        System.out.println("Cities weather: " + citiesWeather.toString());

                                        weatherValueCities.add(Integer.parseInt(resultsOfWeather.getString(1)));
                                        System.out.println("Weather values: " + weatherValueCities.toString());

                                        citiesSeaWeather.add(resultsOfSeaWeather.getString(0));
                                        System.out.println(citiesSeaWeather.toString());

                                        seaWeatherValueCities.add(Integer.parseInt(resultsOfSeaWeather.getString(1)));
                                        System.out.println(seaWeatherValueCities.toString());

                                    }

                                    while(resultsOfRainfall.moveToNext() && resultsOfSunshine.moveToNext()){

                                        citiesRainfall.add(resultsOfRainfall.getString(0));
                                        System.out.println("Rainfall city: " + citiesRainfall.toString());

                                        valuesOfRainfall.add(Integer.parseInt(resultsOfRainfall.getString(1)));
                                        System.out.println("Rainfall values: "+valuesOfRainfall.toString());

                                        citiesSunshine.add(resultsOfSunshine.getString(0));
                                        valuesOfSunshine.add(Integer.parseInt(resultsOfSunshine.getString(1)));
                                    }

                                    //this generates the values for the consumers
                                    while(resultsOfConsumerPrices.moveToNext() && resultsOfAvgStdLiving.moveToNext()) {

                                        citiesConsumerPrices.add(resultsOfConsumerPrices.getString(0));
                                        System.out.println(citiesConsumerPrices.toString());

                                        valuesConsumerPrices.add(Integer.parseInt(resultsOfConsumerPrices.getString(1)));
                                        System.out.println(valuesConsumerPrices.toString());

                                        citiesAvgStdLiving.add(resultsOfAvgStdLiving.getString(0));
                                        System.out.println(citiesAvgStdLiving.toString());

                                        valuesAvgStdLiving.add(Integer.parseInt(resultsOfAvgStdLiving.getString(1)));
                                        System.out.println(valuesAvgStdLiving.toString());

                                        //buffer.append("City: "+resultsOfConsumerPrices.getString(0)+"\n");
                                        //buffer.append("Value: "+resultsOfConsumerPrices.getString(1)+"\n");
                                    }

                                    winnerValuesOfWeather        = checkSeaAndWeatherTemp(weatherValueCities,weatherValueProgress );
                                    winnerValuesOfSeaWeather     = checkSeaAndWeatherTemp(seaWeatherValueCities, weatherValueSeaProgress);

                                    System.out.println("This is the rainfall value progress: "+rainfallValueProgress);
                                    System.out.println("This is the value of the radio button: "+rainfallValueRadioButton);

                                    winnerValuesOfRainfall       = checkOtherValues(valuesOfRainfall, rainfallValueProgress, rainfallValueRadioButton);
                                    winnerValuesOfSunshine       = checkOtherValues(valuesOfSunshine, sunshineValueProgress, sunshineValueRadioButton);

                                    winnerValuesOfConsumerPrices = checkOtherValues(valuesConsumerPrices, pricesValueProgress, priceOfRadioButton);
                                    winnerValuesOfAvgStdLiving   = checkOtherValues(valuesAvgStdLiving, averageStdLiving, avgStdRadioButton);

                                    myWeatherMap                 = insertValuesIntoHashMap(citiesWeather, winnerValuesOfWeather);
                                    mySeaWeatherMap              = insertValuesIntoHashMap(citiesSeaWeather, winnerValuesOfSeaWeather);

                                    myRainfallMap                = insertValuesIntoHashMap(citiesRainfall,winnerValuesOfRainfall);
                                    mySunshineMap                = insertValuesIntoHashMap(citiesSunshine,winnerValuesOfSunshine);

                                    myConsumerPricesMap          = insertValuesIntoHashMap(citiesConsumerPrices,winnerValuesOfConsumerPrices);
                                    myAvgStdLivingMap            = insertValuesIntoHashMap(citiesAvgStdLiving,winnerValuesOfAvgStdLiving);

                                    System.out.println("Weather: "+Arrays.asList(myWeatherMap));
                                    System.out.println("Sea weather: " + Arrays.asList(mySeaWeatherMap));
                                    System.out.println("Rainfall values: "+Arrays.asList(myRainfallMap));
                                    System.out.println("Sunshine values: "+Arrays.asList(mySunshineMap));
                                    System.out.println("Prices values: "+Arrays.asList(myConsumerPricesMap));
                                    System.out.println("Avg standard of living values: " + Arrays.asList(myAvgStdLivingMap));
                                    System.out.println(mergeAndAdd(makeAlist(myWeatherMap, mySeaWeatherMap, myRainfallMap, mySunshineMap, myConsumerPricesMap, myAvgStdLivingMap)));
                                }
                        }
                    }
                }
        );
    }

    public HashMap<String, Integer> mergeAndAdd(ArrayList<HashMap<String, Integer>> maplist) {
        HashMap<String, Integer> result = new HashMap<>();
        for (HashMap<String, Integer> map : maplist) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer current = result.get(key);
                result.put(key, current == null ? entry.getValue() : entry.getValue() + current);
            }
        }
        return result;
    }

    public ArrayList<HashMap<String,Integer>> makeAlist(HashMap<String,Integer> Weather, HashMap<String, Integer> SeaWeather, HashMap<String, Integer> Rainfall,
    HashMap<String, Integer> Sunshine, HashMap <String, Integer> Prices, HashMap<String, Integer> AvgStd){

        ArrayList<HashMap<String,Integer>> mergedCategories = new ArrayList<>();

        mergedCategories.add(Weather);
        mergedCategories.add(SeaWeather);
        mergedCategories.add(Rainfall);
        mergedCategories.add(Sunshine);
        mergedCategories.add(Prices);
        mergedCategories.add(AvgStd);

        return mergedCategories;

    }
}
