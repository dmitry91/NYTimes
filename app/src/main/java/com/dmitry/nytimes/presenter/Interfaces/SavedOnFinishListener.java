package com.dmitry.nytimes.presenter.Interfaces;

import com.dmitry.nytimes.db.TitleEntity;

import java.util.ArrayList;

public interface SavedOnFinishListener {
    void onFinished(ArrayList<TitleEntity> titles);
    void onFailure(Throwable t);
}
