package com.example.android.bakingrecipes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingrecipes.R;
import com.example.android.bakingrecipes.models.StepItem;

import java.util.ArrayList;

/**
 * Created by Noga on 11/29/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final recipeStepListener listener;
    Context context;
    ArrayList<?>recipeItems;
    public StepsAdapter(Context context, ArrayList<?> recipeItems, StepsAdapter.recipeStepListener listener)
    {
        this.context=context;
        this.recipeItems=recipeItems;
        this.listener=listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_step, parent, false);
                return new StepViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                StepViewHolder vh2 = (StepViewHolder) holder;
               configureStepHolder(vh2,position);
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


    public interface recipeStepListener {
         void onClick(StepItem stepItem);
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

}