package com.dmitry.nytimes.ui.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.dmitry.nytimes.R;
import com.dmitry.nytimes.db.App;
import com.dmitry.nytimes.db.AppDatabase;
import com.dmitry.nytimes.db.TitleEntity;
import com.dmitry.nytimes.models.Title;
import com.dmitry.nytimes.ui.activity.ActivityWevPage;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Title> resultTitles;
    private View view;
    private AppDatabase db = App.getInstance().getDatabase();

    public DataAdapter(ArrayList<Title> resultTitles) {
        this.resultTitles = resultTitles;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        final String urlCurrentArticle = resultTitles.get(position).getUrl();
        holder.title.setText(resultTitles.get(position).getTitle());
        Glide
                .with(view.getContext())
                .load(resultTitles.get(position).getMedia().get(0).getMediaMedia().get(1).getUrl())
                .into(holder.image);
        //open article
        holder.itemView.setOnClickListener(v -> openWebPage(urlCurrentArticle));

        //entity for save to database
        TitleEntity titleEntity = new TitleEntity();
        titleEntity.setUrl(resultTitles.get(position).getUrl());
        titleEntity.setTitleText(resultTitles.get(position).getTitle());
        titleEntity.setImgUrl(resultTitles.get(position).getMedia().get(0).getMediaMedia().get(1).getUrl());
        //save article to database
        holder.btnSave.setOnClickListener(v -> {
            Completable.fromAction(() -> db.titleDao().insert(titleEntity)).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onComplete() {
                    Toast.makeText(view.getContext(), "Article saved", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(view.getContext(),"This article has already been saved.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return resultTitles.size();
    }

    private void openWebPage(String url){
        Intent intent = new Intent(view.getContext(), ActivityWevPage.class);
        intent.putExtra("URL", url);
        view.getContext().startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private Button btnSave;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_on_card);
            image = itemView.findViewById(R.id.img_on_card);
            btnSave = itemView.findViewById(R.id.buttonCard);
        }
    }
}