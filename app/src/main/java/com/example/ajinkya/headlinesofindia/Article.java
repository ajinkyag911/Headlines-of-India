
package com.example.ajinkya.headlinesofindia;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article implements Serializable{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<Article_> articles = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Article() {
    }

    /**
     * 
     * @param articles
     * @param totalResults
     * @param status
     */
    public Article(String status, Integer totalResults, List<Article_> articles) {
        super();
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article_> getArticles() {
        return articles;
    }

    public void setArticles(List<Article_> articles) {
        this.articles = articles;
    }

}
