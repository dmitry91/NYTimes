package com.dmitry.nytimes.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.dmitry.nytimes.ui.activity.ActivityWevPage;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class SavedDataAdapter extends RecyclerView.Adapter<SavedDataAdapter.ViewHolder> {

    private ArrayList<TitleEntity> resultTitles;
    private View view;
    private AppDatabase db = App.getInstance().getDatabase();

    public SavedDataAdapter(ArrayList<TitleEntity> resultTitles) {
        this.resultTitles = resultTitles;
    }

    @Override
    public SavedDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_title, parent, false);
        return new SavedDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SavedDataAdapter.ViewHolder holder, int position) {
        final String urlCurrentArticle = resultTitles.get(position).getUrl();
        holder.title.setText(resultTitles.get(position).getTitleText());
        Glide
                .with(view.getContext())
                .load(resultTitles.get(position).getImgUrl())
                .into(holder.image);
        //open article
        holder.itemView.setOnClickListener(v -> openWebPage(urlCurrentArticle));

        //delete article to database
        holder.btnDel.setOnClickListener(v -> {
            Completable.fromAction(() ->
                    db.titleDao().
                    delete(resultTitles.get(position))).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(view.getContext(), "Delete article", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
            });
        });
    }

    @Override
    public int getItemCount() {
        return resultTitles.size();
    }

    private void openWebPage(String url) {
        if (isNetworkAvailable()) {
            Intent intent = new Intent(view.getContext(), ActivityWevPage.class);
            intent.putExtra("URL", url);
            view.getContext().startActivity(intent);
        } else {
            Toast.makeText(view.getContext(), R.string.conn_error, Toast.LENGTH_LONG).show();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private Button btnDel;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_on_card);
            image = itemView.findViewById(R.id.img_on_card);
            btnDel = itemView.findViewById(R.id.buttonCard);
            btnDel.setText(R.string.btnDel); //set text delete
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)
                view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}