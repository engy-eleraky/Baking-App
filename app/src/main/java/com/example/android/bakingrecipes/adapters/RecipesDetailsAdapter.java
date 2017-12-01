package com.example.android.bakingrecipes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingrecipes.R;
import com.example.android.bakingrecipes.models.IngredientItem;
import com.example.android.bakingrecipes.models.RecipeItem;
import com.example.android.bakingrecipes.models.StepItem;

import java.util.ArrayList;

/**
 * Created by Noga on 11/29/2017.
 */

public class RecipesDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface recipeStepListener {
         void onClick(StepItem stepItem);
    }
    Context context;
    ArrayList<?>recipeItems;
    private final recipeStepListener listener;
    public RecipesDetailsAdapter(Context context, ArrayList<?> recipeItems, RecipesDetailsAdapter.recipeStepListener listener)
    {
        this.context=context;
        this.recipeItems=recipeItems;
        this.listener=listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_ingredient, parent, false);
                return new IngredientsViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_step, parent, false);
                return new StepViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 1:
                IngredientsViewHolder vh1 = (IngredientsViewHolder) holder;
                configureIngredientsHolder(vh1, position);
                break;
            case 2:
                StepViewHolder vh2 = (StepViewHolder) holder;
                configureStepHolder(vh2,position);
                break;


        }
    }

    private void configureIngredientsHolder(IngredientsViewHolder vh1, int position) {
        IngredientItem ingredientItem = (IngredientItem) recipeItems.get(position);
        vh1.quantity.setText(ingredientItem.getQuantity().toString());
        vh1.measure.setText(ingredientItem.getMeasure());
        vh1.name.setText(ingredientItem.getIngredient());




    }

    private void configureStepHolder(StepViewHolder vh2,int position) {
        StepItem stepItem = (StepItem) recipeItems.get(position);
        vh2.stepShortDescription.setText(stepItem.getShortDescription());


    }

    @Override
    public int getItemCount() {
        if(null==recipeItems)return 0;
        return recipeItems.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position)
    {
        if(recipeItems.get(0)instanceof IngredientItem )
        { return 1;}

        else return 2;
    }

    public class StepViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView stepShortDescription;
        public StepViewHolder(View itemView) {
            super(itemView);
            stepShortDescription=itemView.findViewById(R.id.stepShortDescription);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition= getAdapterPosition();
            StepItem stepItem= (StepItem) recipeItems.get(clickedPosition);
            listener.onClick(stepItem);
        }
    }
    public class IngredientsViewHolder  extends RecyclerView.ViewHolder{
        TextView name;
        TextView quantity;
        TextView measure;
        public IngredientsViewHolder(View itemView) {
            super(itemView);

            quantity=itemView.findViewById(R.id.quantity);
            measure=itemView.findViewById(R.id.measure);
            name=itemView.findViewById(R.id.name);
        }
    }

//    public void updateData(ArrayList<RecipeItem> recips) {
//        recipeItems = recips;
//        notifyDataSetChanged();
//    }
}
