package com.example.abautista.perfectsummer;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    //See the weather
    private static SeekBar weatherSeekBar;
    private static TextView weatherTextView;

    //See the water temperature
    private static SeekBar seaWeatherSeekBar;
    private static TextView seaWeatherTextView;

    Button showResults;
    Button displayResults;

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

        showResults = (Button)findViewById(R.id.showResults);
        viewResults();

        displayResults = (Button)findViewById(R.id.displayResults);

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

        seaWeatherTextView.setText("Current sea weather: "+seaWeatherSeekBar.getProgress()+
                " / " + seaWeatherSeekBar.getMax() );

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
        showResults.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor results = myDb.showResults();
                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("Code :" + results.getString(0) + "\n");
                                buffer.append("City :" + results.getString(1) + "\n");
                                buffer.append("Month :" + results.getString(2) + "\n\n");
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

    //not working
    public void onClickDisplayResults(View view){

        final Intent startNewActivity = new Intent(this,DisplayResults.class);
        final Bundle bundle = new Bundle();

        displayResults.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor results = myDb.showResults();

                        if (results.getCount() == 0) {
                            showMessage("Error: ", "Nothing found.");
                            return;

                        } else {
                            StringBuffer buffer = new StringBuffer();
                            while (results.moveToNext()) {
                                buffer.append("Id city :" + results.getString(0) + "\n");
                                buffer.append("City :" + results.getString(1) + "\n\n");
                            }
                            bundle.putString("name",buffer.toString());
                            startNewActivity.putExtras(bundle);

                            //startNewActivity.putExtra(startNewActivity.EXTRA_TEXT, buffer.toString());
                            //showMessage("Data", buffer.toString());
                            startActivity(startNewActivity);
                        }
                    }
                }
        );
    }
}
