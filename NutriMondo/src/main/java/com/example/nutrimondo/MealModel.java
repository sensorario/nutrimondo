package com.example.nutrimondo;

import org.json.JSONException;
import org.json.JSONObject;

public final class MealModel {
    public final int id;
    public final String datetime;

    private MealModel(int id, String datetime) {
        this.id = id;
        this.datetime = datetime;
    }

    public static MealModel convertFromJSONToMyClass(JSONObject json) throws JSONException {
        if (json == null) {
            return null;
        }
        MealModel result = new MealModel(
                1,
                (String) json.get("datetime")
        );
        return result;
    }

}
