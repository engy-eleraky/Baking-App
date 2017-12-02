package com.example.android.bakingrecipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.android.bakingrecipes.adapters.IngredientsAdapter;
import com.example.android.bakingrecipes.adapters.StepsAdapter;
import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.models.StepItem;


/**
 * Created by Noga on 11/26/2017.
 */

public class RecipeListFragment extends Fragment implements StepsAdapter.recipeStepListener{
    OnItemClickListener mCallback;
    RecyclerView.LayoutManager layoutManager;

    public RecipeListFragment() {
    }
     //?????????????
    @Override
    public void onClick(StepItem stepItem) {
        mCallback.onItemSelected(stepItem);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnItemClickListener) getActivity();
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
        final RecipeItem recipe = (RecipeItem) intent.getSerializableExtra(MainActivityFragment.RESULT_KEY);
        ListView listView=rootView.findViewById(R.id.listView);
        IngredientsAdapter adapter=new IngredientsAdapter(getActivity(),recipe.getIngredients());
        listView.setAdapter(adapter);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        StepsAdapter adapterSteps = new StepsAdapter(getActivity(), recipe.getSteps(), this);
        recyclerView.setAdapter(adapterSteps);

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
       mCallback = null;
    }

    public interface OnItemClickListener {
        void onItemSelected(StepItem stepItem);
    }

}
