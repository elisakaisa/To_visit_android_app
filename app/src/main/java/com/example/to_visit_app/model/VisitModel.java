package com.example.to_visit_app.model;

import com.example.to_visit_app.utils.Helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VisitModel {
    private String mWhat, mId, mWhere, mTime, mPriceCategory, mEaseOfOrganisation, mNotes;
    private ArrayList<String> mCategory = new ArrayList<>();
    private ArrayList<String> mHow = new ArrayList<>();
    private JSONObject mTimeOfYear;
    private Boolean mDone;
    private float mTotWalkingDistance, mActualPrice;

    enum Time {
        day, weekend, long_weekend, week
    }
    enum EaseOfOrganisation {
        EASY, MEDIUM, HARD
    }
    enum PriceCategory {
        free, $, $$, $$$
    }

    public void setWhat(String what) { mWhat = what; }
    public String getWhat() { return mWhat; }

    public void setId(String id) { mId = id; }
    public String getId() { return mId; }

    public void setWhere(String where) { mWhere = where; }
    public String getWhere() { return mWhere; }

    public void setNotes(String notes) { mNotes = notes; }
    public String getNotes() { return mNotes; }

    public void setDone(Boolean done) { mDone = done; }
    public Boolean getDone() { return mDone; }

    public void setTotalWalkingDistance(String totalWalkingDistance) {
        if (totalWalkingDistance.equals("null")) mTotWalkingDistance = -1;
        else mTotWalkingDistance = Float.parseFloat(totalWalkingDistance);
    }
    public float getTotWalkingDistance() { return mTotWalkingDistance; }

    public void setActualPrice(String actualPrice) {
        if (actualPrice.equals("null")) mActualPrice = -1;
        else mActualPrice = Float.parseFloat(actualPrice);
    }
    public float getActualPrice() { return mActualPrice; }

    public void setCategory(JSONArray category) throws JSONException{
        Helpers.parseJSONArray(category, mCategory);
    }
    public ArrayList<String> getCategory() { return mCategory; }

    public void setHow(JSONArray how) throws JSONException{
        Helpers.parseJSONArray(how, mHow);
    }
    public ArrayList<String> getHow() { return mHow; }

    public void setTime(String time) { mTime = time; }
    public Time getTime() {
        if (mTime.equals("day")) return Time.day;
        if (mTime.equals("weekend")) return Time.weekend;
        if (mTime.equals("long weekend")) return Time.long_weekend;
        if (mTime.equals("week")) return Time.week;
        else return null;
    }

    public void setPriceCategory(String priceCat) { mPriceCategory = priceCat; }
    public PriceCategory getPriceCategory() {
        if (mPriceCategory.equals("free")) return PriceCategory.free;
        if (mPriceCategory.equals("$")) return PriceCategory.$;
        if (mPriceCategory.equals("$$")) return PriceCategory.$$;
        if (mPriceCategory.equals("$$$")) return PriceCategory.$$$;
        else return null;
    }

    public void setEaseOfOrganisation(String eoo) { mEaseOfOrganisation = eoo; }
    public EaseOfOrganisation getEaseOfOrganisation() {
        if (mEaseOfOrganisation.equals("*")) return EaseOfOrganisation.EASY;
        if (mEaseOfOrganisation.equals("**")) return EaseOfOrganisation.MEDIUM;
        if (mEaseOfOrganisation.equals("***")) return EaseOfOrganisation.HARD;
        else return null;
    }
}
