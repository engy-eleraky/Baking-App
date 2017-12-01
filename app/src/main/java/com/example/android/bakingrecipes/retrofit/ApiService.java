package com.example.android.bakingrecipes.retrofit;

/**
 * Created by Noga on 11/29/2017.
 */

public class ApiService {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    public static ApiInterface getService() {
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
