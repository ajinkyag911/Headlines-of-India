package com.example.ajinkya.headlinesofindia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ArticleActivity extends AppCompatActivity {

    //private TextView mArticleTitle;
    //private TextView mArticleContent;
    //private ImageView mArticleImage;
    private Article_ article;
    private ShareActionProvider mShareActionProvider;
    private WebView mWebview;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        //mArticleTitle = findViewById(R.id.articleTitle);
        //mArticleContent = findViewById(R.id.articleContent);
        //mArticleImage = findViewById(R.id.articleImage);

        Intent intent = getIntent();
        article = (Article_) getIntent().getSerializableExtra("MainActivity");

        //String title = article.getTitle();
        //String content = article.getContent();
        //Picasso.get().load(article.getUrlToImage()).into(mArticleImage);

        mWebview = findViewById(R.id.webiew);
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.loadUrl(article.getUrl());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //mArticleTitle.setText(title);
        //mArticleContent.setText(content);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = article.getUrl();
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, article.getTitle());
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share link via"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
