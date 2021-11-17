package com.janap.via_android_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class activity_second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView txt5;
        txt5 = findViewById(R.id.txt5);

        //Shows saved sharedPreferences after clicking bottom 'show preferences'

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getApplicationContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                String input_txt = sp.getString("input_txt", "");

                txt5.setText(input_txt);

                //if sharedPreferences is empty, Toast appears
                if (input_txt.equals("")) {
                    Toast.makeText(activity_second.this, "Nothing found!", Toast.LENGTH_LONG).show();
                }

            }
        });

      //go back to first activity after clicking button 'Back' & remove sharedPref

      Button button4 = findViewById(R.id.button4);
      button4.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              openFirstActivity();
              removeDataFromPref(this);

          }



      });
    }

    private void removeDataFromPref(View.OnClickListener onClickListener) {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        sp.edit().remove("input_txt").commit();
    }



    private void openFirstActivity(){
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }
}