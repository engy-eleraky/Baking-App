package com.example.android.bakingrecipes.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Noga on 11/27/2017.
 */

public class IngredientItem implements Serializable{
    //@SerializedName("quantity")
    private String quantity;
   // @SerializedName("measure")
    private String measure;
   // @SerializedName("ingredient")
    private String ingredient;
    public IngredientItem(String quantity,String measure,String ingredient){
        this.quantity=quantity;
        this.measure=measure;
        this.ingredient=ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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
