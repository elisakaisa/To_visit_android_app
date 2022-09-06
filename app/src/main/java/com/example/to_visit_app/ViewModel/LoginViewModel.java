package com.example.to_visit_app.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.to_visit_app.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginViewModel extends AndroidViewModel {
    private final Application application;

    private MutableLiveData<UserModel> user = new MutableLiveData<>();

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
    }
    //TODO: add token
    //TODO: remove user

}
