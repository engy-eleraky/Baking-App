package com.example.android.bakingrecipes;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingrecipes.adapters.RecipesAdapter;
import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.retrofit.ApiInterface;
import com.example.android.bakingrecipes.retrofit.ApiService;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityFragment extends Fragment implements RecipesAdapter.recipeListener {
    ApiInterface service;
    RecipesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public static final String RESULT_KEY = "myobj";
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        service = ApiService.getService();

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getActivity(), 1);
        }else {
            layoutManager = new GridLayoutManager(getActivity(), 3);

        }
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipesAdapter(getActivity(), new ArrayList<RecipeItem>(0), this);
        recyclerView.setAdapter(adapter);

        loadData();

        return rootView;

    }

    public void loadData() {
        service.getRecipe().enqueue(new Callback<ArrayList<RecipeItem>>() {
            @Override
            public void onResponse(Call<ArrayList<RecipeItem>> call, Response<ArrayList<RecipeItem>> response) {
                if(response.isSuccessful()) {
                    ArrayList<RecipeItem> recipes = response.body();
                    adapter.updateData(recipes);
                    Log.d("MainActivityFragment", "data loaded");
                }else {
                    int statusCode  = response.code();
                    Log.d("status code: ", String.valueOf(statusCode));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecipeItem>> call, Throwable t) {
                Log.d("http fail: ", t.getMessage());

            }
        });
    }

    @Override
    public void onClick(Object recipe) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class)
                .putExtra(RESULT_KEY, (Serializable) recipe);
        startActivity(intent);

    }
}
