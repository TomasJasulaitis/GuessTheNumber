package com.GuessTheNumber.My_Guess_The_Number;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GameWinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_win);
        setActivityData();
    }

    private void setActivityData() {
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        int turnCount = bundle.getInt("winningTurn");

        TextView winningTurnView = (TextView) findViewById(R.id.winningTurnView);
        winningTurnView.setText(String.valueOf(turnCount));

    }
}