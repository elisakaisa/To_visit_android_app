package com.example.to_visit_app.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.to_visit_app.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginViewModel extends AndroidViewModel {
    private final Application application;
    private LoginInterface listener;
    private final RequestQueue mRequestQueue;

    private MutableLiveData<UserModel> user = new MutableLiveData<>();
    private String errorMessage;

    public MutableLiveData<UserModel> getUser() {
        return user;
    }

    public void setUser(JSONObject objUser) {
        UserModel usermodel = new UserModel();
        try {
            usermodel.setUsername(objUser.getString("username"));
            usermodel.setToken(objUser.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        user.postValue(usermodel);
    }
    public void deleteUser() {
        user.postValue(null);
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        mRequestQueue = Volley.newRequestQueue(application.getApplicationContext());
    }

    public interface LoginInterface {
        // used in both register and login
        void onLoggedIn(boolean loggedIn, String errorMessage);
    }

    public void setLoginListener(LoginInterface listener) {
        // Assign the listener implementing events interface that will receive the events
        this.listener = listener;
    }

    public void login(String username, String password) {
        // asynchronous operation to fetch visits
        String mUrl = "https://pacific-spire-62523.herokuapp.com/api/login";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, mUrl, jsonBody, response -> {
            try {
                setUser(response);
                listener.onLoggedIn(true, errorMessage);
            } catch (Exception e) {
                listener.onLoggedIn(false, e.toString());
            }
        }, error -> {
            if (error.toString().contains("AuthFailureError")) {
                listener.onLoggedIn(false, "Authentication error");
            }
        });

        loginRequest.setTag(this);
        mRequestQueue.add(loginRequest);
    }
}
