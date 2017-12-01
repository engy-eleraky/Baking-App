package com.example.android.bakingrecipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bakingrecipes.models.StepItem;

import java.io.Serializable;

public class DetailsActivity extends AppCompatActivity implements RecipeListFragment.OnItemClickListener {
    public static final String DATA_KEY = "myobj";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        //check twopane to add step fragment
        //else
        //two pane false
        //on item clicked check if two pane
        //and decide the action

        //add for fragment
    }

    @Override
    public void onItemSelected(StepItem stepItem) {
        Intent intent = new Intent(this, DetailsActivity.class)
                .putExtra(DATA_KEY, (Serializable) stepItem);
        startActivity(intent);
    }
}
