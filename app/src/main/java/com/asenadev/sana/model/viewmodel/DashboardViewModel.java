package com.asenadev.sana.model.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asenadev.sana.model.customer.get.Customer;
import com.asenadev.sana.model.employee.Employee;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.repository.HomeRepository;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DashboardViewModel extends AndroidViewModel {

    private static final String TAG = "DashboardViewModel";
    private final HomeRepository homeRepository;
    private MutableLiveData<Boolean> isExistCustomer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCreatedCustomer = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Customer> customerProfileLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Employee>> employeeListLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRefersTo = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCompletedReference = new MutableLiveData<>();
    private MutableLiveData<Boolean> isExited = new MutableLiveData<>();

    public DashboardViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        this.homeRepository = new HomeRepository(apiService);
    }


    public LiveData<Boolean> checkExistCustomer(String nationalID) {

        homeRepository.checkExistCustomer(nationalID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Boolean aBoolean) {
                        isExistCustomer.postValue(aBoolean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });

        return isExistCustomer;
    }

    public LiveData<Customer> getCustomerProfile(String nationalCode) {

        homeRepository.getCustomerProfile(nationalCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Customer>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Customer customer) {
                        customerProfileLiveData.postValue(customer);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });

        return customerProfileLiveData;
    }

    public LiveData<Boolean> addCustomerProfile(String firstName, String lastName, String fatherName, String phoneNumber, String nationalCode) {
        homeRepository.addUpdateCustomer(firstName, lastName, fatherName, phoneNumber, nationalCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Boolean aBoolean) {
                        isCreatedCustomer.postValue(aBoolean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });

        return isCreatedCustomer;
    }

    public LiveData<List<Employee>> getEmployeeList() {
        homeRepository.getEmployeeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Employee>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<Employee> employees) {
                        employeeListLiveData.postValue(employees);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });
        return employeeListLiveData;
    }

    public LiveData<Boolean> setCustomerReferral(String customerId, String employeeId, String description) {
        homeRepository.referralTo(customerId, employeeId, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Boolean aBoolean) {
                        isRefersTo.postValue(aBoolean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });

        return isRefersTo;
    }

    public LiveData<Boolean> completeReference(String referenceId) {
        homeRepository.completeReferral(referenceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Boolean aBoolean) {
                        isCompletedReference.postValue(aBoolean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }
                });
        return isCompletedReference;
    }

    public LiveData<Boolean> setExitCustomer(String userId) {
        Log.i(TAG, "setExitCustomer: " + userId);
        homeRepository.setExit(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Boolean isExit) {
                        isExited.postValue(isExit);
                        Log.i(TAG, "onSuccess: " + isExit);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage() + e.toString());
                    }
                });

        return isExited;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
