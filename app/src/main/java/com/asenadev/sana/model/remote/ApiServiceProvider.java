package com.asenadev.sana.model.remote;

import android.app.Application;

import com.asenadev.sana.model.TokenHolder;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceProvider {

    private static ApiService apiService;
    private static String BASE_URL="https://asena-api.asenadev.com/api/";

    public static ApiService getApiService(Application application) {
        if (apiService==null) {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(chain -> {
//                        Request oldRequest = chain.request();
//                        if (oldRequest.url().encodedPath().contains("login") && oldRequest.method().equals("post"))
//                            return chain.proceed(oldRequest);
//
//
//                        HttpUrl requestUrl = oldRequest.url();
//
//                        String token = new TokenHolder(application).getUserLoginToken();
//
//                        Request newRequest = oldRequest.newBuilder()
//                                .url(requestUrl)
//                                .addHeader("Authorization", "Bearer " + token)
//                                .build();
//
//                        return chain.proceed(newRequest);
//                    }).build();

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(client)
                    .build();

            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
