package com.asenadev.sana.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.login.LoginResponse;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.remote.ApiServiceProvider;

import io.reactivex.Single;

public class LoginRepository {

    private ApiService apiService;
    private MutableLiveData<LoginResponse> response;
    private static final String TAG = "LoginRepository";

    public LoginRepository(Application application) {
        apiService = ApiServiceProvider.getApiService(application);
    }

    public Single<LoginResponse> login(String username, String password) {
        return apiService.login(username,password);

    }

}
