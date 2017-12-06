package com.example.android.bakingrecipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.bakingrecipes.adapters.IngredientsAdapter;
import com.example.android.bakingrecipes.adapters.StepsAdapter;
import com.example.android.bakingrecipes.models.IngredientItem;
import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.models.StepItem;
import com.example.android.bakingrecipes.widget.BakingWidgetProvider;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Noga on 11/26/2017.
 */

public class RecipeListFragment extends Fragment implements StepsAdapter.recipeStepListener{
    private static final String SAVED_LAYOUT_MANAGER = "layout";
    Parcelable layout;
    OnItemClickListener mCallback;
    RecyclerView.LayoutManager layoutManager;
    RecipeItem recipe;
    RecyclerView recyclerView;
    public RecipeListFragment() {
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
    public void onClick(StepItem stepItem,int position,ArrayList steps) {
        mCallback.onItemSelected(stepItem,position,steps);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_recipe, container, false);

        Intent intent = getActivity().getIntent();
        recipe = (RecipeItem) intent.getSerializableExtra(MainActivityFragment.RESULT_KEY);
        ListView listView=rootView.findViewById(R.id.listView);
        IngredientsAdapter adapter=new IngredientsAdapter(getActivity(),recipe.getIngredients());
        listView.setAdapter(adapter);

        recyclerView = rootView.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        StepsAdapter adapterSteps = new StepsAdapter(getActivity(), recipe.getSteps(), this);
        recyclerView.setAdapter(adapterSteps);
        restoreLayoutManagerPosition();
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    private void restoreLayoutManagerPosition() {
        if (layout != null ) {
            recyclerView.getLayoutManager().onRestoreInstanceState(layout);

        }
    }
    public interface OnItemClickListener {
        void onItemSelected(StepItem stepItem,int position,ArrayList steps);
    }

}
