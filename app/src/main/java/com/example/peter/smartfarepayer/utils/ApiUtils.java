package com.example.peter.smartfarepayer.utils;

import com.example.peter.smartfarepayer.retrofit.ApiService;
import com.example.peter.smartfarepayer.retrofit.RetrofitClient;

public class ApiUtils {
    private ApiUtils() {}

    public static ApiService getApiService(){
        return RetrofitClient.getClient().create(ApiService.class);
    }
}
