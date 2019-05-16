package com.dmitry.nytimes.data;

import android.support.annotation.NonNull;
import com.dmitry.nytimes.models.ResultTitle;
import com.dmitry.nytimes.models.Title;
import com.dmitry.nytimes.presenter.Interfaces.PopularOnFinishedListener;
import com.dmitry.nytimes.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class MostPopularRepository {

    private ArrayList<Title> data;
    private PopularOnFinishedListener onFinishedListener;


    public MostPopularRepository(PopularOnFinishedListener mostPopularPresenter) {
        this.onFinishedListener = mostPopularPresenter;
    }

    public void getEmailed(){
        Call<ResultTitle> mCall = RetrofitService.getInstance().getRetrofitApi().getMostEmailed();
        mCall.enqueue(new Callback<ResultTitle>() {
            @Override
            public void onResponse(Call<ResultTitle> call, @NonNull Response<ResultTitle> response) {
                response.body();
                data = (ArrayList<Title>) response.body().getResult();
                onFinishedListener.onFinished(data);
            }
            @Override
            public void onFailure(Call<ResultTitle> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    public void getShared(){
        Call<ResultTitle> mCall = RetrofitService.getInstance().getRetrofitApi().getMostShared();
        mCall.enqueue(new Callback<ResultTitle>() {
            @Override
            public void onResponse(Call<ResultTitle> call, @NonNull Response<ResultTitle> response) {
                response.body();
                data = (ArrayList<Title>) response.body().getResult();
                onFinishedListener.onFinished(data);
            }
            @Override
            public void onFailure(Call<ResultTitle> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    public void getViewed(){
        Call<ResultTitle> mCall = RetrofitService.getInstance().getRetrofitApi().getMostViewed();
        mCall.enqueue(new Callback<ResultTitle>() {
            @Override
            public void onResponse(Call<ResultTitle> call, @NonNull Response<ResultTitle> response) {
                response.body();
                data = (ArrayList<Title>) response.body().getResult();
                onFinishedListener.onFinished(data);
            }
            @Override
            public void onFailure(Call<ResultTitle> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

}
