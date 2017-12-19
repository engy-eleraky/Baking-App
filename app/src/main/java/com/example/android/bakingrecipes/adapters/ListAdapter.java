package com.example.android.bakingrecipes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.bakingrecipes.R;
import com.example.android.bakingrecipes.models.IngredientItem;
import com.example.android.bakingrecipes.models.StepItem;
import java.util.ArrayList;

/**
 * Created by Noga on 11/29/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final recipeStepListener listener;
    Context context;
    ArrayList<?>stepItems;
    public ListAdapter(Context context, ArrayList<?> stepItems, recipeStepListener listener)
    {
        this.context=context;
        this.stepItems=stepItems;
        this.listener=listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
                return new IngredientsViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_step, parent, false);
                return new StepsViewHolder(view);

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
                StepsViewHolder vh2 = (StepsViewHolder) holder;
                configureStepsHolder(vh2,position);
                break;


        }
    }
    private void configureIngredientsHolder(IngredientsViewHolder vh1, int position) {
        IngredientItem ingredientItem = (IngredientItem) stepItems.get(position);
         vh1.quantity.setText(ingredientItem.getQuantity());
        vh1.measure.setText(ingredientItem.getMeasure());
        vh1.name.setText(ingredientItem.getIngredient());


    }

    private void configureStepsHolder(StepsViewHolder vh2,int position) {
        StepItem stepItem = (StepItem) stepItems.get(position);
        vh2.stepShortDescription.setText(stepItem.getShortDescription());

    }

    @Override
    public int getItemViewType(int position)
    {
        if(stepItems.get(0)instanceof IngredientItem)
        { return 1;}

        else return 2;
    }
    @Override
    public int getItemCount() {
        if(null==stepItems)return 0;
        return stepItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public interface recipeStepListener {
        void onClick(StepItem stepItem, int position, ArrayList steps);
    }

    public class IngredientsViewHolder  extends RecyclerView.ViewHolder {
        TextView quantity;
        TextView measure;
        TextView name;
        public IngredientsViewHolder(View itemView) {
            super(itemView);
            quantity=itemView.findViewById(R.id.quantity);
            measure=itemView.findViewById(R.id.measure);
            name=itemView.findViewById(R.id.name);
        }
    }




    public class StepsViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView stepShortDescription;
        public StepsViewHolder(View itemView) {
            super(itemView);
            stepShortDescription=itemView.findViewById(R.id.stepShortDescription);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickedPosition= getAdapterPosition();
            StepItem stepItem= (StepItem) stepItems.get(clickedPosition);
            listener.onClick(stepItem,clickedPosition,stepItems);
        }
    }

}
