package com.example.ajinkya.headlinesofindia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ArticleAPI {

    public static final String url = "https://newsapi.org/v2/";
    public static final String apiKey = "2eca830d69bc4e1ab635b7d152885576";

    public static PostService postService = null;

    public static PostService getService()
    {
        if(postService == null)
        {
            Retrofit r = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

            postService = r.create(PostService.class);
        }
        return postService;
    }

    public interface PostService
    {
        @GET("top-headlines/?country=in&excludeDomains=indiatimes.com&pageSize=50&apiKey="+apiKey)
        Call<Article> getArticles();

        @GET("top-headlines/?country=in&category=business&excludeDomains=indiatimes.com&apiKey="+apiKey)
        Call<Article> getBusinessArticles();

        @GET("top-headlines/?country=in&category=entertainment&excludeDomains=indiatimes.com&apiKey="+apiKey)
        Call<Article> getEntertainmentArticles();

        @GET("top-headlines/?country=in&category=health&excludeDomains=indiatimes.com&apiKey="+apiKey)
        Call<Article> getHealthArticles();

        @GET("top-headlines/?country=in&category=science&excludeDomains=indiatimes.com&apiKey="+apiKey)
        Call<Article> getScienceArticles();

        @GET("top-headlines/?country=in&category=sports&excludeDomains=indiatimes.com&apiKey="+apiKey)
        Call<Article> getSportsArticles();

        @GET("top-headlines/?country=in&category=technology&excludeDomains=indiatimes.com&apiKey="+apiKey)
        Call<Article> getTechnologyArticles();

        @GET("everything/")
        Call<Article> getSearchResults(@Query("q") String query, @Query("apiKey") String key);
    }
}
