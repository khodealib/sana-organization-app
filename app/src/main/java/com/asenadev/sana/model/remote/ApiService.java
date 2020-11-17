package com.asenadev.sana.model.remote;

import com.asenadev.sana.model.customer.CustomerReferralResponse;
import com.asenadev.sana.model.customer.add.CustomerAddResponse;
import com.asenadev.sana.model.customer.arrival.departuelist.ArrivalDepartureResponse;
import com.asenadev.sana.model.customer.arrival.submitexit.SubmitDepartureResponse;
import com.asenadev.sana.model.customer.exit.SetExitCustomerResponse;
import com.asenadev.sana.model.customer.get.CustomerGetInfoResponse;
import com.asenadev.sana.model.customer.referralcompleted.CompleteReferralResponse;
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
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("client/login")
    Single<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("employee/list")
    Single<EmployeeResponse> getEmployeeList();

    @FormUrlEncoded
    @POST("customer/get-info")
    Single<CustomerGetInfoResponse> getCustomer(@Field("national_id") String nationalID);

    @FormUrlEncoded
    @POST("customer/add")
    Single<CustomerAddResponse> addCustomer(

            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("father_name") String fatherName,
            @Field("mobile_number") String mobileNumber,
            @Field("national_code") String nationalID
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

    @FormUrlEncoded
    @POST("complete-reference")
    Single<CompleteReferralResponse> completeReferral(@Field("reference_id") String referenceId);


    @GET("customer/{user_id}/set-departure")
    Single<SetExitCustomerResponse> setExit(@Path("user_id") String userId);

    @GET("customer/arrival-departure-list")
    Single<ArrivalDepartureResponse> getArrivalDepartureList(
            @Query("national_code") String nationalCode,
            @Query("present") int present,
            @Query("page") int pageNumber);


    @GET("customer/arrival-departure-list")
    Single<ArrivalDepartureResponse> getArrivalDepartureList(
            @Query("present") int present);


    @GET("customer/arrival-departure-list")
    Single<ArrivalDepartureResponse> getArrivalDepartureList(
            @Query("national_code") String nationalCode,
            @Query("present") int present);


    @FormUrlEncoded
    @POST("customer/submit-departure")
    Single<SubmitDepartureResponse> submitDeparture(@Field("arrival_id") String arrivalId);
}
