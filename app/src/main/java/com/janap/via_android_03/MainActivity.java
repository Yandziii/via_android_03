package com.janap.via_android_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

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

        // open second activity after clicking button 'open 2-nd'

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
                
            }
        });

    }

    private void openSecondActivity() {
        Intent intent = new Intent(this,activity_second.class);
        startActivity(intent);
    }
}