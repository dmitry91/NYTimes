package com.dmitry.nytimes.presenter.Interfaces;

import com.dmitry.nytimes.models.Title;

import java.util.ArrayList;

public interface PopularOnFinishedListener {
    void onFinished(ArrayList<Title> titles);
    void onFailure(Throwable t);
}
