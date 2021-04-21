package edu.qc.seclass.fridgemeal.models;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Recipe {

    private String recipeName;
    private String cookingTime;
    private String serving;
    private String calories;
    private String imagePath;

    public Recipe(JSONObject jsonObject) throws JSONException { ;
        this.recipeName = jsonObject.getJSONObject("recipe").getString("label");
        this.cookingTime = jsonObject.getJSONObject("recipe").getString("totalTime");
        this.serving = jsonObject.getJSONObject("recipe").getString("yield");
        this.calories = jsonObject.getJSONObject("recipe").getString("calories");
        this.imagePath = jsonObject.getJSONObject("recipe").getString("image");
    }

    public static List<Recipe> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Recipe> recipes = new LinkedList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            recipes.add(new Recipe(jsonArray.getJSONObject(i)));
        }
        return recipes;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCookingTime() {
        return getStringBeforePeriod(cookingTime) + " Mins";
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getServing() {
        return getStringBeforePeriod(serving) + " Servings";
    }

    public String getCalories() {
        return getStringBeforePeriod(calories) + " Calories";
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getStringBeforePeriod(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c: word.toCharArray()) {
            if(c == '.') {
                return sb.toString();
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

