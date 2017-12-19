package com.example.android.bakingrecipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.bakingrecipes.adapters.ListAdapter;
import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.models.StepItem;
import java.util.ArrayList;


/**
 * Created by Noga on 11/26/2017.
 */

public class RecipeListFragment extends Fragment implements ListAdapter.recipeStepListener{
    private static final String SAVED_LAYOUT1_MANAGER = "ingredientsLayout";
    private static final String SAVED_LAYOUT2_MANAGER = "stepsLayout";
    public static RecipeItem recipe;
    RecyclerView recyclerViewIngredients;
    RecyclerView reclerViewSteps;
    ListAdapter ingredientsAdapter;
    ListAdapter stepsAdapter;
    RecyclerView.LayoutManager ingredientsLayout;
    RecyclerView.LayoutManager stepsLayout;
    Parcelable layout1;
    Parcelable layout2;
    OnItemClickListener mCallback;

    public RecipeListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outsate) {
        outsate.putParcelable(SAVED_LAYOUT1_MANAGER, recyclerViewIngredients.getLayoutManager().onSaveInstanceState());
        outsate.putParcelable(SAVED_LAYOUT2_MANAGER, reclerViewSteps.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outsate);
    }
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null  ){
            layout1 = savedInstanceState.getParcelable(SAVED_LAYOUT1_MANAGER);
            layout2=savedInstanceState.getParcelable(SAVED_LAYOUT2_MANAGER);
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

        recyclerViewIngredients = rootView.findViewById(R.id.recyclerView3);
        recyclerViewIngredients.setHasFixedSize(true);
        ingredientsLayout = new LinearLayoutManager(getActivity());
        recyclerViewIngredients.setLayoutManager(ingredientsLayout);
        ingredientsAdapter = new ListAdapter(getActivity(), recipe.getIngredients(), this);
        recyclerViewIngredients.setAdapter(ingredientsAdapter);

        reclerViewSteps = rootView.findViewById(R.id.recyclerView2);
        reclerViewSteps.setHasFixedSize(true);
        stepsLayout = new LinearLayoutManager(getActivity());
        reclerViewSteps.setLayoutManager(stepsLayout);
        stepsAdapter = new ListAdapter(getActivity(), recipe.getSteps(), this);
        reclerViewSteps.setAdapter(stepsAdapter);

        restoreLayoutManagerPosition();

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    private void restoreLayoutManagerPosition() {
        if (layout1 != null &layout2 != null ) {
            recyclerViewIngredients.getLayoutManager().onRestoreInstanceState(layout1);
            reclerViewSteps.getLayoutManager().onRestoreInstanceState(layout2);

        }
    }
    public interface OnItemClickListener {
        void onItemSelected(StepItem stepItem,int position,ArrayList steps);
    }

}
