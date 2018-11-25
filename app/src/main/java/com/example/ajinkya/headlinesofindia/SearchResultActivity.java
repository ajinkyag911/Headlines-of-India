package com.example.ajinkya.headlinesofindia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;

import adapters.CustomAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {

    private ListView mArticlesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        mArticlesListView = findViewById(R.id.searchResults);
        Intent intent = getIntent();
        String query = intent.getStringExtra(MainActivity.SEARCH_QUERY);
        getData(query);
    }

    private void getData(String q) {
        Call<Article> article = ArticleAPI.getService().getSearchResults(q,ArticleAPI.apiKey);
        article.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                final Article list = response.body();

                Article_[] articles = new Article_[list.getArticles().size()];
                for (int i = 0; i < list.getArticles().size(); i++) {
                    articles[i] = list.getArticles().get(i);
                }

                ListAdapter myAdapter = null;
                try {
                    myAdapter = new CustomAdapter(SearchResultActivity.this, articles);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                mArticlesListView.setAdapter(myAdapter);

                mArticlesListView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(SearchResultActivity.this, ArticleActivity.class);
                                intent.putExtra("MainActivity", (Serializable) list.getArticles().get(position));
                                startActivity(intent);
                            }
                        }
                );
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast.makeText(SearchResultActivity.this, "Network Unavailable", Toast.LENGTH_LONG).show();

            }
        });
    }
}
