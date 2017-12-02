package com.example.android.bakingrecipes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.bakingrecipes.R;
import com.example.android.bakingrecipes.models.IngredientItem;

import java.util.ArrayList;

/**
 * Created by Noga on 12/1/2017.
 */

public class IngredientsAdapter extends BaseAdapter {
    Context context;
    ArrayList<?> recipes;
    public IngredientsAdapter(Context context,ArrayList<?> recipes){
        this.context=context;
        this.recipes=recipes;
    }
    @Override
    public int getCount() {
        if(null==recipes)return 0;
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)

    {
        if(convertView==null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false);
    }
        TextView quantity=convertView.findViewById(R.id.quantity);
        TextView measure=convertView.findViewById(R.id.measure);
        TextView name=convertView.findViewById(R.id.name);
        IngredientItem ingredientItem = (IngredientItem) recipes.get(position);

        quantity.setText(ingredientItem.getQuantity());
        measure.setText(ingredientItem.getMeasure());
        name.setText("\t"+ingredientItem.getIngredient());
        return convertView;
}
}
