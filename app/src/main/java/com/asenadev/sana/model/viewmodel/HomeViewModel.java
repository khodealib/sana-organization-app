package com.asenadev.sana.model.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.profile.get.ProfileData;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.repository.HomeRepository;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends AndroidViewModel {


    private final HomeRepository homeRepository;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private MutableLiveData<ProfileData> profileLiveData=new MutableLiveData<>();
    private static final String TAG = "HomeViewModel";

    public HomeViewModel(@NonNull Application application , ApiService apiService) {
        super(application);
        this.homeRepository = new HomeRepository(apiService);
    }

    public LiveData<ProfileData> getProfile() {
        homeRepository.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProfileData>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull ProfileData profileData) {
                        profileLiveData.postValue(profileData);
                        Log.i(TAG, "onSuccess: "+profileData.toString());
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.i(TAG, "onError: "+ e.getMessage());
                    }
                });

        return profileLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
