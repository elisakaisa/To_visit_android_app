package com.example.to_visit_app.model;

public class VisitModel {
    private String mWhat, mId, mWhere, mTime, mPriceCategory, mEaseOfOrganisation, mNotes;

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

    public void setTime(String time) { mTime = time; }
    public Time getTime() {
        if (mTime.equals("day")) return Time.day;
        if (mTime.equals("weekend")) return Time.weekend;
        if (mTime.equals("long weekend")) return Time.long_weekend;
        if (mTime.equals("week")) return Time.week;
        else return null;
    }
}
