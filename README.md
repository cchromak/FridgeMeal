
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

* User login in to access favorite/personal recipes/results
* User should be able to search recipes by ingredients
* Recipe results should be sorted by relevance
* User should be able to see a more detailed description of each recipe
* User should able to add personal recipes either public/private
* User should be able to see a list of favorite/indivudal recipes

**Optional Nice-to-have Stories**

* friend system/profile
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

### Login
![](https://github.com/cchromak/FridgeMeal/blob/main/Images/loginWF.png)

### Home
![](https://github.com/cchromak/FridgeMeal/blob/main/Images/homeWF.png)

### Recipe Detail
![](https://github.com/cchromak/FridgeMeal/blob/main/Images/recipeDetailWF.png)

### Profile
![](https://github.com/cchromak/FridgeMeal/blob/main/Images/profileWF.png)


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
     (Create/POST) Create a new recipe post
     (Delete) Delete existing recipe created by current user 
     (Create/POST) Create a new comment on a recipe
     (Delete) Delete existing comment
#### Create Post Screen
    (Create/POST) Create a new recipe object
    (Create/POST) Set recipe as private/public
#### Profile Screen
    (Read/GET) Query logged in user object
    (Update/PUT) Update user profile image
    (Read/GET) Most Recent Recipes 
    (Read/GET) Favorite User Recipes
    (Read/GET) Home feed of friends post
#### Recipe Details
    (Read/GET) Recipe details
    (Create/POST) A new like/favorite
