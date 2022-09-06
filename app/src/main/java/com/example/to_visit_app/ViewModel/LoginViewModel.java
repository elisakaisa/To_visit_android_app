package com.example.to_visit_app.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {
    private final Application application;

    private MutableLiveData<String> user = new MutableLiveData<>();

    public MutableLiveData<String> getUser() {
        return user;
    }

    public void setUser(String sUser) {
        user.postValue(sUser);
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    //TODO: add token
    //TODO: remove user

}
