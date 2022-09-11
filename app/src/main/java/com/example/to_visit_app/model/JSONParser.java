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
            /*-------INIT -------*/
            VisitModel oneVisit = new VisitModel();
            visitData.add(oneVisit);

            JSONObject individualVisit = visitObjects.getJSONObject(i);
            oneVisit.setWhat(individualVisit.getString("what"));
            oneVisit.setId(individualVisit.getString("id"));
        }

        return visitData;
    }

    public List<VisitModel> getIndividualVisit(JSONObject visitObjects) throws JSONException {
        ArrayList <VisitModel> visitData = new ArrayList<>();
        VisitModel oneVisit = new VisitModel();
        visitData.add(oneVisit);

        oneVisit.setWhat(visitObjects.getString("what"));
        oneVisit.setId(visitObjects.getString("id"));
        return visitData;
    }

}
