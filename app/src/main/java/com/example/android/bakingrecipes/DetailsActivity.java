package com.example.android.bakingrecipes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.models.StepItem;
import com.example.android.bakingrecipes.widget.BakingWidgetProvider;
import com.google.gson.Gson;
import java.util.ArrayList;


public class DetailsActivity extends AppCompatActivity implements RecipeListFragment.OnItemClickListener {
    public static final String DATA_KEY = "myobj";
    public static final String CLICK_KEY = "click";
    public static final String ARRAY_KEY = "array";

    public static final String SHARED_PREF_RECIPE_NAME="recipeName";
    public static final String SHARED_PREF_RECIPE_INGREDIENTS="recipeIngredients";
    public static boolean mTwoPane;
    RecipeItem recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        recipe = (RecipeItem) intent.getSerializableExtra(MainActivityFragment.RESULT_KEY);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle(recipe.getRecipeName());
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

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
    public void onItemSelected(StepItem stepItem,int position,ArrayList steps) {
        Bundle args = new Bundle();
        args.putSerializable(DATA_KEY, stepItem);
        args.putInt(CLICK_KEY,position);
        args.putSerializable(ARRAY_KEY,steps);
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
