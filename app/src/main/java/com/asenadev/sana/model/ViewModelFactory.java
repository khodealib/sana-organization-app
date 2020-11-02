package com.asenadev.sana.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.asenadev.sana.LoginViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {


    private Application application;

    public ViewModelFactory(Application application) {


        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(application);
    }
}
