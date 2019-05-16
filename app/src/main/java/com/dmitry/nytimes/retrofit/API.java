package com.dmitry.nytimes.retrofit;

import com.dmitry.nytimes.models.ResultTitle;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("emailed/30")
    Call<ResultTitle> getMostEmailed();

    @GET("shared/30")
    Call<ResultTitle> getMostShared();

    @GET("viewed/30")
    Call<ResultTitle> getMostViewed();

}
