package edu.qc.seclass.fridgemeal.models;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Recipe {

    private String recipeName;
    private int cookingTime;
    private int serving;
    private int calories;
    private String imagePath;
    private String directionsPath;
    private JSONArray ingredientArray;

    public Recipe(JSONObject jsonObject) throws JSONException {
        this.recipeName = jsonObject.getJSONObject("recipe").getString("label");
        this.cookingTime = jsonObject.getJSONObject("recipe").getInt("totalTime");
        this.serving = jsonObject.getJSONObject("recipe").getInt("yield");
        this.calories = jsonObject.getJSONObject("recipe").getInt("calories");
        this.imagePath = jsonObject.getJSONObject("recipe").getString("image");
        this.directionsPath = jsonObject.getJSONObject("recipe").getString("url");
        this.ingredientArray = jsonObject.getJSONObject("recipe").getJSONArray("ingredientLines");
    }

    public static List<Recipe> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Recipe> recipes = new LinkedList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            recipes.add(new Recipe(jsonArray.getJSONObject(i)));
        }
        return recipes;
    }

    public String getDirectionsPath() {
        return directionsPath;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getCookingTime() {
        if (cookingTime != 0) return Integer.toString(cookingTime) + " Mins";
        return "";
    }

    public String getServing() {
        return Integer.toString(serving) + " Servings";
    }

    public String getCalories() {
        return Integer.toString(calories) + " Calories";
    }

    public String getImagePath() {
        return imagePath;
    }

    public JSONArray getIngredientArray() {
        return ingredientArray;
    }

}

