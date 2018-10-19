package com.example.abautista.perfectsummer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayResults extends AppCompatActivity {

    //EXTRA_MESSAGE is the name of the extra value we use to pass the intent
    public static final String EXTRA_MESSAGE = "";
    public EditText setResults = (EditText) findViewById(R.id.setResults);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        Bundle bundle = getIntent().getExtras();
        setResults.setText(bundle.getString("name"));
    }
}
