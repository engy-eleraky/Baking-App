package com.example.android.bakingrecipes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.bakingrecipes.R;
import com.example.android.bakingrecipes.models.RecipeItem;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by Noga on 11/26/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    private final recipeListener listener;
    Context context;
    ArrayList<RecipeItem>recipes;
    public RecipesAdapter(Context context, ArrayList<RecipeItem> recipes, recipeListener listener)
    {
        this.context=context;
        this.recipes=recipes;
        this.listener=listener;
    }

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.ViewHolder holder, int position) {
        RecipeItem recipe = recipes.get(position);

        try{
            Picasso.with(context).load(recipe.getRecipeImage()).into(holder.recipeImage);
        }catch(IllegalArgumentException e){
            Picasso.with(context).load(R.drawable.cake).into(holder.recipeImage);
        }
//        if(recipe.getRecipeImage() !=null && recipes.get(position).getRecipeImage().length()>0){
//            Picasso.with(context).load(recipe.getRecipeImage()).into(holder.recipeImage);
//        }
//        else{
//            Picasso.with(context).load(R.drawable.cake).into(holder.recipeImage);
//        }
        holder.recipeText.setText(recipe.getRecipeName());
        holder.servingsText.setText(recipe.getServings());

    }

    @Override
    public int getItemCount() {
        if (null == recipes) return 0;
        return recipes.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateData(ArrayList<RecipeItem> recipeItems) {
        recipes = recipeItems;
        notifyDataSetChanged();
    }

    public interface recipeListener {
        void onClick(Object recipe);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView recipeText;
        private ImageView recipeImage;
        private TextView servingsText;
        public ViewHolder(View itemView) {
            super(itemView);
            recipeText=itemView.findViewById(R.id.title);
            recipeImage=itemView.findViewById(R.id.image);
            servingsText=itemView.findViewById(R.id.servings);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickedPosition= getAdapterPosition();
            RecipeItem recipe=recipes.get(clickedPosition);
            listener.onClick(recipe);
        }
    }
}
