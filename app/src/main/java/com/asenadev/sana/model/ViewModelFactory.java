package com.asenadev.sana.model;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.asenadev.sana.LoginViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    private TokenHolder tokenHolder;

    public ViewModelFactory(Application application, TokenHolder tokenHolder) {


        this.application = application;
        this.tokenHolder = tokenHolder;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(application,tokenHolder);
    }
}
