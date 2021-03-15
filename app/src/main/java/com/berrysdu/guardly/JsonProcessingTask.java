package com.berrysdu.guardly;

import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonProcessingTask extends AsyncTask<HashMap<Integer,String>,Void,HashMap<Integer, ArrayList<News>>> {
    @Override
    protected HashMap<Integer, ArrayList<News>> doInBackground(HashMap<Integer, String>... hashMaps) {
        HashMap<Integer,String> input=hashMaps[0];
        HashMap<Integer, ArrayList<News>> result=new HashMap<>();



        if(input.containsKey(News.TYPE_1_POLITICS)){
            String jsonStr=input.get(News.TYPE_1_POLITICS);
            result.put(2,processStyle1(jsonStr));
            Log.d("processTsk",jsonStr);
        }

        return result;
    }

    @Override
    protected void onPostExecute(HashMap<Integer, ArrayList<News>> result) {

        PgViwerAdapter.fg2List=result.get(2);

        super.onPostExecute(result);
    }

    private ArrayList<News> processStyle1(String json){
        ArrayList<News> result=new ArrayList<>();
        result.add(new News(json,json,json,json));
        return result;
    }
}
