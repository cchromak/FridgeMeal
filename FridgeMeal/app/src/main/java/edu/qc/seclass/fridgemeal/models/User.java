package edu.qc.seclass.fridgemeal.models;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;

@ParseClassName("Users")
public class User extends ParseObject{
    public static final String key_description = "userInfo";
    public static final String key_image = "userImage";
    public static final String key_user = "user";
    public static final String key_status = "status";
    public static String key_created = "createdAt";
    public static final String KEY_FAVORITE ="favorites";


    public String getDescription() {
        return getString(key_description);
    }

    public void setDescription(String description){
        put(key_description, description);
    }

    public ParseFile getImage() {
        return getParseFile(key_image);
    }

    public void setImage(ParseFile parseFile){
        put(key_image, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(key_user);
    }

    public void setUser(ParseUser user){
        put(key_user, user);
    }

    public String getStatus(){
        return getString(key_status);
    }

    public void setStatus(String status){
        put(key_status, status);
    }
    public void setFavorites(JSONArray jsonArray){put(KEY_FAVORITE, jsonArray);}
    public JSONArray getFavorites(){return getJSONArray(KEY_FAVORITE);}



}