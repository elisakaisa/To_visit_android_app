package com.example.to_visit_app.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.to_visit_app.comBackend.UrlSetter;
import com.example.to_visit_app.model.JSONParser;
import com.example.to_visit_app.model.VisitModel;

import java.util.List;

public class VisitViewModel extends AndroidViewModel {

    private final JSONParser cParser = new JSONParser();
    private final RequestQueue mRequestQueue;

    private MutableLiveData<List<VisitModel>> visits;
    private final Application application;

    public VisitViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        mRequestQueue = Volley.newRequestQueue(application.getApplicationContext());
    }

    public LiveData<List<VisitModel>> getVisits() {
        if (visits == null) {
            visits = new MutableLiveData<>();
            loadVisits();
        }
        return visits;
    }

    private void loadVisits() {
        // asynchronous operation to fetch visits
        String mUrl = UrlSetter.getVisitApiUrl();
        JsonArrayRequest getVisitRequest = new JsonArrayRequest(Request.Method.GET, mUrl, null, response -> {
            try {
                List<VisitModel> visitData = cParser.getVisits(response);
                visits.setValue(visitData);
            } catch (Exception e){
                Log.i("error whilst parsing", e.toString());
            }
        },
                errorListener);
        getVisitRequest.setTag(this);
        mRequestQueue.add(getVisitRequest);
    }

    private final Response.ErrorListener errorListener = error -> {
        Log.i("Volley error", error.toString());
    };

}
