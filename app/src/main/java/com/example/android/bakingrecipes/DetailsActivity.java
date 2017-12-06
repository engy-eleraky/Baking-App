package com.example.android.bakingrecipes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.bakingrecipes.models.IngredientItem;
import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.models.StepItem;
import com.example.android.bakingrecipes.widget.BakingWidgetProvider;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements RecipeListFragment.OnItemClickListener {
    public static final String DATA_KEY = "myobj";
    public static final String SHARED_PREF_RECIPE_NAME="recipeName";
    public static final String SHARED_PREF_RECIPE_INGREDIENTS="recipeIngredients";
    RecipeItem recipe;
    List<IngredientItem> ingredients ;
    ArrayList<RecipeItem> recipes ;
    int Position ;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
         recipe = (RecipeItem) intent.getSerializableExtra(MainActivityFragment.RESULT_KEY);
        setTitle(recipe.getRecipeName());
        if(findViewById(R.id.layout) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                RecipeStepFragment fragmentStep = new RecipeStepFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentStep.setArguments(null);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.widget_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            String recipeName = recipe.getRecipeName() ;
            Gson gson = new Gson();
            String json = gson.toJson(recipe.getIngredients());
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor;
            editor = preferences.edit();
            editor.putString(SHARED_PREF_RECIPE_NAME,recipeName);
            editor.putString(SHARED_PREF_RECIPE_INGREDIENTS,json);
            editor.apply();
            Toast.makeText(getApplicationContext(),"Added to widget",Toast.LENGTH_SHORT).show();
            sendBroadcast();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onItemSelected(StepItem stepItem) {
        Bundle args = new Bundle();
        args.putSerializable(DATA_KEY, stepItem);
        RecipeStepFragment fragmentStep = new RecipeStepFragment();
        fragmentStep.setArguments(args);
        if(mTwoPane){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentStep)
                    .commit();
        }
        else {
            Intent intent = new Intent(this, DetailsRecipeStepActivity.class);
            intent.putExtras(args);
            startActivity(intent);
       }
    }
    private void sendBroadcast() {

        Intent intent = new Intent(this, BakingWidgetProvider.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE\"");
        sendBroadcast(intent);
    }
}
