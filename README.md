
# FridgeMeal

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
After a user inputs ingredients they have at their disposal they are shown a list of recipes to meals they could possibly make. The user is also allowed to add their personal recipes to the entire list for others to see and use. 

### App Evaluation

- **Category:** Food & Nutrition
- **Mobile:** Android Phone
- **Story:** Takes user ingredient input and pull recipes based on relevance
- **Market:** Rated E for Everyone
- **Habit:** Daily use for ease of finding recipes to cook
- **Scope:** First we'd by making it easy to find recipes, then expanding to adding personal recipes, suggestions, offering changes to adjust existing recipes, a friend system and a comment system to assist users in adapting recipes to their own taste/style of cooking.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

-[x] User login in to access favorite/personal recipes/results
-[x] User should be able to search recipes by ingredients
-[x] Recipe results should be sorted by relevance
* User should be able to see a more detailed description of each recipe
* User should able to add personal recipes either public/private
* User should be able to see a list of favorite/indivudal recipes

**Optional Nice-to-have Stories**

[In Progress] friend system/profile
* comment system
* rating system
* embedded video instructions

### 2. Screen Archetypes

* Login
   * User login in to access favorite/personal recipes/results
* Home/Recipe feed
   * User should be able to search recipes by ingredients
   * Recipe results should be sorted by relevance
* Recipe Details
    * User should be able to see a more detailed description of each recipe
* Profile screen
   * User should able to add personal recipes either public/private 
* Favorite/Individual Recipe screen
   * User should be able to see a list of favorite/indivudal recipes
### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Profile
* Home
* Stored Recipe Screen

**Flow Navigation** (Screen to Screen)

* Profile
   * Stored Recipe Screen
* Home
   * Recipe details

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>



## Schema 
### Model
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | userName    | String   | User name used to login |
   | userPassword    | String  | User password used to login |
   | userImage | File | User image for profile |
   | objectId      | String   | Unique id for the user posted recipe (default field) |
   | author        | String| Author name or blank if from api |
   | mealImage         | File     | Image for recipe |
   | mealName | String | Title of the meal |
   | directions      | String   | Directions by author or from api|
   | ingredients | String | Ingredients accessible for meal |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |

### Networking
- ### Network Requests
#### Home Feed Screen
    (POST/GET) Query all posts that matches user search criteria
##### (Create/POST/PUT) Create a new recipe post
      Recipe recipe = new Recipe();
        recipe.setRecipeName();
        recipe.setUserID(currentUserId);
        recipe.setRecipeLink(enter apiURL);
        recipe.saveInBackground(new SaveCallback(){
            @Override
            public void done(ParseException e){}
        });
        recipe.setFavorite(Recipe.KEY_PRIVATE, true);

##### (Delete) Delete existing recipe created by current user
    ParseQuery<Recipe> query = ParseQuery.getQuery(Recipe.class);
        query.whereEqualTo(Recipe.KEY_CREATED, USER ID);
        Recipe recipe = query.getPosition(0);
        recipe.deleteInBackground(true);
        
    
#### Recipe Detail
##### (Read/GET) Recipe details
        ParseQuery<Recipe> query = ParseQuery.getQuery(Recipe.class);
        query.whereEqualTo(Recipe.KEY_CREATED, RECIPE ID);
##### (PUT) A new like/favorite 
    ParseQuery<Recipe> query = ParseQuery.getQuery(Recipe.class);
        query.whereEqualTo(Recipe.KEY_CREATED, RECIPE ID);
        Recipe recipe = query.getPosition(0);
        recipe.setFavorite(true) or recipe.put(Recipe.KEY_FAVORITE, true)

#### Profile Screen
      String currentUserId = ParseUser.getCurrentUser().getObjectId();
##### (Update/PUT) Update user profile image
     // String parseFile = link here
        if (parseFile != null){
            ParseUser current = ParseUser.getCurrentUser();
            current.put(PROFILE_PHOTO, parseFile);
        }
        else {
            Log.e("Profile Photo Error", "Photo is null cannot update", new Exception());
        }
##### (Read/GET) Most Recent Recipes 
    ParseQuery<Recipe> query = ParseQuery.getQuery(Recipe.class);
        query.whereLessThan(Recipe.KEY_CREATED_KEY, DATE Object);
        query.whereEqualTo(Recipe.KEY_USERID, CURRENT USERID);
        query.setLimit(20);

##### (Read/GET) Favorite User Recipes
    ParseQuery<Recipe> query = ParseQuery.getQuery(Recipe.class);
        query.whereEqualTo(Recipe.KEY_FAVORITE, TRUE);
        query.whereEqualTo(Recipe.KEY_USERID, CURRENT USERID);
        query.setLimit(20);
##### (Read/GET) Home feed of friends post
     ParseQuery<Recipe> query = ParseQuery.getQuery(Recipe.class);
        query.whereEqualTo(Recipe.KEY_USERID, CURRENT USERID);
        ParseQuery<User> friend = ParseQuery.getQuery(Friend.class);
        query.whereContainedIn(Recipe.Key_UserID, friend);
        query.setLimit(20);


- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
<img src = 'https://github.com/cchromak/FridgeMeal/blob/main/FridgeMeal/TransitionLogin.gif' title='Video Walkthrough' width='' alt='Video Walkthrough'/>
