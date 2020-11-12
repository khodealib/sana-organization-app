package com.asenadev.sana.model.repository;

import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.login.LoginResponse;
import com.asenadev.sana.model.remote.ApiService;

import io.reactivex.Single;

public class LoginRepository {

    private final ApiService apiService;
    private MutableLiveData<LoginResponse> response;
    private static final String TAG = "LoginRepository";

    public LoginRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<LoginResponse> login(String username, String password) {
        return apiService.login(username,password);

    }

}
