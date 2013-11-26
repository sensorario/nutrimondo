package com.example.nutrimondo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by simonegentili on 18/11/13.
 */
public class TodayMealsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        String[] strings;
        strings = new String[]{
                "Uno",
                "Due",
                "Tre",
                "Quattro",
                "Cinque",
                "Sei",
                "Sette",
                "Otto"
        };
        final TodayMealsActivity context = this;
        final int simple_list_item_1 = android.R.layout.simple_list_item_1;
        ArrayAdapter adapter = new ArrayAdapter<String>(
                context,
                simple_list_item_1,
                strings
        );
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}