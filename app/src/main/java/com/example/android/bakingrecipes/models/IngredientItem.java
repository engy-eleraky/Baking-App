package com.example.android.bakingrecipes.models;

import java.io.Serializable;

/**
 * Created by Noga on 11/27/2017.
 */

public class IngredientItem implements Serializable{
    private String quantity;
    private String measure;
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
