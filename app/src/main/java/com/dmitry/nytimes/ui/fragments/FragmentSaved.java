package com.dmitry.nytimes.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.dmitry.nytimes.R;
import com.dmitry.nytimes.db.TitleEntity;
import com.dmitry.nytimes.presenter.SavedPresenter;
import com.dmitry.nytimes.ui.adapters.SavedDataAdapter;
import net.vrgsoft.layoutmanager.RollingLayoutManager;

import java.util.ArrayList;

public class FragmentSaved extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar simpleProgressBar;
    SavedPresenter savedPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_saved, container, false);
    }

    @Override
    public void onStart() {
        initViews();
        super.onStart();
    }

    private void initViews() {
        simpleProgressBar = getActivity().findViewById(R.id.mainProgressBar);
        recyclerView = getActivity().findViewById(R.id.card_recycler_view_saved);
        recyclerView.setHasFixedSize(true);
        RollingLayoutManager rollingLayoutManager = new RollingLayoutManager(getActivity());
        recyclerView.setLayoutManager(rollingLayoutManager);
        loadFromDB();
    }

    @SuppressLint("CheckResult")
    private void loadFromDB(){
        simpleProgressBar.setVisibility(View.VISIBLE);
        savedPresenter = new SavedPresenter(this);
        savedPresenter.getSaved();
    }

    public void setData(ArrayList<TitleEntity> titles) {
        SavedDataAdapter adapter = new SavedDataAdapter(titles);
        recyclerView.setAdapter(adapter);
    }

    public void invisibleProgressBar(){
        simpleProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedPresenter.destroy();
    }
}