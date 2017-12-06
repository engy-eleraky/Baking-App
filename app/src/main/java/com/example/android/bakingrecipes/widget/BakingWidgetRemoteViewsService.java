package com.example.android.bakingrecipes.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingrecipes.DetailsActivity;
import com.example.android.bakingrecipes.R;
import com.example.android.bakingrecipes.RecipeListFragment;
import com.example.android.bakingrecipes.models.IngredientItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Noga on 12/5/2017.
 */

public class BakingWidgetRemoteViewsService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext());

    }

    class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        Context context;
        //String name;
        String ingredientList;
        private ArrayList<IngredientItem> ingredientItems;
        public MyWidgetRemoteViewsFactory(Context context) {
            this.context = context;

        }
        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            //name=preferences.getString(DetailsActivity.SHARED_PREF_RECIPE_NAME,"");
            String json = preferences.getString(DetailsActivity.SHARED_PREF_RECIPE_INGREDIENTS, "");
            if (!json.equals("")) {
                Gson gson = new Gson();
                ingredientItems = gson.fromJson(json, new TypeToken<ArrayList<IngredientItem>>() {
                }.getType());
            }
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (ingredientItems != null) {
                return ingredientItems.size();
            } else return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_view_item);
            String ingredient=ingredientItems.get(position).getIngredient();
            String measure=ingredientItems.get(position).getMeasure();
            String quantity=ingredientItems.get(position).getQuantity();
            ingredientList=quantity +"\t"+measure;
            views.setTextViewText(R.id.ingredient_quantity,ingredientList);
            views.setTextViewText(R.id.ingredient_name,ingredient);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}