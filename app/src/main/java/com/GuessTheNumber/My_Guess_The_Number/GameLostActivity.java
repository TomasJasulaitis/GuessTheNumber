package com.GuessTheNumber.My_Guess_The_Number;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GameLostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lost);
        setActivityData();
    }

    private void setActivityData() {
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        int randomNumber = bundle.getInt("randomNumber");

        TextView winningTurnView = (TextView) findViewById(R.id.randomNumberView);
        winningTurnView.setText(String.valueOf(randomNumber));
    }
}
