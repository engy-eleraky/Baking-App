package com.example.android.bakingrecipes;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.models.StepItem;

import java.io.Serializable;

public class DetailsActivity extends AppCompatActivity implements RecipeListFragment.OnItemClickListener {
    public static final String DATA_KEY = "myobj";
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        RecipeItem recipe = (RecipeItem) intent.getSerializableExtra(MainActivityFragment.RESULT_KEY);
        setTitle(recipe.getRecipeName());
        if(findViewById(R.id.layout) != null) {
            mTwoPane = true;
            //next,prev is gone
            //colred step
            if (savedInstanceState == null) {
                RecipeStepFragment fragmentStep = new RecipeStepFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.container, fragmentStep)
                        .commit();
            }
        }
        else{
            mTwoPane=false;
        }
    }
    @Override
    public void onItemSelected(StepItem stepItem) {
        if(mTwoPane){
            RecipeStepFragment fragmentStep = new RecipeStepFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            if(stepItem!=null) {
                fragmentStep.setData(stepItem);
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentStep)
                    .commit();
        }
        else {
            Intent intent = new Intent(this, DetailsRecipeStepActivity.class);
            intent.putExtra(DATA_KEY, stepItem);
            startActivity(intent);
        }
    }
}
