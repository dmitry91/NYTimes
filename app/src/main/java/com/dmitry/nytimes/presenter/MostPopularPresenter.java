package com.dmitry.nytimes.presenter;

import com.dmitry.nytimes.data.MostPopularRepository;
import com.dmitry.nytimes.models.Title;
import com.dmitry.nytimes.presenter.Interfaces.PopularOnFinishedListener;
import com.dmitry.nytimes.ui.fragments.FragmentMostPopular;
import java.util.ArrayList;

public class MostPopularPresenter implements PopularOnFinishedListener {

    private FragmentMostPopular view;
    private MostPopularRepository repository = new MostPopularRepository(this);

    public MostPopularPresenter(FragmentMostPopular view) {
        this.view = view;
    }

    public void getEmailed(){
        repository.getEmailed();
    }

    public void getShared(){
        repository.getShared();
    }
    public void getViewed(){
        repository.getViewed();
    }

    @Override
    public void onFinished(ArrayList<Title> titles) {
        view.setData(titles);
        view.invisibleProgressBar();
    }

    @Override
    public void onFailure(Throwable t) {
        view.invisibleProgressBar();
        t.printStackTrace();
    }

    public void destroy(){
        view = null;
    }
}
