package edu.qc.seclass.fridgemeal.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("Favorites")
public class Favorites extends ParseObject {
    public static final String KEY_OBJECTID = "objectId";
    public static final String KEY_USER_PK = "UserPK";
    public static final String KEY_RECIPE_FK = "RecipeFK";
    public static final String KEY_CREATED_AT = "createdAt";


    public String getObjectid(){ return getString(KEY_OBJECTID); }

    public ParseObject getUserPk(){return getParseUser(KEY_USER_PK);}

    public Feed getRecipeFK(){return (Feed) getParseObject(KEY_RECIPE_FK);}

    public Date getCreatedAt(){return getDate(KEY_CREATED_AT);}






}
