package com.news.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.news.MainActivity;
import com.news.R;
import com.news.SubCategoryActivity;
import com.news.model.News;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    MainActivity mainActivity;
    String[] newsCategory;

    public CategoryAdapter(MainActivity mainActivity, String[] newsCategory) {
        this.mainActivity = mainActivity;
        this.newsCategory = newsCategory;
        Log.e("log", "dsgdfgF");
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryAdapter.ViewHolder holder, int position) {
        holder.tvCategory.setText(newsCategory[position]);
        holder.tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectCategory = newsCategory[position];
                Intent intent = new Intent(mainActivity, SubCategoryActivity.class);
                intent.putExtra("selectedCategory",selectCategory);
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsCategory.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;

        public ViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);

            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
