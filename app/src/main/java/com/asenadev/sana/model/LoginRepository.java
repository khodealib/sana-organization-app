package com.asenadev.sana.model;

import android.app.Application;

import com.asenadev.sana.model.remote.ApiClient;
import com.asenadev.sana.model.remote.ApiService;

import io.reactivex.Completable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginRepository {

    private ApiService apiService;
    private TokenHolder tokenHolder;
    private LoginResponse response;
    private static final String TAG = "LoginRepository";

    public LoginRepository(Application application) {
        apiService = ApiClient.getApiService(application);
        tokenHolder = new TokenHolder(application);
    }

    public Completable login(String username, String password) {


        RequestBody usernameBody = RequestBody.create(MediaType.parse("text/plain"),username);
        RequestBody passwordBody = RequestBody.create(MediaType.parse("text/plain"),password);
        return apiService.login(usernameBody,passwordBody)
                .doOnSuccess(loginResponse -> response = loginResponse)
                .ignoreElement();


//        return apiService.login(username, password).enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                Log.d(TAG, "onResponse: "+ response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//
//            }
//        });

    }

}
