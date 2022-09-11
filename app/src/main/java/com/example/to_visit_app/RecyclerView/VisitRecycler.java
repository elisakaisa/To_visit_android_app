package com.example.to_visit_app.RecyclerView;

import com.example.to_visit_app.model.VisitList;

public class VisitRecycler extends VisitList {
    private final String mTextWhat, mId;

    public VisitRecycler(String textWhat, String id) {
        mTextWhat = textWhat;
        mId = id;
    }

    public String getTextWhat() { return mTextWhat; }
    public String getTextId() { return mId; }
}
