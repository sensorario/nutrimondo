package com.example.nutrimondo;

/**
 * Created by simonegentili on 26/11/13.
 */
public final class MealModel {
    public final int id;
    public final String datetime;

    private MealModel(int id, String datetime) {
        this.id = id;
        this.datetime = datetime;
    }
}
