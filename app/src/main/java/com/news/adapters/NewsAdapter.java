package com.news.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.news.MainActivity;
import com.news.R;
import com.news.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Activity activity;
    ArrayList<News> newsArrayList;

    public NewsAdapter(Activity activity, ArrayList<News> newsArrayList) {
        this.activity = activity;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.news_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull NewsAdapter.ViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        holder.tvPublishedAt.setText(news.getPublishedAt());
        holder.tvSourceName.setText(news.getSourseName());
        holder.tvTitle.setText(news.getTitle());
        holder.tvContent.setText(news.getContent());
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(news.getUrl()));
                activity.startActivity(intent);

            }
        });

        Picasso.get().load(news.getUrlToImage()).placeholder(activity.getResources().getDrawable(R.drawable.ic_launcher_background)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPublishedAt, tvTitle, tvContent, tvMore, tvSourceName;
        ImageView imageView;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            tvPublishedAt = itemView.findViewById(R.id.tvPublishedAt);
            tvSourceName = itemView.findViewById(R.id.tvSourceName);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvMore = itemView.findViewById(R.id.tvMore);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
