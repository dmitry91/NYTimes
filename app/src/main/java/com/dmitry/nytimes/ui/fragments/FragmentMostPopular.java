package com.dmitry.nytimes.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.dmitry.nytimes.R;
import com.dmitry.nytimes.models.Title;
import com.dmitry.nytimes.presenter.MostPopularPresenter;
import com.dmitry.nytimes.ui.adapters.DataAdapter;
import net.vrgsoft.layoutmanager.RollingLayoutManager;

import java.util.ArrayList;

public class FragmentMostPopular extends Fragment {

    private ProgressBar simpleProgressBar;
    private RecyclerView recyclerView;
    MostPopularPresenter mostPopularPresenter;
    private int cardId;
    private int fragmentId;
    int page;

    public static FragmentMostPopular newInstance(int page) {
        FragmentMostPopular fragment = new FragmentMostPopular();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
        mostPopularPresenter = new MostPopularPresenter(this);
        switch (page){
            case 0:{
                mostPopularPresenter.getEmailed();
                cardId = R.id.card_recycler_view_emailed;
                fragmentId = R.layout.fragment_list_emailed;
                break;
            }
            case 1:{
                mostPopularPresenter.getShared();
                cardId = R.id.card_recycler_view_shared;
                fragmentId = R.layout.fragment_list_shared;
                break;
            }
            case 2:{
                mostPopularPresenter.getViewed();
                cardId = R.id.card_recycler_view_viewed;
                fragmentId = R.layout.fragment_list_viewed;
                break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(fragmentId, container, false);
    }

    @Override
    public void onStart() {
        initViews();
        simpleProgressBar.setVisibility(View.VISIBLE);
        super.onStart();
    }

    private void initViews() {
        simpleProgressBar = getActivity().findViewById(R.id.mainProgressBar);
        recyclerView = getActivity().findViewById(cardId);
        recyclerView.setHasFixedSize(true);
        RollingLayoutManager rollingLayoutManager = new RollingLayoutManager(getActivity());
        recyclerView.setLayoutManager(rollingLayoutManager);
    }

    public void setData(ArrayList<Title> titles) {
        DataAdapter adapter = new DataAdapter(titles);
        recyclerView.setAdapter(adapter);
    }

    public void invisibleProgressBar(){
        simpleProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mostPopularPresenter.destroy();
    }
}