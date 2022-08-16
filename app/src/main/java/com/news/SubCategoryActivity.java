package com.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.news.adapters.CategoryAdapter;
import com.news.adapters.NewsAdapter;
import com.news.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {

    String selectedCategory;
    RecyclerView rvSubCategory;
    TextView tvSubCategory;
    String result;
    ArrayList<News> newsArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        selectedCategory = getIntent().getStringExtra("selectedCategory");

        tvSubCategory = findViewById(R.id.tvSubCategory);
        rvSubCategory = findViewById(R.id.rvSubCategory);
        rvSubCategory.setLayoutManager(new LinearLayoutManager(this));

        tvSubCategory.setText(selectedCategory);
        result = fetchData(selectedCategory.toLowerCase()+".json");

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                News news = new News(json.getJSONObject("source").getString("name"),
                        json.getString("author"),
                        json.getString("title"),
                        json.getString("description"),
                        json.getString("url"),
                        json.getString("urlToImage"),
                        json.getString("publishedAt"),
                        json.getString("content"));

                newsArrayList.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NewsAdapter newsAdapter = new NewsAdapter(SubCategoryActivity.this,newsArrayList);
        rvSubCategory.setAdapter(newsAdapter);

    }

    String fetchData(String  fileName) {
        String result = "";
        try {
            InputStream stream = getAssets().open(fileName);
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            stream.close();
            result = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
