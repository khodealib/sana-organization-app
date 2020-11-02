package com.asenadev.sana.model.remote;

import com.asenadev.sana.model.employee.Response;
import com.asenadev.sana.model.login.LoginResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("client/login")
    Single<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("employee/list")
    Single<Response> getEmployeeList();


}
