package com.dmitry.nytimes.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dmitry.nytimes.R;
import com.dmitry.nytimes.models.Title;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Title> resultTitles;
    private View view;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(urlCurrentArticle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultTitles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_on_card);
            image = itemView.findViewById(R.id.img_on_card);
        }
    }
}