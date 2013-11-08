package com.example.nutrimondo;

import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

public class InterfaceBuilder {

    MainActivity _mainActivity;
    FrameLayout _frameLayout;

    int width = 420;
    int leftMargin = 20;
    int top = 0;
    int lineHeightTextView = 40;
    int lineHeightSpinner = 80;
    int lineHeightTimePicker = 220;

    InterfaceBuilder(MainActivity mainActivity, FrameLayout frameLayout) {
        _mainActivity = mainActivity;
        _frameLayout = frameLayout;
    }

    public void addTextView(String text) {
        FrameLayout.LayoutParams layoutParams;

        layoutParams = new FrameLayout.LayoutParams(
                width,
                lineHeightTextView
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = getNewTop(lineHeightTextView);

        TextView textView = new TextView(_mainActivity);
        textView.setText(text);
        textView.setLayoutParams(layoutParams);

        _frameLayout.addView(textView);
    }

    public void addSpinner(ArrayAdapter<CharSequence> adapter) {
        FrameLayout.LayoutParams layoutParams;
        layoutParams = new FrameLayout.LayoutParams(
                width,
                lineHeightSpinner
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = getNewTop(lineHeightSpinner);

        Spinner spinner = new Spinner(_mainActivity);
        spinner.setAdapter(adapter);
        spinner.setLayoutParams(layoutParams);

        _frameLayout.addView(spinner);
    }

    public void addTimePicker() {
        FrameLayout.LayoutParams layoutParams;
        layoutParams = new FrameLayout.LayoutParams(
                width,
                lineHeightTimePicker
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = getNewTop(lineHeightTimePicker);

        TimePicker textView = new TimePicker(_mainActivity);
        textView.setLayoutParams(layoutParams);

        _frameLayout.addView(textView);
    }

    private int getNewTop(int elementHeight) {
        int returnTop = top + 20;
        top = top + elementHeight;
        return returnTop;
    }

}
