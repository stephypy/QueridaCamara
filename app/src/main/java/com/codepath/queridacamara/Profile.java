package com.codepath.queridacamara;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("User")
public class Profile extends ParseObject {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PROFILE_PICTURE = "profilePicture";


    public String getUsername() {
        return getString(KEY_USERNAME);
    }

    public void setUsername(String username) {  put(KEY_USERNAME, username); }

    public ParseFile getProfilePicture() {
        return getParseFile(KEY_PROFILE_PICTURE);
    }

    public void setProfilePicture(ParseFile image) {
        put(KEY_PROFILE_PICTURE, image);
    }
}
