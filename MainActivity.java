package com.example.maxlena.cs265_week09;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    private Button save;
    private Button advance;
    private TextView text;
    private Button reset;
    private int number;
    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;
    private static final int PREFERENCE_MODE_PRIVATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate all objects
        preferenceSettings = getPreferences(PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSettings.edit();
        advance = (Button) findViewById(R.id.button);
        save = (Button) findViewById(R.id.button2);
        reset = (Button) findViewById(R.id.button3);
        text = (TextView) findViewById(R.id.textView);

        // Check if 0 ot not
        validateNumber();

        // Set buttons
        advance.setOnClickListener(this);
        save.setOnClickListener(this);
        reset.setOnClickListener(this);
    }


    // Validate if it is 0 or not
    private void validateNumber() {

        // Check if number is 0 or not.....
        if (preferenceSettings.getInt("number", number) != 0) {
            text.setText("" + preferenceSettings.getInt("number", number));
        } else {
            text.setText("" + preferenceSettings.getInt("number", number));
        }
    }

    // Advance the count by 1
    private void advanceNumber(){
        // advance
        number++;

        // Display the message
        Toast.makeText(this, "Advance adds 1 to the count", Toast.LENGTH_SHORT).show();

        // Display number on the screen
        text.setText("" + number);
    }


    // save the count using sharedPreferences
    private void saveNumber(int number){

        // Put number into reference object and save it
      preferenceEditor.putInt("number",number);
      preferenceEditor.commit();

        // Display the message
        Toast.makeText(this, "The count was saved...You can EXIT", Toast.LENGTH_SHORT).show();
    }


    // Reset the count to 0
    private void resetNumber(){
        number = 0;
        // Put number into preference object
        preferenceEditor.putInt("number",0);
        // Save the data
        preferenceEditor.commit();
        Toast.makeText(this, "The count for reset...", Toast.LENGTH_SHORT).show();

        text.setText("" + preferenceSettings.getInt("number",number));
    }


    @Override
    public void onClick(View view) {

        // Advance button
        if(view == advance){
           advanceNumber();
        }

        // Save button
        if(view == save){
            saveNumber(number);
        }

        // Reset button
        if(view == reset){
           resetNumber();
        }
    }
}
