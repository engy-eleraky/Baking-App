package com.example.android.bakingrecipes.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Noga on 11/27/2017.
 */

public class IngredientItem {
    @SerializedName("quantity")
    private Double quantity;
    @SerializedName("measure")
    private String measure;
    @SerializedName("ingredient")
    private String ingredient;
    public IngredientItem(Double quantity,String measure,String ingredient){
        this.quantity=quantity;
        this.measure=measure;
        this.ingredient=ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
