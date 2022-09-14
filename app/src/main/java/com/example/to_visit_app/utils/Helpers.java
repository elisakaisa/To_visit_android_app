package com.example.to_visit_app.utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Helpers {

    public static void parseJSONArray(JSONArray json, ArrayList<String> arraylist) throws JSONException {
        for(int i=0; i < json.length(); i++) {
            arraylist.add(String.valueOf(json.get(i)));
        }
    }
}
