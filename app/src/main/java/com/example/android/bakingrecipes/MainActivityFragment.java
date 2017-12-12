package com.example.android.bakingrecipes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.android.bakingrecipes.adapters.RecipesAdapter;
import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.retrofit.ApiInterface;
import com.example.android.bakingrecipes.retrofit.ApiService;
import java.io.Serializable;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.bakingrecipes.MainActivity.idlingResource;

public class MainActivityFragment extends Fragment implements RecipesAdapter.recipeListener {
    public static final String RESULT_KEY = "myobj";
    private static final String SAVED_LAYOUT_MANAGER = "layout";
    ApiInterface service;
    RecipesAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Parcelable layout;
    ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public void onSaveInstanceState(Bundle outsate) {
        outsate.putParcelable(SAVED_LAYOUT_MANAGER, recyclerView.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outsate);
    }
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null  ){
            layout=savedInstanceState.getParcelable(SAVED_LAYOUT_MANAGER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        service = ApiService.getService();
        progressBar =  rootView.findViewById(R.id.progressBar);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getActivity(), 1);
        }
        else{
            layoutManager = new GridLayoutManager(getActivity(), 2);
        }

        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipesAdapter(getActivity(), new ArrayList<RecipeItem>(0), this);
        recyclerView.setAdapter(adapter);

        loadData();
        return rootView;

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {

            loadData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadData() {
        service.getRecipe().enqueue(new Callback<ArrayList<RecipeItem>>() {
            @Override
            public void onResponse(Call<ArrayList<RecipeItem>> call, Response<ArrayList<RecipeItem>> response) {
                if(response.isSuccessful()) {
                    ArrayList<RecipeItem> recipes = response.body();
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(adapter);
                    adapter.updateData(recipes);
                    restoreLayoutManagerPosition();
                    Log.d("MainActivityFragment", "data loaded");
                }else {
                    int statusCode  = response.code();
                    Log.d("status code: ", String.valueOf(statusCode));

                }
                if (idlingResource != null) {
                    idlingResource.setIdleState(true);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecipeItem>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
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
    private void restoreLayoutManagerPosition() {
        if (layout != null ) {
            recyclerView.getLayoutManager().onRestoreInstanceState(layout);

        }
    }

}
