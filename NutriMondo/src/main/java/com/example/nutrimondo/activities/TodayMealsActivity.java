package com.example.nutrimondo.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nutrimondo.json.LoadTodayMeals;
import com.example.nutrimondo.R;
import com.example.nutrimondo.models.MealModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

public class TodayMealsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        final ArrayList<MealModel> mealModelArrayList;
        mealModelArrayList = LoadTodayMeals.getAll();

        BaseAdapter adapter;
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mealModelArrayList.size();
            }

            @Override
            public MealModel getItem(int position) {
                return mealModelArrayList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 1;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if (view == null) {
                    view = getLayoutInflater()
                            .inflate(R.layout.today_list_item, null);
                }

                final TextView dateTime;
                final LinearLayout foodList;

                dateTime = (TextView) view.findViewById(R.id.list_item_time);
                foodList = (LinearLayout) view.findViewById(R.id.food_list);

                try {
                    dateTime.setText(mealModelArrayList.get(position).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dateTime.setTypeface(Typeface.DEFAULT_BOLD);
                dateTime.setTextSize(20);

                JSONArray arrayFoods = mealModelArrayList.get(position).food;
                for (int i = 0; i < arrayFoods.length(); i++) {
                    TextView textView = new TextView(getBaseContext());
                    try {
                        final String textItemFood;
                        textItemFood = arrayFoods.get(i).toString();
                        textView.setText(textItemFood);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    foodList.addView(textView);
                }

                return view;
            }
        };

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