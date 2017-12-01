package com.example.android.bakingrecipes.retrofit;

import com.example.android.bakingrecipes.models.RecipeItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Noga on 11/27/2017.
 */

public interface ApiInterface {
    @GET("baking.json")
    Call<ArrayList<RecipeItem>> getRecipe();
}
