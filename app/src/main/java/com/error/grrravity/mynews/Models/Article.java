package com.error.grrravity.mynews.Models;

public class Article {

    private String mSection;
    private String mTitle;
    private String mDate;
    private String mUrl;
    private String mThumbnail;

    public Article(String section, String title, String date, String url, String thumbnail){
        this.mSection = section;
        this.mTitle = title;
        this.mDate = date;
        this.mUrl = url;
        this.mThumbnail = thumbnail;
    }

    public String getSection() { return mSection; }

    public String getTitle() { return mTitle; }

    public String getDate() { return mDate; }

    public String getUrl() { return mUrl; }

    public String getThumbnail() { return mThumbnail; }
}