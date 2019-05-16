package com.dmitry.nytimes.data;

import android.annotation.SuppressLint;
import com.dmitry.nytimes.db.App;
import com.dmitry.nytimes.db.AppDatabase;
import com.dmitry.nytimes.db.TitleEntity;
import com.dmitry.nytimes.presenter.Interfaces.SavedOnFinishListener;
import io.reactivex.android.schedulers.AndroidSchedulers;

import java.util.ArrayList;

public class SavedRepository {

    private SavedOnFinishListener onFinishedListener;

    public SavedRepository(SavedOnFinishListener onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }

    @SuppressLint("CheckResult")
    public void getSaved(){
        AppDatabase db = App.getInstance().getDatabase();
        db.titleDao().getAllData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(titleEntities -> {
                    ArrayList<TitleEntity> data = (ArrayList<TitleEntity>) titleEntities;
                    onFinishedListener.onFinished(data);
                });
    }

}
