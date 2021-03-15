package com.berrysdu.guardly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends NoFragmentBase {

    public static boolean nextPgOperate=false;
    public static int fgPage=1;
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context_) {
        super.onAttach(context_);
        context=context_;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
/*        if(newsList.get(0)!=null){
            Toast.makeText(getContext(),"2-1"+ newsList.get(0).getPublishTime(),Toast.LENGTH_SHORT).show();
        }*/
        //fgList=PgViwerAdapter.fg1List;

        fragView= inflater.inflate(R.layout.fragment_main, container, false);


        startLoader(PgViwerAdapter.fg1Type);


        cleanProgressH();
        return fragView;
    }

    @Override
    public void setAdapter() {

        super.fgList=PgViwerAdapter.fg1List;
        super.setAdapter();

        /*final ListView listView=(ListView)fragView.findViewById(R.id.MainFragListView);
        final ArrayAdapter<News> adapter=new DefaultNewsAdapter(context,PgViwerAdapter.fg1List,1);

        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();*/

    }

    @Override
    public void plusPage() {
        MainFragment.fgPage++;
        super.plusPage();
    }
}