package com.example.nutrimondo;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class InterfaceBuilder {

    MainActivity _mainActivity;
    FrameLayout _frameLayout;

    int width = 440;
    int leftMargin = 20;
    int top = 0;
    int lineHeightTextView = 40;
    int lineHeightButton = 80;
    int lineHeightSpinner = 80;
    int lineHeightTimePicker = 220;

    int multiSpinnerCount = 0;
    ArrayAdapter<CharSequence> _multiSpinnerAdapter;

    private int getNewFieldId() {
        multiSpinnerCount++;
        return multiSpinnerCount;
    }

    public int getFormItems() {
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
        addMultiSpinnerSpinner(getNewFieldId(), adapter);
        addMultiSpinnerButton("+");
    }

    private void addMultiSpinnerSpinner(int id, ArrayAdapter<CharSequence> adapter) {
        _multiSpinnerAdapter = adapter;

        FrameLayout.LayoutParams layoutParams;
        layoutParams = new FrameLayout.LayoutParams(
                width,
                lineHeightSpinner
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = getNewTop(lineHeightSpinner);

//        AlertDialog.Builder alert = new AlertDialog.Builder(_mainActivity);
//        alert.setTitle("ID : " + id);
//        alert.setIcon(R.drawable.ic_launcher);
//        AlertDialog.Builder ok = alert.setPositiveButton("Ok", null);
//        alert.show();

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
                top -= lineHeightSpinner;
                top -= lineHeightSpinner;
                addMultiSpinnerSpinner(getNewFieldId(), _multiSpinnerAdapter);
                moveMultiSpinnerButton();
                top += lineHeightSpinner;
                top += lineHeightSpinner;
            }

            private void moveMultiSpinnerButton() {
                //
                FrameLayout.LayoutParams layoutParams;
                layoutParams = new FrameLayout.LayoutParams(
                        button.getWidth(),
                        button.getHeight()
                );
                layoutParams.leftMargin = leftMargin;
                layoutParams.topMargin = button.getTop() + lineHeightSpinner;
                button.setLayoutParams(layoutParams);
                //
                FrameLayout.LayoutParams layoutParamsSubmit;
                layoutParamsSubmit = new FrameLayout.LayoutParams(
                        _mainActivity.findViewById(666).getWidth(),
                        _mainActivity.findViewById(666).getHeight()
                );
                layoutParamsSubmit.leftMargin = leftMargin;
                layoutParamsSubmit.topMargin = _mainActivity.findViewById(666).getTop() + lineHeightSpinner;
                _mainActivity.findViewById(666).setLayoutParams(layoutParamsSubmit);
            }
        });

        _frameLayout.addView(button);
    }

    public void addSubmitButton(View.OnClickListener clickListener) {
        FrameLayout.LayoutParams layoutParams;
        layoutParams = new FrameLayout.LayoutParams(
                width,
                lineHeightButton
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = getNewTop(lineHeightButton);

        final Button button = new Button(_mainActivity);
        button.setText("Salva");
        button.setId(666);
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(clickListener);

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

        TimePicker timePicker = new TimePicker(_mainActivity);
        timePicker.setId(getNewFieldId());
        timePicker.setLayoutParams(layoutParams);

        _frameLayout.addView(timePicker);
    }

    private int getNewTop(int elementHeight) {
        int returnTop = top + 20;
        top = top + elementHeight;
        return returnTop;
    }

}
