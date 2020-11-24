package com.asenadev.sana.model.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.remote.ApiService;

public class ViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    private TokenHolder tokenHolder;
    private ApiService apiService;

    public ViewModelFactory(Application application, TokenHolder tokenHolder, ApiService apiService) {


        this.application = application;
        this.tokenHolder = tokenHolder;
        this.apiService = apiService;
    }

    public ViewModelFactory(Application application, ApiService apiService) {
        this.apiService = apiService;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(application, tokenHolder, apiService);
        } else if (modelClass.isAssignableFrom(DashboardViewModel.class)) {
            return (T) new DashboardViewModel(application, apiService);
        } else if (modelClass.isAssignableFrom(CustomerViewModel.class)) {
            return (T) new CustomerViewModel(application, apiService);
        } else if (modelClass.isAssignableFrom(ProfileViewModel.class))
            return (T) new ProfileViewModel(application, apiService);
        else if (modelClass.isAssignableFrom(HomeViewModel.class))
            return (T) new HomeViewModel(application,apiService);
        else if (modelClass.isAssignableFrom(ReferralViewModel.class))
            return (T) new ReferralViewModel(application,apiService);

        return null;
    }
}
