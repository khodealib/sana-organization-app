package com.asenadev.sana.model.repository;

import android.annotation.SuppressLint;

import com.asenadev.sana.model.customer.arrival.departuelist.ArrivalsItem;
import com.asenadev.sana.model.customer.get.Customer;
import com.asenadev.sana.model.employee.Employee;
import com.asenadev.sana.model.profile.get.ProfileData;
import com.asenadev.sana.model.remote.ApiService;

import java.io.File;
import java.util.List;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HomeRepository {

    private static final String TAG = "HomeRepository";
    private ApiService apiService;

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

    public Single<Boolean> addUpdateCustomer(String firstName, String lastName, String fatherName, String phoneNumber, String nationalCode, File image) {

        RequestBody firstNameBody = RequestBody.create(MediaType.parse("text/plain"), firstName);
        RequestBody lastNameBody = RequestBody.create(MediaType.parse("text/plain"), lastName);
        RequestBody fatherNameBody = RequestBody.create(MediaType.parse("text/plain"), lastName);
        RequestBody phoneNumberBody = RequestBody.create(MediaType.parse("text/plain"), lastName);
        RequestBody nationalCodeBody = RequestBody.create(MediaType.parse("text/plain"), lastName);

        if (image == null) {
            return apiService.addCustomer(firstName, lastName, fatherName, phoneNumber, nationalCode).map(customerAddResponse -> customerAddResponse.getCode() == 200);
        }


        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("picture", image.getName(), requestBody);

        return apiService.addCustomer(firstNameBody, lastNameBody, fatherNameBody, phoneNumberBody, nationalCodeBody, filePart).map(customerAddResponse -> customerAddResponse.getCode() == 200);
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
        return apiService.getProfile().map(getProfileResponse -> getProfileResponse.getProfileData());
    }

    public Single<Boolean> updateOwnProfile(String fullName, String nationalCode, File imageFile) {

        RequestBody fullNameBody = RequestBody.create(MediaType.parse("text/plain"), fullName);
        RequestBody userNameBody = RequestBody.create(MediaType.parse("text/plain"), nationalCode);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("profile_pic", imageFile.getName(), requestBody);

        return apiService.updateProfile(fullNameBody, userNameBody, filePart).map(updateProfileResponse -> updateProfileResponse.getCode() == 200);
    }

    public Single<Boolean> updateOwnProfile(String fullName, String nationalCode) {


        return apiService.updateProfile(fullName, nationalCode).map(updateProfileResponse -> updateProfileResponse.getCode() == 200);
    }
}
