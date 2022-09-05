package com.example.to_visit_app.RecyclerView;

import com.example.to_visit_app.model.VisitList;

public class VisitRecycler extends VisitList {
    private final String mTextWhat;

    public VisitRecycler(String textWhat) { mTextWhat = textWhat;}

    public String getTextWhat() { return mTextWhat; }
}
