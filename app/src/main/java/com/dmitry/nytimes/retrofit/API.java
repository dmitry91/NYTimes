package com.dmitry.nytimes.retrofit;

import com.dmitry.nytimes.models.ResultTitle;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("mostemailed/all-sections/30")
    Call<ResultTitle> getMostEmailed();

    @GET("mostshared/all-sections/30")
    Call<ResultTitle> getMostShared();

    @GET("mostviewed/all-sections/30")
    Call<ResultTitle> getMostViewed();

}
