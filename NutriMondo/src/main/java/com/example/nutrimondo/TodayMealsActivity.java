package com.example.nutrimondo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by simonegentili on 18/11/13.
 */
public class TodayMealsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl("http://www.yiinotes.com/nutrimondo/web/meals/today");
    }

}