package com.dmitry.nytimes.retrofit;

import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class RetrofitService {

    private static API retrofitApi;
    private static String BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/";
    private static String KEY = "5CCZZpONsltL0p05NC0UOzJn8ls1R9eo";
    private static RetrofitService instance;

    private RetrofitService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder().addQueryParameter("api-key", KEY).build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                }).build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        retrofitApi = mRetrofit.create(API.class);

    }

    public static RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }


    public API getRetrofitApi() {
        return retrofitApi;
    }

}
