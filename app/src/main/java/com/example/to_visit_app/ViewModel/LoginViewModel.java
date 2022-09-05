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
import com.example.to_visit_app.model.JSONParser;
import com.example.to_visit_app.model.LoginModel;
import com.example.to_visit_app.model.VisitModel;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    //TODO: see how to implement login into vm? necessary?

    private final JSONParser cParser = new JSONParser();
    private final RequestQueue mRequestQueue;

    private MutableLiveData<LoginModel> user;
    private final Application application;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        mRequestQueue = Volley.newRequestQueue(application.getApplicationContext());
    }

    public LiveData<LoginModel> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
            //loadVisits();
            //TDOO: throw error message about not logged in
        }
        //Log.i("getVisits -> visits", String.valueOf(user));
        return user;
    }

    private void login() {
        // asynchronous operation to fetch visits
        String mUrl = "https://pacific-spire-62523.herokuapp.com/api/login";
        JsonArrayRequest getVisitRequest = new JsonArrayRequest(Request.Method.POST, mUrl, null, response -> {
            try {
                List<VisitModel> visitData = cParser.getVisits(response);


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
        //createMsgDialog("Network error", "Couldn't download the data").show();
    };

}
