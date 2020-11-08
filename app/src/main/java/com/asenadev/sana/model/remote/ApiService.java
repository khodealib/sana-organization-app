package com.asenadev.sana.model.remote;

import com.asenadev.sana.model.customer.Data;
import com.asenadev.sana.model.customer.referral.CustomerReferralResponse;
import com.asenadev.sana.model.employee.EmployeeResponse;
import com.asenadev.sana.model.login.LoginResponse;
import com.asenadev.sana.model.profile.Profile;
import com.asenadev.sana.model.profile.update.UpdateProfileResponse;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("client/login")
    Single<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("employee/list")
    Single<EmployeeResponse> getEmployeeList();

    @FormUrlEncoded
    @POST("customer/get-info")
    Single<Data> getCustomer(@Field("national_id") String nationalID);

    @FormUrlEncoded
    @POST("customer/add")
    Single<Data> addCustomer(
            @Field("national_code") String nationalID,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("father_name") String fatherName,
            @Field("mobile_number") String mobileNumber
    );


    @FormUrlEncoded
    @POST("customer/referral")
    Single<CustomerReferralResponse> customerReferral(
            @Field("customer_id") String customerID,
            @Field("employee_id") String employeeID,
            @Field("description") String description
    );

    @GET("profile")
    Single<Profile> getProfile();

    @FormUrlEncoded
    @POST("employee/update-own-profile")
    Single<UpdateProfileResponse> updateProfile(
            @Field("name") String name,
            @Field("username") String username,
            @Field("profile_pic") MultipartBody profilePic
    );


}
