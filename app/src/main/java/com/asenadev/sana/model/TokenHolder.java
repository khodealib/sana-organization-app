package com.asenadev.sana.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TokenHolder {

    private SharedPreferences sharedPreferences=null;

    public TokenHolder(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public void saveUserLoginToken(String token) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.apply();
    }

    public String getUserLoginToken() {
        return sharedPreferences.getString("token","");
    }
}
