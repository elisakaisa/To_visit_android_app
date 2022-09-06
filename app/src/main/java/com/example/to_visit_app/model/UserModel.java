package com.example.to_visit_app.model;

public class UserModel {
    String mUsername, mToken;

    public UserModel() {
        mUsername = null;
        mToken = null;
    }

    public void setUsername(String username) { mUsername = username; }
    public void setToken(String token) { mToken = token; }

    public String getUsername() { return mUsername; }
    public String getToken() { return mToken; }
}
