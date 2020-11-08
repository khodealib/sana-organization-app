package com.asenadev.sana.model.remote;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceProvider {

    private static ApiService apiService;
    private static Retrofit retrofitAuth, retrofitNoAuth;
    private static String BASE_URL = "https://asena-api.asenadev.com/api/";

    public static Retrofit getRetrofitAuth(String token) {
        if (retrofitAuth == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request oldRequest = chain.request();
                        if (oldRequest.url().encodedPath().contains("login") && oldRequest.method().equals("post"))
                            return chain.proceed(oldRequest);


                        HttpUrl requestUrl = oldRequest.url();


                        Request newRequest = oldRequest.newBuilder()
                                .url(requestUrl)
                                .addHeader("Authorization", "Bearer " + token)
                                .build();

                        return chain.proceed(newRequest);
                    }).build();

            retrofitAuth = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofitAuth;
    }

    public static Retrofit getRetrofitNoAuth() {
        if (retrofitNoAuth == null) {

            retrofitAuth = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return retrofitNoAuth;
    }

    //checks if an authenticated retrofit already exists and re-uses it, else a new instance is created
    public static <S> S createService(Class<S> serviceClass, String token) {
        if (retrofitAuth == null)
            return getRetrofitAuth(token).create(serviceClass);
        else
            return retrofitAuth.create(serviceClass);
    }

    //checks if a public retrofit already exists and re-uses it, else a new instance is created
    public static <S> S createService(Class<S> serviceClass) {
        if (retrofitNoAuth == null)
            return getRetrofitNoAuth().create(serviceClass);
        else
            return retrofitNoAuth.create(serviceClass);
    }

    //call this when the user logs out of the app or if the auth token is refreshed from backend. This resets all retrofit instances to null
    public static void clearRetrofit() {
        retrofitAuth = null;
        retrofitNoAuth = null;
    }
}
