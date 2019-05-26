package com.GuessTheNumber.My_Guess_The_Number;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePlayActivity extends Activity {
    private int limitFrom;
    private int limitTo;
    private int randomNumber;
    private int currentTurn;
    private int maxTurns;
    private String hint;

    ArrayList<HistoryEntry> mContents;
    HistoryAdapter mAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        loadData();
    }

    private void loadData() {
        this.currentTurn = 1;
        loadGameData();
        generateRandomNumber();
        setListView();
        updateFields();
    }

    private void loadGameData() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        this.maxTurns = sharedPreferences.getInt("maxTurns", 10);
        this.limitFrom = sharedPreferences.getInt("limitFrom", 1);
        this.limitTo = sharedPreferences.getInt("limitTo", 10);
        this.hint = "Nėra!";
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(limitTo-limitFrom) + limitFrom;
    }

    private void setListView() {
        HistoryDatabaseHandler dbhandler = new HistoryDatabaseHandler(this);
        mContents = dbhandler.getAllEntries();

        mAdapter = new HistoryAdapter(mContents, this);
        mListView = findViewById(R.id.guessListView);
        mListView.setAdapter(mAdapter);
    }

    private void updateFields() {
        String descriptionText = String.format("Guess the number from %d to %d", limitFrom, limitTo);
        String turnText = String.format("Turn: %d",currentTurn);
        String leftTurns = String.format("Guesses left: %d", maxTurns - currentTurn + 1);
        String hint = this.hint;

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        TextView turnTextView = findViewById(R.id.turnTextView);
        TextView leftTurnsView = findViewById(R.id.leftTurnsView);
        TextView hintView = findViewById(R.id.hintView);
        EditText numberInput = findViewById(R.id.numberInput);

        descriptionTextView.setText(descriptionText);
        turnTextView.setText(turnText);
        leftTurnsView.setText(leftTurns);
        leftTurnsView.setText(leftTurns);
        hintView.setText(hint);
        numberInput.setText("");
    }

    protected void onGuessButtonClick(View view) {
        EditText inputField = findViewById(R.id.numberInput);
        String inputString = inputField.getText().toString();

        if(!inputString.isEmpty()) {
            int enteredNumber = Integer.parseInt(inputString);

            if (enteredNumber == randomNumber) {
                loadGameWinActivity();
            } else if (this.currentTurn == this.maxTurns) {
                loadGameLostActivity();
            }

            this.currentTurn++;
            setHint(enteredNumber);
            updateFields();
        }
    }

    private void setHint(int enteredNumber) {
        if (randomNumber > enteredNumber) {
            this.hint = "Higher!";
        } else {
            this.hint = "Smaller!";
        }
    }

    private void loadGameWinActivity() {
        prepareContent("Win", currentTurn);
        Bundle bundle = new Bundle();
        bundle.putInt("winningTurn", currentTurn);
        Intent intent = new Intent(this, GameWinActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void loadGameLostActivity() {
        prepareContent("Lose", currentTurn);
        Bundle bundle = new Bundle();
        bundle.putInt("randomNumber", randomNumber);
        Intent intent = new Intent(this, GameLostActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void prepareContent(String type, int currentTurn) {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "user");

        HistoryDatabaseHandler dbhandler = new HistoryDatabaseHandler(this);
        dbhandler.addEntry(new HistoryEntry(0, type, username, currentTurn));
    }
}
