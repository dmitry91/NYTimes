package com.dmitry.nytimes.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class RetrofitService {

    private static API retrofitApi;
    private static String BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/";
    private static String KEY = "112c3a156cc3458db55f7d3524d5ddc1";

    private RetrofitService() {
    }

    public static void clearInstance(){
        retrofitApi = null;
    }

    public static API getInstance() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("api-key", KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        }).build();

        if (retrofitApi == null) {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            retrofitApi = mRetrofit.create(API.class);
        }
        return retrofitApi;
    }

}
