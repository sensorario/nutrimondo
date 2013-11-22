package com.example.nutrimondo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    public void openTodayHistoryActivity(View view) {
        Intent intent = new Intent(this, TodayMealsActivity.class);
        startActivity(intent);
    }

    public void openWeekHistoryActivity(View view) {
        Intent intent = new Intent(this, WeekMealActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_week:
                startActivity(
                        new Intent(this, WeekMealActivity.class)
                );
                return true;
            case R.id.action_open_today:
                startActivity(
                        new Intent(this, TodayMealsActivity.class)
                );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}