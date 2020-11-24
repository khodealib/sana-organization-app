package com.asenadev.sana.model.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.referral.ReferralDataItem;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.repository.HomeRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class
ReferralViewModel extends AndroidViewModel {

    private HomeRepository homeRepository;
    private MutableLiveData<List<ReferralDataItem>> referralDataItemLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isSuccessLiveData = new MutableLiveData<>();

    public ReferralViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        homeRepository = new HomeRepository(apiService);
    }

    public LiveData<List<ReferralDataItem>> getReferralList() {
        homeRepository.getReferralList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(referralDataItems -> referralDataItemLiveData.postValue(referralDataItems))
                .subscribe();

        return referralDataItemLiveData;
    }

    public LiveData<Boolean> setCompleteProcess(String description , String referenceId) {
        homeRepository.setCompleteReference(description,referenceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(isSuccess -> isSuccessLiveData.postValue(isSuccess))
                .doOnError(throwable -> isSuccessLiveData.postValue(Boolean.FALSE))
                .subscribe();

        return isSuccessLiveData;
    }
}
