package com.example.nutrimondo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by simonegentili on 20/11/13.
 */
public class WeekMealActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        WebView webView = (WebView) findViewById(R.id.web_week_view);
        webView.loadUrl("http://www.yiinotes.com/nutrimondo/web/meals/week");
    }
}