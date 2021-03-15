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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment{
    public HomeFragment() {
        // Required empty public constructor
    }

    View fragView=null;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView= inflater.inflate(R.layout.fragment_home, container, false);
        //startLoader();
        initTab();
        return fragView;
    }

    private void initTab(){
        PagerAdapter pgAdapter=new PgViwerAdapter(getChildFragmentManager(),PgViwerAdapter.TYPE_MAIN);
        TabLayout tabLayout=(TabLayout)fragView.findViewById(R.id.mainTabLayout);
        ViewPager pager=(ViewPager)fragView.findViewById(R.id.MainPgViwer);
        if(pager.getAdapter()!=null){pager.setAdapter(null);}

        pager.setAdapter(pgAdapter);
        tabLayout.setupWithViewPager(pager);
        Log.i("trump",PgViwerAdapter.tabType+"");
    }


   /* private void startLoader(){
        getLoaderManager().initLoader(1,null,this).forceLoad();
    }


    @NonNull
    @Override
    public Loader<HashMap<Integer, ArrayList<News>>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<HashMap<Integer, ArrayList<News>>>(getContext()) {
            @Nullable
            @Override
            public HashMap<Integer, ArrayList<News>> loadInBackground() {
                HashMap<Integer,ArrayList<News>> result = new HashMap<>();

                ArrayList<News> tab1NewsList=new ArrayList<>();
                ArrayList<News> tab2NewsList=new ArrayList<>();
                ArrayList<News> tab3NewsList=new ArrayList<>();
                ArrayList<News> tab4NewsList=new ArrayList<>();

                String tab1Json=new ContentGetter(News.TYPE_1_POLITICS).getJsonStr();
                //TODO Add more gettings.

                //TODO process Str and put in Arrays.

                tab1NewsList.add(new News(tab1Json,tab1Json,tab1Json,tab1Json));

                result.put(News.TYPE_1_POLITICS,tab1NewsList);

                return result;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<HashMap<Integer, ArrayList<News>>> loader, HashMap<Integer, ArrayList<News>> data) {
        PgViwerAdapter.fg2List=data.get(News.TYPE_1_POLITICS);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<HashMap<Integer, ArrayList<News>>> loader) {}*/
}