package com.example.to_visit_app.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.to_visit_app.comBackend.UrlSetter;
import com.example.to_visit_app.model.JSONParser;
import com.example.to_visit_app.model.VisitModel;

public class VisitViewModel extends AndroidViewModel {

    private final JSONParser cParser = new JSONParser();
    private final RequestQueue mRequestQueue;

    public MutableLiveData<VisitModel> visit;

    private String selectedId;

    public VisitViewModel(@NonNull Application application) {
        super(application);
        mRequestQueue = Volley.newRequestQueue(application.getApplicationContext());
    }


    private final Response.ErrorListener errorListener = error -> {
        Log.i("Volley error", error.toString());
    };


    /*----------- GET SELECTED VISITS --------------*/
    public void setSelectId(String id) {
        selectedId = id;
        visit = null; // erase previous selected visit data
    }

    public void loadSelectedVisit() {
        // TODO: figure if it's better to do it somehow this way, instead of searching in visits
        String mUrl = UrlSetter.getSelectedVisitApiUrl(selectedId);
        JsonObjectRequest getSelectedVisitRequest = new JsonObjectRequest(Request.Method.GET, mUrl, null, response -> {
            try {
                VisitModel visitData = cParser.getIndividualVisit(response);
                visit.setValue(visitData);
            } catch (Exception e){
                Log.i("error whilst parsing", e.toString());
            }
        },
                errorListener);
        getSelectedVisitRequest.setTag(this);
        mRequestQueue.add(getSelectedVisitRequest);
    }

    public LiveData<VisitModel> getSelectedVisits() {
        if (visit == null) {
            visit = new MutableLiveData<>();
            loadSelectedVisit();
        }
        return visit;
    }

}
