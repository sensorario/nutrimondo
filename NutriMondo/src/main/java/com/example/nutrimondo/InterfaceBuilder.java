package com.example.nutrimondo;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class InterfaceBuilder {

    private int textViewHeight;
    private int buttonHeight;
    private int leftMargin;
    private int numMultiSpinner;
    private int top;

    private ArrayAdapter<CharSequence> _adapter;
    private FrameLayout _frameLayout;
    private RegisterNewMealActivity _registerNewMealActivity;

    InterfaceBuilder(
            RegisterNewMealActivity registerNewMealActivity,
            FrameLayout frameLayout
    ) {
        _registerNewMealActivity = registerNewMealActivity;
        _frameLayout = frameLayout;
        textViewHeight = 40;
        buttonHeight = 80;
        leftMargin = 20;
        numMultiSpinner = 0;
        top = 0;
    }

    private int getNewFieldId() {
        numMultiSpinner++;
        return numMultiSpinner;
    }

    public int getFormItems() {
        return numMultiSpinner;
    }

    public void addTextView(
            String text
    ) {
        final int width = 440;
        final int height = textViewHeight;
        final int top = getNewTop(height);

        FrameLayout.LayoutParams layoutParams;
        layoutParams = getLayoutParams(width, height, top);

        TextView textView;
        textView = new TextView(_registerNewMealActivity);
        textView.setText(text);
        textView.setLayoutParams(layoutParams);

        _frameLayout.addView(textView);
    }

    public void addMultiSpinner(
            ArrayAdapter<CharSequence> adapter
    ) {
        addMultiSpinnerSpinner(getNewFieldId(), adapter);
        addMultiSpinnerButton("+");
    }

    private void addMultiSpinnerSpinner(
            int id,
            ArrayAdapter<CharSequence> adapter
    ) {
        _adapter = adapter;

        final int width = 440;
        final int height = buttonHeight;
        final int top = getNewTop(height);

        FrameLayout.LayoutParams layoutParams;
        layoutParams = getLayoutParams(width, height, top);

        Spinner spinner;
        spinner = new Spinner(_registerNewMealActivity);
        spinner.setId(id);
        spinner.setAdapter(adapter);
        spinner.setLayoutParams(layoutParams);

        _frameLayout.addView(spinner);
    }

    private void addMultiSpinnerButton(
            String text
    ) {

        final int width = 440;
        final int height = buttonHeight;
        final int top = getNewTop(height);

        FrameLayout.LayoutParams layoutParams;
        layoutParams = getLayoutParams(width, height, top);

        final Button button = new Button(_registerNewMealActivity);
        button.setText(text);
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterfaceBuilder.this.top -= buttonHeight;
                InterfaceBuilder.this.top -= buttonHeight;
                addMultiSpinnerSpinner(getNewFieldId(), _adapter);
                moveMultiSpinnerButton();
                InterfaceBuilder.this.top += buttonHeight;
                InterfaceBuilder.this.top += buttonHeight;
            }

            private void moveMultiSpinnerButton() {

                final int width = button.getWidth();
                final int height = button.getHeight();
                final int top = button.getTop() + height;

                FrameLayout.LayoutParams layoutParams;
                layoutParams = getLayoutParams(width, height, top);

                button.setLayoutParams(layoutParams);

                final View registerNewMealActivity = _registerNewMealActivity.findViewById(666);

                final int widthSubmit = registerNewMealActivity.getWidth();
                final int heightSubmit = registerNewMealActivity.getHeight();
                final int topSubmit = registerNewMealActivity.getTop() + buttonHeight;

                FrameLayout.LayoutParams layoutParamsSubmit;
                layoutParamsSubmit = getLayoutParams(
                        widthSubmit,
                        heightSubmit,
                        topSubmit
                );

                registerNewMealActivity.setLayoutParams(layoutParamsSubmit);
            }
        });

        _frameLayout.addView(button);
    }

    public void addSubmitButton(
            View.OnClickListener clickListener
    ) {

        final int width = 440;
        final int height = buttonHeight;
        final int top = getNewTop(height);

        FrameLayout.LayoutParams layoutParams;
        layoutParams = getLayoutParams(width, height, top);

        final Button button = new Button(_registerNewMealActivity);
        button.setText("Salva");
        button.setId(666);
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(clickListener);

        _frameLayout.addView(button);
    }

    private FrameLayout.LayoutParams getLayoutParams(
            int width,
            int height,
            int top
    ) {
        FrameLayout.LayoutParams layoutParams;
        layoutParams = new FrameLayout.LayoutParams(
                width,
                height
        );
        layoutParams.leftMargin = leftMargin;
        layoutParams.topMargin = top;
        return layoutParams;
    }

    private int getNewTop(
            int elementHeight
    ) {
        int returnTop = top + 20;
        top = top + elementHeight;
        return returnTop;
    }

}
