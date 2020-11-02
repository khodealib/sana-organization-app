package com.asenadev.sana.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.login.LoginResponse;
import com.asenadev.sana.model.remote.ApiClient;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.remote.ApiServiceProvider;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
