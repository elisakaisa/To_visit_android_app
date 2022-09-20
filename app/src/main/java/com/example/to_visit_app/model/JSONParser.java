package com.example.to_visit_app.model;

import android.app.Application;
import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public List<VisitModel> getVisits(JSONArray visitObjects) throws JSONException {
        ArrayList <VisitModel> visitData = new ArrayList<>();

        for (int i = 0; i < visitObjects.length(); i++) {
            VisitModel oneVisit = parse(visitObjects.getJSONObject(i));
            visitData.add(oneVisit);
        }

        return visitData;
    }

    public VisitModel getIndividualVisit(JSONObject visitObjects) throws JSONException{
        return parse(visitObjects);
    }

    public VisitModel parse(JSONObject obj)  throws JSONException{
        return new VisitModel(
                obj.getString("what"),
                obj.getString("id"),
                obj.getString("where"),
                obj.getString("timeLength"),
                obj.getString("priceCategory"),
                obj.getString("easeOfOrganization"),
                obj.getString("notes"),
                obj.getJSONArray("category"),
                obj.getJSONArray("how"),
                obj.getBoolean("done"),
                obj.getString("totalWalkingDistance"),
                obj.getString("actualPrice"));
    }

}
