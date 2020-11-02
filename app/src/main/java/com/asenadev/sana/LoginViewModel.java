package com.asenadev.sana;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.asenadev.sana.model.LoginRepository;
import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.login.LoginResponse;

import io.reactivex.CompletableObserver;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository repository;
    private Disposable disposable;
    private Application application;
    private boolean isSuccessLogin;
    private TokenHolder tokenHolder;
    private LoginResponse response;
    private static final String TAG = "LoginViewModel";

    public LoginViewModel(@NonNull Application application, TokenHolder tokenHolder) {
        super(application);
        this.tokenHolder = tokenHolder;
        repository = new LoginRepository(application);
    }


    public boolean login(String userName, String password) {
        repository.login(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(loginResponse -> isSuccessLogin=true)
                .subscribe(new SingleObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull LoginResponse loginResponse) {
                        Log.i(TAG, "onSuccess: " + loginResponse.getData().getAccessToken());
                        response = loginResponse;
                        tokenHolder.saveUserLoginToken(loginResponse.getData().getAccessToken());
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.i(TAG, "onError: error");
                        isSuccessLogin =false;
                    }
                });

        return isSuccessLogin;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
