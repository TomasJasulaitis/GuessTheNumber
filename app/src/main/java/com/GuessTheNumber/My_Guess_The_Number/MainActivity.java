package com.GuessTheNumber.My_Guess_The_Number;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);
        aSwitch=findViewById(R.id.switch_toggle);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //if day mode is enabled, set night mode using AppCompatDelegate class.
                    Toast.makeText(MainActivity.this, "Night selected", Toast.LENGTH_SHORT).show();
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    //if night mode is enabled, set day mode using AppCompatDelegate class.
                    Toast.makeText(MainActivity.this, "Day mode selected", Toast.LENGTH_SHORT).show();
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }


    public void onButtonClick(View view) {
        if (view.getId() == R.id.startGameBtn) {
            startGameClick();
        }
        else if (view.getId() == R.id.settingsBtn) {
            openSettings();
        }
        else if (view.getId() == R.id.aboutBtn) {
            openAbout();
        }
    }

    private void startGameClick() {
        Intent intent = new Intent(this, GamePlayActivity.class);
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    private void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
