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

    public VisitModel(
            String what,
            String id,
            String where,
            String time,
            String priceCategory,
            String eoo,
            String notes,
            JSONArray category,
            JSONArray how,
            Boolean done,
            String totWalkDist,
            String actualPrice) throws JSONException {
        this.mWhat = what;
        this.mId = id;
        this.mWhere = where;
        this.mTime = time;
        this.mPriceCategory = priceCategory;
        this.mEaseOfOrganisation = eoo;
        this.mNotes = notes;
        Helpers.parseJSONArray(category, mCategory);
        Helpers.parseJSONArray(how, mHow);
        this.mDone = done;
        this.mTotWalkingDistance = Helpers.convertToFloat(totWalkDist);
        this.mActualPrice = Helpers.convertToFloat(actualPrice);
    }

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

    public String getWhere() { return mWhere; }

    public String getNotes() { return mNotes; }

    public Boolean getDone() { return mDone; }

    public float getTotWalkingDistance() { return mTotWalkingDistance; }

    public float getActualPrice() { return mActualPrice; }

    public ArrayList<String> getCategory() { return mCategory; }
    public String getStringCategory() { return String.valueOf(mCategory);}

    public ArrayList<String> getHow() { return mHow; }
    public String getStringHow() { return String.valueOf(mHow); }

    public String getTime() {
        if (mTime.equals("day")) return String.valueOf(Time.day);
        if (mTime.equals("weekend")) return String.valueOf(Time.weekend);
        if (mTime.equals("long weekend")) return String.valueOf(Time.long_weekend);
        if (mTime.equals("week")) return String.valueOf(Time.week);
        else return null;
    }

    public String getPriceCategory() {
        if (mPriceCategory.equals("free")) return String.valueOf(PriceCategory.free);
        if (mPriceCategory.equals("$")) return String.valueOf(PriceCategory.$);
        if (mPriceCategory.equals("$$")) return String.valueOf(PriceCategory.$$);
        if (mPriceCategory.equals("$$$")) return String.valueOf(PriceCategory.$$$);
        else return null;
    }

    public String getEaseOfOrganisation() {
        if (mEaseOfOrganisation.equals("*")) return String.valueOf(EaseOfOrganisation.EASY);
        if (mEaseOfOrganisation.equals("**")) return String.valueOf(EaseOfOrganisation.MEDIUM);
        if (mEaseOfOrganisation.equals("***")) return String.valueOf(EaseOfOrganisation.HARD);
        else return null;
    }
}
