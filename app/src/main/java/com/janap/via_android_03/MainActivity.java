package com.janap.via_android_03;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText input_txt;
    Button button1;
    SharedPreferences sp;
    String input_txtString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_txt = findViewById(R.id.input_txt);
        button1 = findViewById(R.id.button1);

        sp = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        int themeOpt = sp.getInt("themeOpt", 0);


      // save sharedPreferences after clicking botton 'Save'
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_txtString = input_txt.getText().toString();

                SharedPreferences.Editor editor = sp.edit();

                editor.putString("input_txt", input_txtString);
                editor.commit();


            }
        });

        //Set string array to spinner

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Themes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // set selected from Shared prefs
        spinner.setSelection(themeOpt);
        spinner.setOnItemSelectedListener(this);

        // open second activity after clicking button 'open 2-nd'

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
                
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (pos) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
                break;
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("themeOpt", pos);
        editor.apply();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void openSecondActivity() {
        Intent intent = new Intent(this,activity_second.class);
        startActivity(intent);
    }
}