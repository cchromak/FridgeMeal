package edu.qc.seclass.fridgemeal.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Recipes")
public class Feed  extends ParseObject{
    public static final String key_description = "description";
    public static final String key_image = "recipeImage";
    public static final String key_user = "user";
    public static final String key_recipe = "recipeName";
    public static final String key_created = "createdAt";

    public String getDescription() {
        return getString(key_description);
    }

    public void setDescription(String description){
        put(key_description, description);
    }

    public String getRecipe() {
        return getString(key_recipe);
    }

    public void setRecipe(String recipeName){
        put(key_recipe, recipeName);
    }

    public ParseFile getRecipeImage() {
        return getParseFile(key_image);
    }

    public ParseFile getUserImage() {
        return getParseFile(key_image);
    }

    public void setRecipeImage(ParseFile parseFile){
        put(key_image, parseFile);
    }

    public void setUserImage(ParseFile parseFile){
        put(key_image, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(key_user);
    }

    public void setUser(ParseUser user){
        put(key_user, user);
    }

}