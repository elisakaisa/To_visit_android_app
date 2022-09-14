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

    public List<VisitModel> getIndividualVisit(JSONObject visitObjects) throws JSONException{
        ArrayList <VisitModel> visitData = new ArrayList<>();
        VisitModel oneVisit = parse(visitObjects);
        visitData.add(oneVisit);
        return visitData;
    }

    public VisitModel parse(JSONObject obj)  throws JSONException{
        VisitModel oneVisit = new VisitModel();
        oneVisit.setWhat(obj.getString("what"));
        oneVisit.setId(obj.getString("id"));
        oneVisit.setWhere(obj.getString("where"));
        oneVisit.setTime(obj.getString("timeLength"));
        oneVisit.setNotes(obj.getString("notes"));
        oneVisit.setCategory(obj.getJSONArray("category"));
        oneVisit.setHow(obj.getJSONArray("how"));
        oneVisit.setPriceCategory(obj.getString("priceCategory"));
        oneVisit.setDone(obj.getBoolean("done"));
        oneVisit.setTotalWalkingDistance(obj.getString("totalWalkingDistance"));
        oneVisit.setActualPrice(obj.getString("actualPrice"));
        oneVisit.setEaseOfOrganisation(obj.getString("easeOfOrganization"));

        return oneVisit;
    }

}
