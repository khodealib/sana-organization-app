package com.asenadev.sana.model.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.customer.arrival.departuelist.ArrivalsItem;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.repository.HomeRepository;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerViewModel extends AndroidViewModel {


    private static final String TAG = "CustomerViewModel";
    private MutableLiveData<List<ArrivalsItem>> presentList = new MutableLiveData<>();
    private MutableLiveData<List<ArrivalsItem>> exitedList = new MutableLiveData<>();
    private MutableLiveData<List<ArrivalsItem>> arrivalListByNationalCode = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDeparture = new MutableLiveData<>();
    private HomeRepository homeRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CustomerViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        homeRepository = new HomeRepository(apiService);
    }

    public LiveData<List<ArrivalsItem>> getPresentList() {
        homeRepository.getArrivalDepartureList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .doOnSuccess(arrivalsItems -> presentList.postValue(arrivalsItems))
                .subscribe();

        return presentList;
    }


    public LiveData<List<ArrivalsItem>> getExitedList() {
        homeRepository.getArrivalDepartureList(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ArrivalsItem>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<ArrivalsItem> arrivalsItems) {
                        exitedList.postValue(arrivalsItems);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });

        return exitedList;
    }

    public LiveData<List<ArrivalsItem>> getPresentListByNationalCode(String nationalCode, int present) {
        homeRepository.getArrivalDepartureListByNationalCode(nationalCode, present)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ArrivalsItem>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<ArrivalsItem> arrivalsItems) {
                        arrivalListByNationalCode.postValue(arrivalsItems);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });
        return arrivalListByNationalCode;
    }

    public LiveData<Boolean> setDeparture(String arrivalId) {
        homeRepository.submitCustomerDeparture(arrivalId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Boolean aBoolean) {
                        isDeparture.postValue(aBoolean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                        if (e.getMessage().equals("HTTP 400 Bad Request"))
                            isDeparture.postValue(false);
                    }
                });

        return isDeparture;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
