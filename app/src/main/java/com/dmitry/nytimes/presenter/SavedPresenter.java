package com.dmitry.nytimes.presenter;

import com.dmitry.nytimes.data.SavedRepository;
import com.dmitry.nytimes.db.TitleEntity;
import com.dmitry.nytimes.presenter.Interfaces.SavedOnFinishListener;
import com.dmitry.nytimes.ui.fragments.FragmentSaved;

import java.util.ArrayList;

public class SavedPresenter implements SavedOnFinishListener {

    private FragmentSaved view;
    private SavedRepository repository = new SavedRepository(this);

    public SavedPresenter(FragmentSaved view) {
        this.view = view;
    }

    public void getSaved(){
        repository.getSaved();
    }

    @Override
    public void onFinished(ArrayList<TitleEntity> titles) {
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
