package com.asenadev.sana.model.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.profile.get.ProfileData;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.repository.HomeRepository;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends AndroidViewModel {


    private static final String TAG = "ProfileViewModel";

    private HomeRepository homeRepository;
    private MutableLiveData<ProfileData> profileDataLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Boolean> isUpdated = new MutableLiveData<>();

    public ProfileViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
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

    public LiveData<Boolean> updateProfile(String fullName, String nationalCode, File imageFile) {
        homeRepository.updateOwnProfile(fullName, nationalCode, imageFile)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(isUpdate -> isUpdated.postValue(isUpdate))
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe();

        return isUpdated;
    }
    public LiveData<Boolean> updateProfile(String fullName, String nationalCode) {
        homeRepository.updateOwnProfile(fullName, nationalCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(isUpdate -> isUpdated.postValue(isUpdate))
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe();

        return isUpdated;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
