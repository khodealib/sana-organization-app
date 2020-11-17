package com.asenadev.sana.model.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.profile.ProfileData;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.repository.HomeRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends AndroidViewModel {


    private ApiService apiService;
    private HomeRepository homeRepository;
    private MutableLiveData<ProfileData> profileDataLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public ProfileViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
        this.homeRepository = new HomeRepository(apiService);
    }

    public LiveData<ProfileData> getProfile() {
        homeRepository.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(profileData -> profileDataLiveData.postValue(profileData))
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe();

        return profileDataLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
