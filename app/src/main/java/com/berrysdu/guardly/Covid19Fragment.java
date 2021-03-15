package com.berrysdu.guardly;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Covid19Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Covid19Fragment extends Fragment {

    private View fragView=null;

    public Covid19Fragment() {
        // Required empty public constructor
    }
    public static Covid19Fragment newInstance(String param1, String param2) {
        Covid19Fragment fragment = new Covid19Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragView= inflater.inflate(R.layout.fragment_covid19, container, false);

        //startLoader();
        initTab();
        return fragView;
    }

    private void initTab(){
        PagerAdapter pgAdapter=new PgViwerAdapter(getChildFragmentManager(),PgViwerAdapter.TYPE_COVID19);
        TabLayout tabLayout=(TabLayout)fragView.findViewById(R.id.mainTabLayout);
        ViewPager pager=(ViewPager)fragView.findViewById(R.id.MainPgViwer);

        pager.setAdapter(pgAdapter);
        tabLayout.setupWithViewPager(pager);
        //Log.i("trump",PgViwerAdapter.tabType+"");
    }


    /*private void startLoader(){
        getLoaderManager().initLoader(1,null,this).forceLoad();
    }*/


    /*@NonNull
    @Override
    public Loader<HashMap<Integer, ArrayList<News>>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<HashMap<Integer, ArrayList<News>>>(getContext()) {
            @Nullable
            @Override
            public HashMap<Integer, ArrayList<News>> loadInBackground() {
                Log.d("trumpFrag2","starting loading..");
                Log.d("trumpFrag2","starting loading..");

                HashMap<Integer,ArrayList<News>> result = new HashMap<>();

                ArrayList<News> tab1NewsList=new ArrayList<>();
                ArrayList<News> tab2NewsList=new ArrayList<>();
                ArrayList<News> tab3NewsList=new ArrayList<>();

                String tab1Json=new ContentGetter(News.TYPE_3_CHINA).getJsonStr();
                //TODO Add more gettings.

                //TODO process Str and put in Arrays.

                tab1NewsList.add(new News(tab1Json,tab1Json,tab1Json,tab1Json));

                result.put(News.TYPE_3_CHINA,tab1NewsList);

                return result;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<HashMap<Integer, ArrayList<News>>> loader, HashMap<Integer, ArrayList<News>> data) {
        Log.d("trumpFrag2"," loading.. finshed");
        Log.d("trumpFrag2"," loading.. finshed");

        PgViwerAdapter.fg3List=data.get(News.TYPE_3_CHINA);
        initTab();
        Toast.makeText(getContext(),PgViwerAdapter.fg3List.get(0).getPublishTime(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<HashMap<Integer, ArrayList<News>>> loader) {}*/
}