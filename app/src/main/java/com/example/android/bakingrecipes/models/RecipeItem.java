package com.example.android.bakingrecipes.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Noga on 11/26/2017.
 */

public class RecipeItem {
    @SerializedName("name")
    private String recipeName;
    @SerializedName("id")
    private String recipeId;
    @SerializedName("image")
    private String recipeImage;
    @SerializedName("servings")
    private String servings;
    @SerializedName("ingredients")
    private ArrayList<IngredientItem> ingredients = new ArrayList<IngredientItem>();
    @SerializedName("steps")
    private ArrayList<StepItem> steps = new ArrayList<StepItem>();

    public RecipeItem(String recipeName,String recipeId,String recipeImage,String servings,
                      ArrayList<IngredientItem> ingredients , ArrayList<StepItem> steps ){
        this.recipeName=recipeName;
        this.recipeId=recipeId;
        this.recipeImage=recipeImage;
        this.servings=servings;
        this.ingredients=ingredients;
        this.steps=steps;

    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
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
