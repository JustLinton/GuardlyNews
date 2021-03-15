package com.berrysdu.guardly;

import android.content.Context;
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
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link No2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class No2Fragment extends NoFragmentBase{

    public static boolean nextPgOperate=false;
    public static int fgPage=1;
    private static ArrayList<News> newsList=PgViwerAdapter.fg2List;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public No2Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static No2Fragment newInstance(int type_) {
        No2Fragment fragment = new No2Fragment();
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context_) {
        super.onAttach(context_);
        context=context_;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*        if(newsList.get(0)!=null){
            Toast.makeText(getContext(),"2-2"+ newsList.get(0).getPublishTime(),Toast.LENGTH_SHORT).show();
        }*/

            fgList=PgViwerAdapter.fg2List;
            fragView = inflater.inflate(R.layout.fragment_main, container, false);

            startLoader(PgViwerAdapter.fg2Type);


            cleanProgressH();
            return fragView;
    }
    @Override
    public void setAdapter() {

        super.fgList=PgViwerAdapter.fg2List;
        super.setAdapter();

     /*   final ListView listView=(ListView)fragView.findViewById(R.id.MainFragListView);
        final ArrayAdapter<News> adapter=new DefaultNewsAdapter(context,PgViwerAdapter.fg2List,1);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();*/

    }
    @Override
    public void plusPage() {
        No2Fragment.fgPage++;
        super.plusPage();
    }

}