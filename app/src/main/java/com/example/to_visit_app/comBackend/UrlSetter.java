package com.example.to_visit_app.comBackend;

public class UrlSetter {
    static String baseUrl = "https://pacific-spire-62523.herokuapp.com";

    public static String getVisitApiUrl() {
        return baseUrl + "/api/visits";
    }

    public static String getLoginApiUrl() {
        return baseUrl + "/api/login";
    }
}
