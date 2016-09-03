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

public class MainActivity extends AppCompatActivity {

    private static SeekBar weatherSeekBar;
    private static TextView weatherTextView;

    //See the water temperature
    private static SeekBar seaWeatherSeekBar;
    private static TextView seaWeatherTextView;

    Button showResultsMonthsAndCities, showResultsMonths, showResultsCities, showResultsConsumerPrices,
            showResultsAvgStdOfLiving, showResultsWeather, showResultsRainfall, showResultsSeaTemp,
            showResultsSunshine, showResults ;

    RadioGroup selectedMonth;
    RadioButton selectedMonthRb;
    TextView viewWhichMonthIsSelected;


    //create the database in the main activity
    DatabaseHelper myDb;

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

    public void seeTheWeatherTemp (){

        weatherSeekBar  = (SeekBar)findViewById(R.id.seekTheWeatherTemp);
        weatherTextView = (TextView)findViewById(R.id.seeTheWeatherTemp);

        //This line helps us to see the initial value of the seekbar once it gets initialized.
        weatherTextView.setText("Current weather: "+weatherSeekBar.getProgress()+
                " / " + weatherSeekBar.getMax() );

        //This piece of code helps us to see the current progress of the seekbar when you start moving the seekbar.
        weatherSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        weatherTextView.setText("Desired weather: " + progress + " / " + weatherSeekBar.getMax());
                        Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        weatherTextView.setText("Current: " + progress_value + " / " + weatherSeekBar.getMax());
                        Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
                    }
                }

        );// End of the piece of code: see the current progress of the seekbar.
    }

    public void seeTheWeatherWater(){

        seaWeatherSeekBar = (SeekBar) findViewById(R.id.seekTheSeaWeatherTemp);
        seaWeatherTextView = (TextView) findViewById(R.id.seeTheSeaWeatherTemp);

        seaWeatherTextView.setText("Current sea weather: " + seaWeatherSeekBar.getProgress() +
                " / " + seaWeatherSeekBar.getMax());

        seaWeatherSeekBar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        seaWeatherTextView.setText("Desired sea weather: " + progress + " / " + seaWeatherSeekBar.getMax());
                        Toast.makeText(MainActivity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(MainActivity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seaWeatherTextView.setText("Current: " + progress_value + " / " + seaWeatherSeekBar.getMax());
                        Toast.makeText(MainActivity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
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
        selectedMonth = (RadioGroup)findViewById(R.id.controlTheMonths);

        showResults.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int month = selectedMonth.getCheckedRadioButtonId();
                        selectedMonthRb = (RadioButton) findViewById(month);
                        String monthText = (String) selectedMonthRb.getText();
                        viewWhichMonthIsSelected.setText(" Button is: " + selectedMonthRb.getText());

                        switch (monthText) {
                            case "January":
                                viewWhichMonthIsSelected.setText(" You selected button 1");
                                break;
                            case "February":
                                viewWhichMonthIsSelected.setText(" You selected button 2");

                        }
                    }
                }
        );


       /* switch (id){

            case -1:
                viewWhichMonthIsSelected.setText(" No button was selected " + id);
                break;
            case R.id.January:
                viewWhichMonthIsSelected.setText(" This is January: " + id);
                break;
            case R.id.February:
                viewWhichMonthIsSelected.setText(" This is February: " + id);
                break;
            case R.id.March:
                viewWhichMonthIsSelected.setText(" This is March: " + id);
                break;
            case 0:
                viewWhichMonthIsSelected.setText(" This is March: " + id);
                break;
            default:
                break;
        }*/



    }

}
