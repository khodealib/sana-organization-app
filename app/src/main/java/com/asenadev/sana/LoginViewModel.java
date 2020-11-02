package com.asenadev.sana;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.asenadev.sana.model.LoginRepository;

import io.reactivex.CompletableObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel  extends AndroidViewModel {

    private LoginRepository repository;
    private Disposable disposable;
    private MutableLiveData<String> isSuccessLogin;
    private static final String TAG = "LoginViewModel";

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new LoginRepository(application);
    }


    public LiveData<String> login(String userName, String password) {


        repository.login(userName,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        disposable =d;
                    }

                    @Override
                    public void onComplete() {
                        isSuccessLogin.postValue("success");

                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        isSuccessLogin.postValue(e.getMessage());
                        Log.i(TAG, "onError: "+e.getMessage());
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
