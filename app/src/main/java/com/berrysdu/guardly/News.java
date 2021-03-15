package com.berrysdu.guardly;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class News {
    public final static int TYPE_1_Main=1;
    public final static int TYPE_1_POLITICS=2;
    public final static int TYPE_1_BUSINESS=3;
    public final static int TYPE_1_VEHICLE=4;
    public final static int TYPE_2_TRUMP=5;
    public final static int TYPE_2_AMERICA=6;
    public final static int TYPE_2_POLICY=7;
    public final static int TYPE_3_CHINA=8;
    public final static int TYPE_3_WORLDWIDE=9;


    public final static int TYPE_ARTICLE=99933;
    public final static int TYPE_SEARCH=99934;


    public String getTag() {
        return tag;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public Bitmap getCover() {
        return cover;
    }

    public String getPageAt() {
        return pageAt;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public boolean hasCover() {
        return hasCover;
    }

    public News(String title_, String publishTime_, String webUrl_, String tag_){
        title=title_;publishTime=publishTime_;webUrl=webUrl_;tag=tag_;
    }

    public void setPageAt(String pageAt) {
        this.pageAt = pageAt;
    }


    private boolean hasCover=false;
    private String pageAt="1";
    private Bitmap cover=null;
    private String tag;
    private String publishTime;
    private String title;
    private String webUrl;
    private String article;
    //private int index=0;

}
