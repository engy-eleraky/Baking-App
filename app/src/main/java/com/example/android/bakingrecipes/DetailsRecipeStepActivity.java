package com.example.android.bakingrecipes;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class DetailsRecipeStepActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_recipe_step);

        if (savedInstanceState==null) {

            RecipeStepFragment fragmentStep = new RecipeStepFragment();
            fragmentStep.setArguments(getIntent().getExtras());
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragmentStep)
                    .commit();
        }
    }
}
