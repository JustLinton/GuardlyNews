package com.berrysdu.guardly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ContentGetter {


    private int type=1;
    private String json=null;


    public ContentGetter(String query,int searchType)throws IOException{

        HttpURLConnection connection=(HttpURLConnection) urlBuilder(query,searchType).openConnection();
        connection.setReadTimeout(12000);
        connection.setConnectTimeout(10000);
        connection.connect();
        InputStream inputStream=connection.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result=new StringBuilder();
        String line=null;
        while ((line=reader.readLine())!=null){
            result.append(line);
            json=result.toString();
        }

    }

    public ContentGetter(int type_)throws IOException{
        type=type_;


                HttpURLConnection connection=(HttpURLConnection) urlBuilder().openConnection();
                connection.setReadTimeout(12000);
                connection.setConnectTimeout(10000);
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result=new StringBuilder();
                String line=null;
                while ((line=reader.readLine())!=null){
                    result.append(line);
                    json=result.toString();
                }

    }

    public ContentGetter(String articleApi)throws IOException{

        HttpURLConnection connection=(HttpURLConnection) urlBuilder(articleApi).openConnection();
        connection.setReadTimeout(12000);
        connection.setConnectTimeout(10000);
        connection.connect();
        InputStream inputStream=connection.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result=new StringBuilder();
        String line=null;
        while ((line=reader.readLine())!=null){
            result.append(line);
            json=result.toString();
        }

    }

    public String getJsonStr(){
        return json;
    }

    private URL urlBuilder() throws MalformedURLException{
        final String contentUrl="https://content.guardianapis.com/search?show-fields=body&page-size=20";
        final String apiKey="&api-key=81454f0e-b178-4bb5-a8f4-5ff0c74dce72";
        final String query="&q=";
        final String page="&page=";


            switch (type){
                    case News.TYPE_1_Main:
                    return new URL(contentUrl+page+MainFragment.fgPage+apiKey);
                    case News.TYPE_1_POLITICS:
                        return new URL(contentUrl+page+No2Fragment.fgPage+query+"politics"+apiKey);
                case News.TYPE_1_BUSINESS:
                    return new URL(contentUrl+page+No3Fragment.fgPage+query+"business"+apiKey);
                case News.TYPE_1_VEHICLE:
                    return new URL(contentUrl+page+No4Fragment.fgPage+query+"car"+apiKey);
                case News.TYPE_2_TRUMP:
                    return new URL(contentUrl+page+MainFragment.fgPage+query+"trump"+apiKey);
                    case News.TYPE_2_AMERICA:
                        return new URL(contentUrl+page+No2Fragment.fgPage+query+"america"+apiKey);
                case News.TYPE_2_POLICY:
                    return new URL(contentUrl+page+No3Fragment.fgPage+query+"policy"+apiKey);
                    case News.TYPE_3_CHINA:
                        return new URL(contentUrl+page+MainFragment.fgPage+query+"coronavirus"+ apiKey);
                case News.TYPE_3_WORLDWIDE:
                    return new URL(contentUrl+page+No2Fragment.fgPage+query+"covid"+ apiKey);
                default:
                    return null;
            }
    }


    private URL urlBuilder(String articleApi)throws MalformedURLException{
        final String apiKey="&api-key=81454f0e-b178-4bb5-a8f4-5ff0c74dce72";
        final String articleHead="?show-fields=body";
            return new URL(articleApi+articleHead+apiKey);
    }

    private URL urlBuilder(String query,int searchType)throws MalformedURLException{
        final String apiKey="&api-key=81454f0e-b178-4bb5-a8f4-5ff0c74dce72";
        final String contentUrl="https://content.guardianapis.com/search?show-fields=body&page-size=20";
        final String q="&q=";
        final String page="&page=";

        String codedQuery =URLEncoder.encode(query);

        return new URL(contentUrl+page+STFragment.fgPage +q + codedQuery+ apiKey);
    }
}
