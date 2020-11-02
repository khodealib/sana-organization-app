package com.asenadev.sana.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class TokenHolder {

    private final SharedPreferences sharedPreferences;

    public TokenHolder(Application application) {
        sharedPreferences = application.getSharedPreferences("bearer_token", Context.MODE_PRIVATE);

        sharedPreferences.registerOnSharedPreferenceChangeListener(((sharedPreferences, s) -> {

        }));

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
