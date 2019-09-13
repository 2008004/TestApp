package com.example.a2008004.testapp;

import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences saveData;
    private String sharedPrefFile = "com.example.a2008004.testapp";
    int score;
    int clickPow;
    int powUpCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView button = findViewById(R.id.button);
        Button backButton = findViewById(R.id.backButton);
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView displayClickPow = findViewById(R.id.displayClickPow);
        final ImageView shopButton = findViewById(R.id.shopButton);
        ImageView settingsButton = findViewById(R.id.settingsButton);
        Button closeSettingsButton = findViewById(R.id.closeSettingsButton);
        Button resetButton = findViewById(R.id.resetButton);
        TextView powCostLabel = findViewById(R.id.powCostLabel);
        Button upClickPowB = findViewById(R.id.upgradeClickPowButon);
        upClickPowB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upClickPow();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetProgress();
            }
        });
        closeSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeSettings();
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeShop();
            }
        });
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopMenu();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                butttonPress();
            }
        });
        saveData = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        try {
            score = saveData.getInt("scoreKey", 0);
            clickPow = saveData.getInt("clickPow", 1);
            displayClickPow.setText("Click Power Lv" + Integer.toString(clickPow));
            scoreLabel.setText(Integer.toString(score));
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }

        powUpCost = (int) Math.pow(clickPow, 2);
        powCostLabel.setText("cost: "+Integer.toString(powUpCost));
    }

    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor saveEditor = saveData.edit();
        saveEditor.putInt("clickPow",clickPow);
        saveEditor.putInt("scoreKey", score);
        Log.i("tag", "saved");
        saveEditor.apply();

    }

    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor saveEditor = saveData.edit();
        saveEditor.putInt("clickPow",clickPow);
        saveEditor.putInt("scoreKey", score);
        Log.i("tag", "saved");
        saveEditor.apply();
    }

    public void butttonPress() {
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        ImageView button = findViewById(R.id.button);
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        button.startAnimation(pulse);
        score += clickPow;
        scoreLabel.setText((Integer.toString(score)));

    }

    public void shopMenu() {
        Animation drawerUp = AnimationUtils.loadAnimation(this, R.anim.drawer_up);
        ViewGroup mainPanel = findViewById(R.id.mainLayout);
        ViewGroup shopPanel = findViewById(R.id.shopMenu);
        mainPanel.setVisibility(View.INVISIBLE);
        shopPanel.setVisibility(View.VISIBLE);
        shopPanel.startAnimation(drawerUp);
    }

    public void closeShop() {
        Animation drawerDown = AnimationUtils.loadAnimation(this, R.anim.drawer_down);
        ViewGroup mainPanel = findViewById(R.id.mainLayout);
        ViewGroup shopPanel = findViewById(R.id.shopMenu);
        mainPanel.setVisibility(View.VISIBLE);
        shopPanel.setVisibility(View.INVISIBLE);
        shopPanel.startAnimation(drawerDown);
    }

    public void openSettings() {
        Animation drawerUp = AnimationUtils.loadAnimation(this, R.anim.drawer_up);
        ViewGroup mainPanel = findViewById(R.id.mainLayout);
        ViewGroup settingsPanel = findViewById(R.id.settingsMenu);
        mainPanel.setVisibility(View.INVISIBLE);
        settingsPanel.setVisibility(View.VISIBLE);
        settingsPanel.startAnimation(drawerUp);
    }

    public void closeSettings() {
        Animation drawerDown = AnimationUtils.loadAnimation(this, R.anim.drawer_down);
        ViewGroup mainPanel = findViewById(R.id.mainLayout);
        ViewGroup settingsPanel = findViewById(R.id.settingsMenu);
        mainPanel.setVisibility(View.VISIBLE);
        settingsPanel.setVisibility(View.INVISIBLE);
        settingsPanel.startAnimation(drawerDown);
    }

    public void resetProgress() {
        score = 0;
        clickPow = 1;
        powUpCost = (int)Math.pow(clickPow,2);
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView powUpLabel = findViewById(R.id.displayClickPow);
        TextView powUpCostLabel = findViewById(R.id.powCostLabel);
        powUpLabel.setText("Click Power Lv"+Integer.toString(clickPow));
        powUpCostLabel.setText("Cost: "+Integer.toString(powUpCost));
        scoreLabel.setText(Integer.toString(score));


        SharedPreferences.Editor saveEditor = saveData.edit();
        saveEditor.clear();
        saveEditor.apply();
    }

    public void upClickPow() {

        if (score >= powUpCost) {
            clickPow++;
            score = score - powUpCost;
            powUpCost = (int) Math.pow(clickPow, 2);
            TextView scoreLabel = findViewById(R.id.scoreLabel);
            TextView displayClickPow = findViewById(R.id.displayClickPow);
            TextView powCostLabel = findViewById(R.id.powCostLabel);
            scoreLabel.setText(Integer.toString(score));
            displayClickPow.setText("Click Power Lv" + Integer.toString(clickPow));
            powCostLabel.setText("Cost: "+Integer.toString(powUpCost));
        }

    }
}











