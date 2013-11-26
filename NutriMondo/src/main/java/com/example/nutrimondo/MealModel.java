package com.example.nutrimondo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class MealModel {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("E dd MMMM yyyy");
    public final int id;
    private final String datetime;
    public final JSONArray food;

    private MealModel(int id, String datetime, JSONArray foods) {
        this.id = id;
        this.datetime = datetime;
        this.food = foods;
    }

    public static MealModel convertFromJSONToMyClass(JSONObject json) throws JSONException {
        if (json == null) {
            return null;
        }
        MealModel result = new MealModel(
                1,
                (String) json.get("datetime"),
                (JSONArray) json.get("foods")
        );
        return result;
    }

    public String getTime() throws ParseException {
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ITALIAN);
        Date date = dateParser.parse(datetime);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
        return dateFormatter.format(date);
    }
}
