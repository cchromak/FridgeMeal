package edu.qc.seclass.fridgemeal.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class Profile extends ParseObject {
     public static final String key_status = "status";
     public static final String key_user = "user";
     public static final String key_image = "userImage";


        public String getStatus() {
            return getString(key_status);
        }
        public void setStatus(String status){
            put(key_status, status);
        }

        public ParseUser getUser() {
            return getParseUser(key_user);
        }
        public void setUser(ParseUser user){
            put(key_user, user);
        }

        public ParseFile getUserImage() {
            return getParseFile(key_image);
        }
        public void setUserImage(ParseFile parseFile){
        put(key_image, parseFile);
    }

}
