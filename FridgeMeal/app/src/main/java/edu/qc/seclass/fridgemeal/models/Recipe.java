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
    private String description;

    public Recipe(JSONObject jsonObject) throws JSONException { ;
        this.recipeName = jsonObject.getJSONObject("recipe").getString("label");
        this.cookingTime = "45 mins";
        this.description = "jesus";
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
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

