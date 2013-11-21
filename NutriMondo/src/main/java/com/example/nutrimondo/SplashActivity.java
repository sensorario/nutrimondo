package com.example.nutrimondo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by simonegentili on 17/11/13.
 */
public class SplashActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void openMainActivity(View view) {
        Intent intent = new Intent(this, RegisterNewMealActivity.class);
        startActivity(intent);
    }

    public void openHistory(View view) {
        Intent intent = new Intent(this, TodayMealsActivity.class);
        startActivity(intent);
    }

    public void openWeek(View view) {
        Intent intent = new Intent(this, WeekMealActivity.class);
        startActivity(intent);
    }

}