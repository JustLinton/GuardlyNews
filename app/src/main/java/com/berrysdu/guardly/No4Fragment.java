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
 * Use the {@link No4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class No4Fragment extends NoFragmentBase{

    public static boolean nextPgOperate=false;
    public static int fgPage=1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public No4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment No4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static No4Fragment newInstance(String param1, String param2) {
        No4Fragment fragment = new No4Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public void onAttach(@NonNull Context context_) {
        super.onAttach(context_);
        context=context_;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fgList=PgViwerAdapter.fg4List;
        fragView= inflater.inflate(R.layout.fragment_main, container, false);


            startLoader(PgViwerAdapter.fg4Type);


        cleanProgressH();
        return fragView;
    }
    @Override
    public void setAdapter() {

        super.fgList=PgViwerAdapter.fg4List;
        super.setAdapter();

       /* final ListView listView=(ListView)fragView.findViewById(R.id.MainFragListView);
        final ArrayAdapter<News> adapter=new DefaultNewsAdapter(context,PgViwerAdapter.fg4List,1);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();*/

    }
    @Override
    public void plusPage() {
        No4Fragment.fgPage++;
        super.plusPage();
    }
}