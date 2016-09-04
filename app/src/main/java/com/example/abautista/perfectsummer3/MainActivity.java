package com.example.abautista.perfectsummer3;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.content.Intent;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {


    private static int ProgressBarValueWeather    = 0;
    private static int ProgressBarValueSeaWeather = 0;

    private static SeekBar weatherSeekBar;
    private static TextView weatherTextView;

    //See the water temperature
    private static SeekBar seaWeatherSeekBar;
    private static TextView seaWeatherTextView;

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
        seeTheWeatherWater();

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

    public int getProgressBarValueSeaWeather(){
        return ProgressBarValueSeaWeather;
    }

    public void setProgressBarValueSeaWeather(int progressBarValueSeaWeather){
        this.ProgressBarValueSeaWeather = progressBarValueSeaWeather;
    }

    public int getProgressBarValueWeather(){
        return ProgressBarValueWeather;
    }

    public void setProgressBarValueWeather(int progressBarValueWeather){
        this.ProgressBarValueWeather = progressBarValueWeather;
    }

    public void seeTheWeatherTemp (){

        weatherSeekBar  = (SeekBar)findViewById(R.id.seekTheWeatherTemp);
        weatherTextView = (TextView)findViewById(R.id.seeTheWeatherTemp);
        //This line helps us to see the initial value of the seekbar once it gets initialized.
        weatherTextView.setText("Current weather: "+weatherSeekBar.getProgress()+
                " / " + weatherSeekBar.getMax() +"ºC" );

        //This piece of code helps us to see the current progress of the seekbar when you start moving the seekbar.
        weatherSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        setProgressBarValueWeather(progress_value);
                        weatherTextView.setText("Desired weather: " + progress + " / " + weatherSeekBar.getMax() +"ºC");
                        //Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        weatherTextView.setText("Current: " + progress_value + " / " + weatherSeekBar.getMax() +"ºC");
                        //Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }

        );// End of the piece of code: see the current progress of the seekbar.
    }

    public void seeTheWeatherWater(){

        seaWeatherSeekBar = (SeekBar) findViewById(R.id.seekTheSeaWeatherTemp);
        seaWeatherTextView = (TextView) findViewById(R.id.seeTheSeaWeatherTemp);

        seaWeatherTextView.setText("Current sea weather: " + seaWeatherSeekBar.getProgress() +
                " / " + seaWeatherSeekBar.getMax() +"ºC");

        seaWeatherSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        setProgressBarValueSeaWeather(progress_value);
                        seaWeatherTextView.setText("Desired sea weather: " + progress + " / " + seaWeatherSeekBar.getMax() +"ºC");
                        //Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seaWeatherTextView.setText("Current: " + progress_value + " / " + seaWeatherSeekBar.getMax() +"ºC");
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

    public void onClickListenerButton(){

        viewWhichMonthIsSelected    = (TextView)findViewById(R.id.displayMonth);
        selectedMonth               = (RadioGroup)findViewById(R.id.controlTheMonths);
        selectedRainfall            = (RadioGroup)findViewById(R.id.controlRainfall);
        selectedSunshine            = (RadioGroup)findViewById(R.id.controlSunshine);
        selectedBigMac              = (RadioGroup)findViewById(R.id.controlBigMac);
        selectedAvgStds             = (RadioGroup)findViewById(R.id.controlAverageOfLiving);

        showResults.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int weatherValueProgress    = getProgressBarValueWeather();
                        int weatherValueSeaProgress = getProgressBarValueSeaWeather();
                        int month                   = selectedMonth.getCheckedRadioButtonId();
                        int rainfall                = selectedRainfall.getCheckedRadioButtonId();
                        int sunshine                = selectedSunshine.getCheckedRadioButtonId();
                        int bigMacIndex             = selectedBigMac.getCheckedRadioButtonId();
                        int avgStdLiving            = selectedAvgStds.getCheckedRadioButtonId();

                        //months
                        selectedMonthRb           = (RadioButton) findViewById(month);
                        String monthText          = (String) selectedMonthRb.getText();
                        //viewWhichMonthIsSelected.setText(" Button is: " + selectedMonthRb.getText());

                        //rainfall
                        selectedRainfallRb        = (RadioButton) findViewById(rainfall);
                        String [] rainfallText    = selectedRainfallRb.getText().toString().split("\\/");

                        //sunshine
                        selectedSunshineRb        = (RadioButton) findViewById(sunshine);
                        String sunshineText       = (String) selectedSunshineRb.getText();

                        //BigMac
                        selectedBigMacRb          = (RadioButton) findViewById(bigMacIndex);
                        String bigMacText         = (String) selectedBigMacRb.getText();

                        //AvgStds
                        selectedAvgStdsRb         = (RadioButton) findViewById(avgStdLiving);
                        String avgStdLivingText   = (String) selectedAvgStdsRb.getText();


                        switch (monthText) {
                            case "January":
                                viewWhichMonthIsSelected.setText("No match with your existing data: "+"\nWeather value: " + weatherValueProgress + "\n Sea value: " + weatherValueSeaProgress +
                                        "\n Rain values :" + rainfallText[0] + "\n Sunshine values: " + sunshineText
                                        + "\n Big Mac Index: " + bigMacText + "\n Avg Standard Living: " + avgStdLivingText);

                                if (weatherValueProgress==15 && weatherValueSeaProgress==15 && rainfallText[0]=="Regular rain" && sunshineText =="10-12h"
                                        && bigMacText=="$3.5 - 4.0" && avgStdLivingText == "0.850+" ){
                                    viewWhichMonthIsSelected.setText("Tu ciudad ideal es Mallorca");
                                }else{
                                    viewWhichMonthIsSelected.setText("No match with your existing data: "+"\nWeather value: " + weatherValueProgress + "\n Sea value: " + weatherValueSeaProgress +
                                            "\n Rain values :" + rainfallText[0] + "\n Sunshine values: " + sunshineText
                                            + "\n Big Mac Index: " + bigMacText + "\n Avg Standard Living: " + avgStdLivingText);
                                }

                               /*
                                Cursor results = myDb.showResultsTempJanuary(weatherValueProgress);

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
                                    viewWhichMonthIsSelected.setText(" Values: " +buffer.toString());
                                }*/

                                break;
                            case "February":
                                viewWhichMonthIsSelected.setText(" Sea value: " + weatherValueSeaProgress);
                        }
                    }
                }
        );
    }

}
