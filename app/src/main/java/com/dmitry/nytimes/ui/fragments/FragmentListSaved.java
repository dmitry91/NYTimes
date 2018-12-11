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
import com.dmitry.nytimes.db.App;
import com.dmitry.nytimes.db.AppDatabase;
import com.dmitry.nytimes.db.TitleEntity;
import com.dmitry.nytimes.ui.adapters.SavedDataAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import net.vrgsoft.layoutmanager.RollingLayoutManager;

import java.util.ArrayList;

public class FragmentListSaved extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<TitleEntity> data;
    private SavedDataAdapter adapter;
    private ProgressBar simpleProgressBar;
    private AppDatabase db = App.getInstance().getDatabase();

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
        recyclerView = getActivity().findViewById(R.id.card_recycler_view_saved);
        recyclerView.setHasFixedSize(true);
        RollingLayoutManager rollingLayoutManager = new RollingLayoutManager(getActivity());
        recyclerView.setLayoutManager(rollingLayoutManager);
        loadFromDB();
    }

    @SuppressLint("CheckResult")
    private void loadFromDB(){
        simpleProgressBar = getActivity().findViewById(R.id.mainProgressBar);
        simpleProgressBar.setVisibility(View.VISIBLE);
        db.titleDao().getAllData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(titleEntities -> {
                    data = (ArrayList<TitleEntity>) titleEntities;
                    adapter = new SavedDataAdapter(data);
                    recyclerView.setAdapter(adapter);
                    simpleProgressBar.setVisibility(View.INVISIBLE);
                });
    }
}