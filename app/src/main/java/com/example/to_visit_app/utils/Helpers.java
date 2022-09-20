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

    public static float convertToFloat(String s) {
        float f;
        if (s.equals("null")) f = -1;
        else f = Float.parseFloat(s);
        return f;
    }
}
