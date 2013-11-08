package com.example.nutrimondo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class InterfaceBuilder {

    MainActivity _mainActivity;
    FrameLayout _frameLayout;

    int width = 420;
    int leftMargin = 20;
    int top = 0;
    int lineHeightTextView = 40;
    int lineHeightButton = 80;
    int lineHeightSpinner = 80;
    int lineHeightTimePicker = 220;

    int multiSpinnerCount = 1;

    private int getNewMultiSpinnerId() {
        multiSpinnerCount++;
        return multiSpinnerCount;
    }

    InterfaceBuilder(
            MainActivity mainActivity,
            FrameLayout frameLayout
    ) {
        _mainActivity = mainActivity;
        _frameLayout = frameLayout;
    }

    public void addTextView(
            String text
    ) {
        FrameLayout.LayoutParams layoutParams;

        layoutParams = new FrameLayout.LayoutParams(
                width,
                lineHeightTextView
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = getNewTop(
                lineHeightTextView
        );

        TextView textView = new TextView(_mainActivity);
        textView.setText(text);
        textView.setLayoutParams(layoutParams);

        _frameLayout.addView(textView);
    }

    public void addMultiSpinner(ArrayAdapter<CharSequence> adapter) {
        addMultiSpinnerSpinner(getNewMultiSpinnerId(), adapter);
        addMultiSpinnerButton("+");
    }

    private void addMultiSpinnerSpinner(int id, ArrayAdapter<CharSequence> adapter) {
        FrameLayout.LayoutParams layoutParams;
        layoutParams = new FrameLayout.LayoutParams(
                width,
                lineHeightSpinner
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = getNewTop(lineHeightSpinner);

        Spinner spinner = new Spinner(_mainActivity);
        spinner.setId(id);
        spinner.setAdapter(adapter);
        spinner.setLayoutParams(layoutParams);

        _frameLayout.addView(spinner);
    }

    private void addMultiSpinnerButton(String text) {
        FrameLayout.LayoutParams layoutParams;
        layoutParams = new FrameLayout.LayoutParams(
                width,
                lineHeightButton
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = getNewTop(lineHeightButton);

        final Button button = new Button(_mainActivity);
        button.setText(text);
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout.LayoutParams layoutParams;
                layoutParams = new FrameLayout.LayoutParams(
                        button.getWidth(),
                        button.getHeight()
                );
                layoutParams.leftMargin = leftMargin;
                layoutParams.topMargin = button.getTop() + 40;
                button.setLayoutParams(layoutParams);
            }
        });

        _frameLayout.addView(button);
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
