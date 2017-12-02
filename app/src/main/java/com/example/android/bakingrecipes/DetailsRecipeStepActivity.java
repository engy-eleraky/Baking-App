package com.example.android.bakingrecipes;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.models.StepItem;

public class DetailsRecipeStepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_recipe_step);

        if (savedInstanceState==null) {
            RecipeStepFragment fragmentStep = new RecipeStepFragment();
            Intent intent = getIntent();
            StepItem step = (StepItem) intent.getSerializableExtra(DetailsActivity.DATA_KEY);
            fragmentStep.setData(step);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragmentStep)
                    .commit();
        }
    }
}
