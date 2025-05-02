package com.example.myapplication1.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {
    private static final String PREF_NAME = "UserPrefs";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SharedPrefHelper(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void registerUser(String username, String password) {
        editor.putString(username, password);
        editor.apply();
    }

    public boolean isValidUser(String username, String password) {
        return password.equals(prefs.getString(username, ""));
    }

    public void setLoggedIn(String username) {
        editor.putString("loggedInUser", username);
        editor.apply();
    }

    public String getLoggedInUser() {
        return prefs.getString("loggedInUser", null);
    }

    public void logout() {
        editor.remove("loggedInUser");
        editor.apply();
    }
}
