package com.berrysdu.guardly;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link STFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class STFragment extends NoFragmentBase {

    public static boolean nextPgOperate=false;
    public static int fgPage=1;
    private static String query="world";
    private Bundle bundleQue=null;

    public STFragment() {
        // Required empty public constructor
    }

    public static STFragment newInstance(String query_) {
        STFragment fragment = new STFragment();
        query=query_;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void startLoader(int type) {
        Bundle bundle=new Bundle();
        bundle.putString("query",query);

        bundleQue=bundle;

        super.bundleArg=bundle;

        super.startLoader(type);
    }

    @Override
    public void onAttach(@NonNull Context context_) {
        super.onAttach(context_);
        context=context_;
    }

    @Override
    public void onPause() {
        super.onPause();
        fgPage=1;
        nextPgOperate=false;
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


        startLoader(News.TYPE_SEARCH);


        cleanProgressH();
        return fragView;
    }

    @Override
    public void setAdapter() {

        super.bundleArg=bundleQue;
        super.fgType=News.TYPE_SEARCH;
        super.fgList=PgViwerAdapter.fgSearchList;
        super.setAdapter();

        /*final ListView listView=(ListView)fragView.findViewById(R.id.MainFragListView);
        final ArrayAdapter<News> adapter=new DefaultNewsAdapter(context,PgViwerAdapter.fg1List,1);

        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();*/

    }

    @Override
    public void plusPage() {
        STFragment.fgPage++;

        super.bundleArg=bundleQue;
        super.plusPage();
    }
}