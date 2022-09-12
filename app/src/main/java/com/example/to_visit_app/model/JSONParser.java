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
            VisitModel oneVisit = parse(visitObjects.getJSONObject(i), visitData);
            visitData.add(oneVisit);
        }

        return visitData;
    }

    public List<VisitModel> getIndividualVisit(JSONObject visitObjects) throws JSONException {
        ArrayList <VisitModel> visitData = new ArrayList<>();
        VisitModel oneVisit = parse(visitObjects, visitData);
        visitData.add(oneVisit);
        return visitData;
    }

    public VisitModel parse(JSONObject obj, ArrayList<VisitModel> visitData) {
        VisitModel oneVisit = new VisitModel();

        try {
            oneVisit.setWhat(obj.getString("what"));
            oneVisit.setId(obj.getString("id"));
            oneVisit.setWhere(obj.getString("where"));
            oneVisit.setTime(obj.getString("timeLength"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return oneVisit;
    }

}
