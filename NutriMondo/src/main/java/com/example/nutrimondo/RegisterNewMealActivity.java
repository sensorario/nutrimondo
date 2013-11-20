package com.example.nutrimondo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new PlaceholderFragment())
                    .commit();
        }

        buildInterface();
    }

    private RegisterNewMealActivity getContext () {
        return this;
    }

    private void buildInterface() {
        ArrayAdapter<CharSequence> adapter = getAliments();

        final InterfaceBuilder interfaceBuilder = getBuilder();
        interfaceBuilder.addTextView("Orario del pasto:");
        interfaceBuilder.addTimePicker();
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
                    nameValuePairs.add(new BasicNameValuePair("foods", "" + interfaceBuilder.getFormItems()));
                    for (int i = interfaceBuilder.getFormItems(); i > 1; i--) {
                        Spinner item = (Spinner) findViewById(i);
                        nameValuePairs.add(new BasicNameValuePair("food_" + i, item.getSelectedItem().toString()));
                    }
                    request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    client.execute(request);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Pasto salvato!");
                alert.setIcon(R.drawable.ic_launcher);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.show();

                finish();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }
    }

}
