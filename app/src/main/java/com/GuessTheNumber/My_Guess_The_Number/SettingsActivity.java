package com.GuessTheNumber.My_Guess_The_Number;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        loadSettings();
    }

    private void loadSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "admin");
        int maxTurns = sharedPreferences.getInt("maxTurns", 10);
        int limitFrom = sharedPreferences.getInt("limitFrom", 1);
        int limitTo = sharedPreferences.getInt("limitTo", 10);

        EditText usernameView = (EditText) findViewById(R.id.username);
        EditText maxTurnsView = (EditText) findViewById(R.id.maxTurnsView);
        EditText limitFromView = (EditText) findViewById(R.id.limitFromView);
        EditText limitToView = (EditText) findViewById(R.id.limitToView);

        usernameView.setText(username);
        maxTurnsView.setText(String.valueOf(maxTurns));
        limitFromView.setText(String.valueOf(limitFrom));
        limitToView.setText(String.valueOf(limitTo));
    }

    protected void onSaveClick(View view) {
        EditText usernameView = (EditText) findViewById(R.id.username);
        EditText maxTurnsView = (EditText) findViewById(R.id.maxTurnsView);
        EditText limitFromView = (EditText) findViewById(R.id.limitFromView);
        EditText limitToView = (EditText) findViewById(R.id.limitToView);

        String usernameText = usernameView.getText().toString();
        String maxTurnsText =  maxTurnsView.getText().toString();
        String limitFromText =  limitFromView.getText().toString();
        String limitToText =  limitToView.getText().toString();

        if(!usernameText.trim().isEmpty() && !maxTurnsText.isEmpty() && !limitFromText.isEmpty() && !limitToText.isEmpty()) {
            int maxTurns = Integer.parseInt(maxTurnsText);
            int limitFrom = Integer.parseInt(limitFromText);
            int limitTo = Integer.parseInt(limitToText);

            if(maxTurns > 0 && limitFrom > 0 && limitTo - limitFrom > 0) {
                SharedPreferences.Editor sharedEditor = getSharedPreferences("settings", MODE_PRIVATE).edit();
                sharedEditor.putString("username", usernameText);
                sharedEditor.putInt("maxTurns", maxTurns);
                sharedEditor.putInt("limitFrom", limitFrom);
                sharedEditor.putInt("limitTo", limitTo);
                sharedEditor.apply();
                finish();
            }
        }
    }
}
