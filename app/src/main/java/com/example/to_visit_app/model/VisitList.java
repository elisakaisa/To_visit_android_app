package com.example.to_visit_app.model;

import java.util.ArrayList;
import java.util.List;

public class VisitList {

    private static List<VisitModel> theVisit;

    public VisitList() {}

    public static List<VisitModel> getInstance() {
        if (theVisit == null) theVisit = new ArrayList<>();
        return theVisit;
    }
}
