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

public class MainActivity extends AppCompatActivity {


    private static int ProgressBarValueWeather    = 0;
    private static int ProgressBarValueSeaWeather = 0;
    private static int ProgressBarRainfall        = 0;
    private static int ProgressBarSunshine        = 0;

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

    //create buttons, radiogroups, textviews...
    private static Button showResultsMonthsAndCities, showResultsMonths, showResultsCities, showResultsConsumerPrices,
            showResultsAvgStdOfLiving, showResultsWeather, showResultsRainfall, showResultsSeaTemp,
            showResultsSunshine, showResults ;

    private static RadioGroup selectedMonth, selectedRainfall, selectedSunshine, selectedBigMac, selectedAvgStds;
    private static RadioButton selectedMonthRb, selectedRainfallRb, selectedSunshineRb, selectedBigMacRb, selectedAvgStdsRb;
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
                                buffer.append("Value :" + results.getString(2) + "\n\n");
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

    //to modify
    public List<Integer> checkOtherValues(List<Integer> listOfValues, Integer valueToCheck, Integer selectedValue){

        List<Integer> accumulatedPoints = new ArrayList<Integer>();
        Integer tempValue =0;

        for (int i=0; i<listOfValues.size(); i++){

            tempValue = listOfValues.get(i);

            if((java.lang.Math.abs(tempValue-valueToCheck)==0) && selectedValue==4)
                accumulatedPoints.add(5);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==1 && selectedValue==3)
                accumulatedPoints.add(4);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==2 && selectedValue==2)
                accumulatedPoints.add(3);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==3 && selectedValue==1)
                accumulatedPoints.add(2);
            else if (java.lang.Math.abs(tempValue-valueToCheck)==4 && selectedValue==0)
                accumulatedPoints.add(1);
            else
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


    public void onClickListenerButton(){

        viewWhichMonthIsSelected    = (TextView)findViewById(R.id.displayMonth);
        selectedMonth               = (RadioGroup)findViewById(R.id.controlTheMonths);
        //selectedRainfall            = (RadioGroup)findViewById(R.id.controlRainfall);
        //selectedSunshine            = (RadioGroup)findViewById(R.id.controlSunshine);
        selectedBigMac              = (RadioGroup)findViewById(R.id.controlBigMac);
        selectedAvgStds             = (RadioGroup)findViewById(R.id.controlAverageOfLiving);

        showResults.setOnClickListener(

                new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {


                        int weatherValueProgress      = getProgressBarValueWeather();
                        int weatherValueSeaProgress   = getProgressBarValueSeaWeather();
                        int month                     = selectedMonth.getCheckedRadioButtonId();

                        //int rainfall                = selectedRainfall.getCheckedRadioButtonId();
                        //int sunshine                = selectedSunshine.getCheckedRadioButtonId();
                        //int bigMacIndex             = selectedBigMac.getCheckedRadioButtonId();
                        //int avgStdLiving            = selectedAvgStds.getCheckedRadioButtonId();


                        //months
                        selectedMonthRb              = (RadioButton) findViewById(month);
                        String monthText             = (String) selectedMonthRb.getText();
                        //viewWhichMonthIsSelected.setText(" Button is: " + selectedMonthRb.getText());

                        //sunshine

                        //rainfall
                        //RadioGroup radioGroupRainfall     =  (RadioGroup) findViewById(R.id.controlRainfall);
                        //RadioButton checkedButtonRainfall = ((RadioButton)findViewById(radioGroupRainfall.getCheckedRadioButtonId()));
                        //Integer valueOfRainfall           = (Integer) checkedButtonRainfall.getTag();

                        //selectedSunshineRb        = (RadioButton) findViewById(sunshine);
                        //String sunshineText       = (String) selectedSunshineRb.getText();

                        //BigMac
                        //selectedBigMacRb          = (RadioButton) findViewById(bigMacIndex);
                        //String bigMacText         = (String) selectedBigMacRb.getText();

                        //AvgStds
                        //selectedAvgStdsRb         = (RadioButton) findViewById(avgStdLiving);
                        //String avgStdLivingText   = (String) selectedAvgStdsRb.getText();

                        HashMap<String, Integer> myWeatherMap = new HashMap<>();
                        HashMap<String, Integer> mySeaWeatherMap = new HashMap<>();
                        HashMap<String, Integer> myRainfallMap = new HashMap<>();

                        List<String> citiesWeather = new ArrayList<String>();
                        List<Integer> weatherValueCities = new ArrayList<Integer>();

                        List<String> citiesSeaWeather = new ArrayList<String>();
                        List<Integer> seaWeatherValueCities = new ArrayList<Integer>();

                        List<String> citiesRainfall = new ArrayList<String>();
                        List<Integer> rainfallValueCities = new ArrayList<Integer>();

                        List<Integer> winnerValuesOfWeather = new ArrayList<Integer>();
                        List<Integer> winnerValuesOfSeaWeather = new ArrayList<Integer>();
                        List<Integer> winnerValuesOfRainfall = new ArrayList<Integer>();

                        switch (monthText) {

                            case "January":

                                Cursor resultsOfWeather    = myDb.showResultsWeatherJanuary();
                                Cursor resultsOfSeaWeather = myDb.showResultsSeaWeatherJanuary();
                                //Cursor resultsOfRainfall = myDb.showResultsRainfallJanuary();

                                if (resultsOfWeather.getCount() == 0) {

                                    showMessage("Error: ", "Nothing found.");

                                    return;

                                } else {

                                    //create a function that can verify if the Lists contain data
                                    while (resultsOfWeather.moveToNext() && resultsOfSeaWeather.moveToNext()) {

                                        citiesWeather.add(resultsOfWeather.getString(0));
                                        System.out.println(citiesWeather.toString());

                                        weatherValueCities.add(Integer.parseInt(resultsOfWeather.getString(1)));
                                        System.out.println(weatherValueCities.toString());

                                        citiesSeaWeather.add(resultsOfSeaWeather.getString(0));
                                        System.out.println(citiesSeaWeather.toString());

                                        seaWeatherValueCities.add(Integer.parseInt(resultsOfSeaWeather.getString(1)));
                                        System.out.println(seaWeatherValueCities.toString());

                                        /*citiesRainfall.add(resultsOfRainfall.getString(0));
                                        System.out.println(citiesRainfall.toString());

                                        rainfallValueCities.add(Integer.parseInt(resultsOfRainfall.getString(1)));
                                        System.out.println(resultsOfRainfall.toString());*/

                                    }

                                    winnerValuesOfWeather    = checkSeaAndWeatherTemp(weatherValueCities,weatherValueProgress );
                                    winnerValuesOfSeaWeather = checkSeaAndWeatherTemp(seaWeatherValueCities,weatherValueSeaProgress);
                                    //winnerValuesOfRainfall   = checkOtherValues(rainfallValueCities,valueOfRainfall);

                                    myWeatherMap    = insertValuesIntoHashMap(citiesWeather, winnerValuesOfWeather);
                                    mySeaWeatherMap = insertValuesIntoHashMap(citiesSeaWeather,winnerValuesOfSeaWeather);

                                    System.out.println(Arrays.asList(myWeatherMap));
                                    System.out.println(Arrays.asList(mySeaWeatherMap));

                                }
                        }

                    }
                }
        );
    }

}

/*
Part 2, how to convert that radio button to a numeric value. Really, there are many ways to do this. I think my preferred way would be an enum, but you could do
any number of methods for string to int mapping, radio button id to int mapping, etc. You could even use tags. Looks like you have some commented code in there around tags.
I would just set the tags in the xml, then use checkedButton.getTag() to get the value when you are ready to move on to the next page. Simple enough.
* */