package com.example.nutrimondo.json;

import android.os.StrictMode;

import com.example.nutrimondo.models.MealModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by simonegentili on 26/11/13.
 */
public class LoadTodayMeals {
    public static ArrayList<MealModel> getAll() {
        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://www.yiinotes.com/nutrimondo/web/meals/today");
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            StringBuilder builder = new StringBuilder();
            String result;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("Date") && !line.startsWith("HTTP") && !line.startsWith("Cache-Control") && !line.startsWith("Content-Type") && !line.trim().toString().isEmpty()) {
                    builder.append(line);
                }
            }
            content.close();
            result = builder.toString();
            JSONObject json = new JSONObject(result);
            JSONArray items = json.getJSONArray("items");

            final int itemCount = json.getInt("itemCount");

            ArrayList<MealModel> arrayList;
            arrayList = new ArrayList<MealModel>();

            for (int i = 0; i < itemCount; i++) {
                arrayList.add(MealModel.convertFromJSONToMyClass((JSONObject) items.get(i)));
            }
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
