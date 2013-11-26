package com.example.nutrimondo;

import org.json.JSONException;
import org.json.JSONObject;

public final class MealModel {
    public final int id;
    public final String datetime;
    public final String food;

    private MealModel(int id, String datetime, String foods) {
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
                json.get("foods").toString()
        );
        return result;
    }

}
