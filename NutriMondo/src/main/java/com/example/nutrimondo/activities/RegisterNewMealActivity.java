package com.example.nutrimondo.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.nutrimondo.ui.InterfaceBuilder;
import com.example.nutrimondo.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RegisterNewMealActivity extends Activity {

    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buildInterface();

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

    private RegisterNewMealActivity getContext() {
        return this;
    }

    private void buildInterface() {
        ArrayAdapter<CharSequence> adapter = getAliments();

        final InterfaceBuilder interfaceBuilder = getBuilder();
        interfaceBuilder.addTextView("Alimento:");
        interfaceBuilder.addMultiSpinner(adapter);
        interfaceBuilder.addSubmitButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                        .Builder()
                        .permitAll()
                        .build();
                StrictMode.setThreadPolicy(policy);

                HttpClient client = new DefaultHttpClient();
                HttpPost request = new HttpPost("http://www.yiinotes.com/nutrimondo/web/meals");

                try {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    final int numItemsToSend = interfaceBuilder.getFormItems();
                    nameValuePairs.add(new BasicNameValuePair("foods", "" + numItemsToSend));
                    for (int i = numItemsToSend; i > 0; i--) {
                        Spinner item = (Spinner) findViewById(i);
                        final Object foodItem = item.getSelectedItem();
                        final String foodName = foodItem.toString();
                        nameValuePairs.add(new BasicNameValuePair("food_" + i, foodName));
                    }
                    request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = client.execute(request);

                    StatusLine statusLine = response.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    if (201 == statusCode) {
                        showSavedMealDialog();
                    } else {
                        showUnknowDialog(statusLine);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private void showSavedMealDialog() {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Pasto salvato!");
                alert.setIcon(R.drawable.ic_launcher);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                        Intent intent = new Intent(getContext(), TodayMealsActivity.class);
                        startActivity(intent);
                    }
                });
                alert.show();
            }

            private void showUnknowDialog(StatusLine statusLine) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Response " + statusLine.getStatusCode() + ": " + statusLine.getReasonPhrase());
                alert.setIcon(R.drawable.ic_launcher);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                        Intent intent = new Intent(getContext(), TodayMealsActivity.class);
                        startActivity(intent);
                    }
                });
                alert.show();
            }
        });
    }

    private ArrayAdapter<CharSequence> getAliments() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.aliments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    private InterfaceBuilder getBuilder() {
        InterfaceBuilder interfaceBuilder;
        interfaceBuilder = new InterfaceBuilder(
                this,
                (FrameLayout) findViewById(R.id.activity_main)
        );

        return interfaceBuilder;
    }
}
