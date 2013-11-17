package com.example.nutrimondo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import android.app.Dialog;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.os.StrictMode;

//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;

//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new PlaceholderFragment())
                    .commit();
        }

//        DialogInterface.OnClickListener onClickThenAddOnion;
//        onClickThenAddOnion = onClickThenAddOnion();

//        AlertDialog.Builder alert = new AlertDialog.Builder(this)
//                .setTitle(new Date().toString())
//                .setIcon(R.drawable.ic_launcher)
//                .setPositiveButton("Ok", onClickThenAddOnion);
//        alert.show();

        buildInterface();
    }

//    private DialogInterface.OnClickListener onClickThenAddOnion() {
//        return new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                HttpClient httpclient;
//                HttpPost httppost;
//                List<NameValuePair> nameValuePairs;
//
//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
//                        .Builder()
//                        .permitAll()
//                        .build();
//                StrictMode.setThreadPolicy(policy);
//
//                httpclient = new DefaultHttpClient();
//                httppost = new HttpPost("http://www.yiinotes.com/nutrimondo/web/aggiungi-cipolla");
//                nameValuePairs = new ArrayList<NameValuePair>(2);
//                nameValuePairs.add(new BasicNameValuePair("parametro", "valore"));
//
//                try {
//                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//
//                HttpResponse response;
//                response = null;
//
//                try {
//                    response = httpclient.execute(httppost);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    InputStream inputStream = response.getEntity().getContent();
//                } catch (IOException e) {
//
//                }
//
//                alertMessage("Aggiunta Cipolla");
//            }
//        };
//    }

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
                    for(int i = interfaceBuilder.getFormItems();i>1;i--) {
                        Spinner item = (Spinner) findViewById(i);
                        nameValuePairs.add(new BasicNameValuePair("food_"+i, item.getSelectedItem().toString()));
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

                finish();
            }
        });
    }

    private void alertMessage(String title) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setIcon(R.drawable.ic_launcher);
        AlertDialog.Builder ok = alert.setPositiveButton("Ok", null);
        alert.show();
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

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
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
