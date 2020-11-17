package com.asenadev.sana.model.repository;

import android.annotation.SuppressLint;

import com.asenadev.sana.model.customer.arrival.departuelist.ArrivalsItem;
import com.asenadev.sana.model.customer.get.Customer;
import com.asenadev.sana.model.employee.Employee;
import com.asenadev.sana.model.profile.Profile;
import com.asenadev.sana.model.profile.ProfileData;
import com.asenadev.sana.model.remote.ApiService;

import java.util.List;

import io.reactivex.Single;

public class HomeRepository {

    private static final String TAG = "HomeRepository";
    private ApiService apiService;
    private Single<Boolean> isExist;

    public HomeRepository(ApiService apiService) {

        this.apiService = apiService;
    }

    public Single<Boolean> checkExistCustomer(String nationalCode) {

        return apiService.getCustomer(nationalCode).map(customerGetInfoResponse -> customerGetInfoResponse.getData().getCustomer() != null);
    }

    @SuppressLint("CheckResult")
    public Single<Customer> getCustomerProfile(String nationalCode) {
        return apiService.getCustomer(nationalCode).map(customerGetInfoResponse -> customerGetInfoResponse.getData().getCustomer());
    }

    public Single<Boolean> addUpdateCustomer(String firstName, String lastName, String fatherName, String phoneNumber, String nationalCode) {
        return apiService.addCustomer(firstName, lastName, fatherName, phoneNumber, nationalCode).map(customerAddResponse -> customerAddResponse.getCode() == 200);
    }

    public Single<List<Employee>> getEmployeeList() {
        return apiService.getEmployeeList().map(employeeResponse -> employeeResponse.getData().getEmployees());
    }

    public Single<Boolean> referralTo(String customerID, String employeeID, String description) {
        return apiService.customerReferral(customerID, employeeID, description).map(customerReferralResponse -> customerReferralResponse.getCode() == 200);
    }

    public Single<Boolean> completeReferral(String referenceId) {
        return apiService.completeReferral(referenceId).map(completeReferralResponse -> completeReferralResponse.getCode() == 200);
    }

    public Single<Boolean> setExit(String userId) {
        return apiService.setExit(userId).map(setExitCustomerResponse -> setExitCustomerResponse.getCode() == 200);
    }

    public Single<List<ArrivalsItem>> getArrivalDepartureList(int present) {
        return apiService.getArrivalDepartureList(present).map(arrivalDepartureResponse -> arrivalDepartureResponse.getData().getArrivals());
    }

    public Single<List<ArrivalsItem>> getArrivalDepartureListByNationalCode(String nationalCode, int present) {
        return apiService.getArrivalDepartureList(nationalCode, present).map(arrivalDepartureResponse -> arrivalDepartureResponse.getData().getArrivals());
    }

    public Single<Boolean> submitCustomerDeparture(String arrivalId) {
        return apiService.submitDeparture(arrivalId).map(submitDepartureResponse -> submitDepartureResponse.getCode() == 200);
    }

    public Single<ProfileData> getProfile() {
        return apiService.getProfile().map(Profile::getProfileData);
    }
}
