package com.berrysdu.guardly;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PgViwerAdapter extends FragmentPagerAdapter {

    public final static int TYPE_MAIN=0;
    public final static int TYPE_TRUMP=1;
    public final static int TYPE_COVID19=2;

    public static int tabType=0;

    public static int fg1Type=News.TYPE_1_Main;
    public static int fg2Type=News.TYPE_1_POLITICS;
    public static int fg3Type=News.TYPE_1_BUSINESS;
    public static int fg4Type=News.TYPE_1_VEHICLE;
    public static int fgSearchType=News.TYPE_SEARCH;

  /*  public static int fg1Page=1;
    public static int fg2Page=1;
    public static int fg3Page=1;
    public static int fg4Page=1;*/

    public static ArrayList<News> fg1List=new ArrayList<>();
    public static ArrayList<News> fg2List=new ArrayList<>();
    public static ArrayList<News> fg3List=new ArrayList<>();
    public static ArrayList<News> fg4List=new ArrayList<>();
    public static ArrayList<News> fgSearchList=new ArrayList<>();

    public Fragment fg1=null;
    public Fragment fg2=new No2Fragment();
    public Fragment fg3=new No3Fragment();
    public Fragment fg4=new No4Fragment();


    private int type = TYPE_MAIN;
    public PgViwerAdapter(@NonNull FragmentManager fm,int type_) {
        super(fm);
        type=type_;
        fg1= new MainFragment();

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getMyTitle(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return getMyItem(position);
    }


    @Override
    public int getCount() {
        return getMyCount();
    }

    private int getMyCount(){
        switch (type){
            case TYPE_MAIN:
                return 4;
            case TYPE_TRUMP:
                return 3;
            case TYPE_COVID19:
            default:
                return 2;
        }
    }

    private Fragment getMyItem(int position){
        switch (type){
            case TYPE_MAIN:
                switch (position){
                    case 0:return new MainFragment();
                    case 1: return new No2Fragment();
                    case 2:return new No3Fragment();
                    case 3:return new No4Fragment();
                }

            case TYPE_TRUMP:
                switch (position){
                    case 0: return new MainFragment();
                    case 1: return new No2Fragment();
                    case 2:return new No3Fragment();
                }

            case TYPE_COVID19:
                switch (position){
                    case 0:return new MainFragment();
                    case 1:return new No2Fragment();
                }

            default:
                return new ErrorFragment();
        }
    }

    private CharSequence getMyTitle(int position) {
        switch (type){
            case TYPE_MAIN:
                switch (position){
                    case 0:return "Main";
                    case 1:return "Politics";
                    case 2:return "Business";
                    case 3:return "Cars";
                }
            case TYPE_TRUMP:
                switch (position){
                    case 0:return "Trump";
                    case 1:return "America";
                    case 2:return "Policy";
                }
            case TYPE_COVID19:
                switch (position){
                    case 0:return "Virus";
                    case 1:return "WorldWide";
                }
            default:
                return null;
        }
    }

    public static String getArticle(String json)throws JSONException,NullPointerException {
        JSONObject jsonObject = new JSONObject(json);

        JSONObject rootObject = jsonObject.getJSONObject("response");
        JSONObject contentObj=rootObject.getJSONObject("content");
        JSONObject fieldsObj=contentObj.getJSONObject("fields");
        String divBody=fieldsObj.getString("body");
        Log.e("body:",divBody);


        return stripHT(divBody);
    }

    private static String stripHT(String strHtml) {
        String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符
        return txtcontent;
    }

    public static ArrayList<News> getNews(String json,int type)throws JSONException,IOException,NullPointerException {
        JSONObject jsonObject=new JSONObject(json);
        ArrayList<News> newsList=new ArrayList<>();

        JSONObject rootObject=jsonObject.getJSONObject("response");
        String status=rootObject.getString("status");
        String pageAt=rootObject.getString("currentPage");

        if(status.equals("ok")){
            int count=rootObject.getInt("pageSize");
            JSONArray resultArray=rootObject.getJSONArray("results");

            for(int i=0;i<=count-1;i++){
                JSONObject resultObj=resultArray.getJSONObject(i);
                String title=resultObj.getString("webTitle");
                String date=resultObj.getString("webPublicationDate").substring(0,10);
                String url=resultObj.getString("apiUrl");
                String tag=resultObj.getString("sectionName");

                News news=new News(title,date,url,tag);



                JSONObject fieldObj=resultObj.getJSONObject("fields");
                String body=fieldObj.getString("body");
                int imgSrcIndexBegin=  body.indexOf("<img");

                news.setPageAt(pageAt);
                //news.setIndex(i);

                if(imgSrcIndexBegin!=-1){
                    String imgBeginSubStr=body.substring(imgSrcIndexBegin,body.length()-1);
                    int imgSrcIndexEnd=  imgBeginSubStr.indexOf("jpg");
                    String imgUrl=imgBeginSubStr.substring(0,imgSrcIndexEnd)+"jpg";
                    imgUrl = imgUrl.replace("<img src=\"","");
                    news.setHasCover(true);

                    try {
                        news.setCover(getBitmap(imgUrl));
                    }catch (FileNotFoundException e){
                        news.setHasCover(false);
                    }

                }



                newsList.add(news);
            }



            switch (type){
                case News.TYPE_2_TRUMP:
                    case News.TYPE_1_Main:
                        case News.TYPE_3_CHINA:
                MainFragment.fgPage=rootObject.getInt("currentPage");
                break;
                case News.TYPE_1_POLITICS:
                    case News.TYPE_2_AMERICA:
                case News.TYPE_3_WORLDWIDE:
                    No2Fragment.fgPage=rootObject.getInt("currentPage");
                        break;
                case News.TYPE_1_BUSINESS:
                    case News.TYPE_2_POLICY:
                        No3Fragment.fgPage=rootObject.getInt("currentPage");
                        break;
                        case News.TYPE_1_VEHICLE:
                            No4Fragment.fgPage=rootObject.getInt("currentPage");
                            break;
                            case News.TYPE_SEARCH:
                                STFragment.fgPage=rootObject.getInt("currentPage");
                                break;

            }

        }else {
            throw new JSONException(json);
        }

        return newsList;
    }

    private static Bitmap getBitmap(String url_)throws IOException {
        URL url=new URL(url_);
        Bitmap bitmap;

        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(10000);
        urlConnection.setReadTimeout(12000);
        urlConnection.connect();
        InputStream inputStream=urlConnection.getInputStream();
        bitmap = BitmapFactory.decodeStream(inputStream);

        return bitmap;
    }
}
