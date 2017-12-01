package com.example.android.bakingrecipes.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Noga on 11/26/2017.
 */

public class RecipeItem implements Serializable{
    //@SerializedName("name")
    private String name;
   // @SerializedName("id")
    private String id;
   // @SerializedName("image")
    private String image;
    //@SerializedName("servings")
    private String servings;
   // @SerializedName("ingredients")
    private ArrayList<IngredientItem> ingredients = new ArrayList<IngredientItem>();
    //@SerializedName("steps")
    private ArrayList<StepItem> steps = new ArrayList<StepItem>();

    public RecipeItem(String name,String id,String image,String servings,
                      ArrayList<IngredientItem> ingredients , ArrayList<StepItem> steps ){
        this.name=name;
        this.id=id;
        this.image=image;
        this.servings=servings;
        this.ingredients=ingredients;
        this.steps=steps;

    }

    public String getRecipeName() {
        return name;
    }

    public void setRecipeName(String recipeName) {
        this.name = name;
    }

    public String getRecipeId() {
        return id;
    }

    public void setRecipeId(String recipeId) {
        this.id = id;
    }

    public String getRecipeImage() {
        return image;
    }

    public void setRecipeImage(String recipeImage) {
        this.image = image;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public ArrayList<IngredientItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<IngredientItem> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<StepItem> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepItem> steps) {
        this.steps = steps;
    }
}
