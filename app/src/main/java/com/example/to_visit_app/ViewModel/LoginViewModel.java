package com.example.to_visit_app.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.to_visit_app.comBackend.UrlSetter;
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

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        mRequestQueue = Volley.newRequestQueue(application.getApplicationContext());
    }

    public interface LoginInterface {
        // used in both register and login
        void onLoggedIn(boolean loggedIn, String errorMessage, UserModel user);
    }

    public void setLoginListener(LoginInterface listener) {
        // Assign the listener implementing events interface that will receive the events
        this.listener = listener;
    }

    /*public void setUser(JSONObject objUser) {
        UserModel usermodel = new UserModel();
        try {
            usermodel.setUsername(objUser.getString("username"));
            usermodel.setToken(objUser.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        user.postValue(usermodel);
    } */
    public void setUser(UserModel objUser) {
        user.postValue(objUser);
    }
    public void deleteUser() {
        UserModel nullUser = new UserModel();
        nullUser.setUsername(null);
        nullUser.setToken(null);
        user.postValue(nullUser);
    }

    public void login(String username, String password) {
        String mUrl = UrlSetter.getLoginApiUrl();

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, mUrl, jsonBody, response -> {
            try {
                UserModel usermodel = new UserModel();
                usermodel.setUsername(response.getString("username"));
                usermodel.setToken(response.getString("token"));
                setUser(usermodel);
                listener.onLoggedIn(true, errorMessage, usermodel);
            } catch (Exception e) {
                listener.onLoggedIn(false, e.toString(), null);
            }
        }, error -> {
            if (error.toString().contains("AuthFailureError")) {
                listener.onLoggedIn(false, "Authentication error", null);
            }
        });

        loginRequest.setTag(this);
        mRequestQueue.add(loginRequest);
    }
}
