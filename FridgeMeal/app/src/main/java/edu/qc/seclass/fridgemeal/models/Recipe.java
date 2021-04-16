package edu.qc.seclass.fridgemeal.models;

public class Recipe {

    private String recipeName;
    private String cookingTime;
    private String description;

    public Recipe(String recipeName, String cookingTime, String description) {
        this.recipeName = recipeName;
        this.cookingTime = cookingTime;
        this.description = description;
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

