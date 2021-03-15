package com.berrysdu.guardly;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoFragmentBase#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoFragmentBase extends Fragment implements LoaderManager.LoaderCallbacks<Integer>{


    public static boolean nextPgOperate=false;
    public int fgType=0;
    public ArrayList<News> fgList=null;
    public static int fgPage;
    View fragView=null;
    Bundle bundleArg=null;
    Context context;

    public NoFragmentBase() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoFragmentBase.
     */
    // TODO: Rename and change types and number of parameters
    public static NoFragmentBase newInstance(String param1, String param2) {
        NoFragmentBase fragment = new NoFragmentBase();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_no_base, container, false);
    }

    public void setAdapter() {
        final ListView listView=(ListView)fragView.findViewById(R.id.MainFragListView);
        listView.setEmptyView(fragView.findViewById(R.id.emptyGroupIndicator));

        //final ArrayAdapter<News> adapter=new DefaultNewsAdapter(context,fgList,1);

        //listView.setAdapter(adapter);

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                listView.setAdapter(adapter);
                Toast.makeText(getContext(),"Handler setted.",Toast.LENGTH_SHORT).show();
            }
        },2000);*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==19){
                    plusPage();
                }else {

                    final TextView tagView=view.findViewById(R.id.perNewsTag);
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(fgType!=News.TYPE_SEARCH){
                                Intent intent=new Intent(getContext(),TagActivity.class);

                                Bundle queryBundle=new Bundle();
                                queryBundle.putString("qu",tagView.getText().toString());

                                intent.putExtras(queryBundle);
                                startActivity(intent);
                            }
                        }
                    });


                    News news=fgList.get(position);
                    Bundle bundle=new Bundle();
                    bundle.putString("apiUrl",news.getWebUrl());
                    bundle.putSerializable("newsObj",new NewsSerable(news));

                    if(news.hasCover()){
                        Bitmap bitmap=news.getCover();
                        byte[] bArray=bitmap2Bytes(bitmap);
                        bundle.putByteArray("pic",bArray);
                    }

                    Intent intent=new Intent(getContext(),ReadingActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        if(fgType==News.TYPE_SEARCH){
            final ArrayAdapter<News> adapter=new DefaultNewsAdapter(context,fgList,News.TYPE_SEARCH,bundleArg.getString("query"));
            listView.setAdapter(adapter);
        }else {
            final ArrayAdapter<News> adapter=new DefaultNewsAdapter(context,fgList,1);
            listView.setAdapter(adapter);
        }

        listView.deferNotifyDataSetChanged();
    }

    private byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public void startLoader(int type){
        MainActivity.loaderNum++;
        fgType=type;

        getLoaderManager().initLoader( MainActivity.loaderNum,bundleArg,this).forceLoad();
    }


    public void plusPage(){
        nextPgOperate=true;
        MainActivity.loaderNum++;
        fgPage++;
        getLoaderManager().initLoader( MainActivity.loaderNum,bundleArg,this).forceLoad();
        View progressBarHorizen=fragView.findViewById(R.id.progress_h);
        progressBarHorizen.setVisibility(View.VISIBLE);
        progressBarHorizen.bringToFront();
    }


    public void cleanProgressH(){
        View progressBarHorizen=fragView.findViewById(R.id.progress_h);
        progressBarHorizen.setVisibility(View.GONE);
    }
    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable final Bundle args) {
        if(!nextPgOperate){
            View progressBar=fragView.findViewById(R.id.loadingPage);
            progressBar.setVisibility(View.VISIBLE);
            cleanProgressH();
        }

        View errView=fragView.findViewById(R.id.errorGroup);
        errView.setVisibility(View.GONE);
        Button retryBtn=(Button)fragView.findViewById(R.id.retryBtn);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoader(fgType);
                View progressBar=fragView.findViewById(R.id.loadingPage);
                progressBar.setVisibility(View.VISIBLE);
                cleanProgressH();
            }
        });


        return new AsyncTaskLoader<Integer>(context) {
            @Nullable
            @Override
            public Integer loadInBackground() {

                ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info=cm.getActiveNetworkInfo();
                if(info!=null&&info.isConnected()){
                    int k=3;
                }else {
                    return 0;
                }

                try {
                    switch (fgType){
                        case News.TYPE_1_Main:
                            String json=new ContentGetter(News.TYPE_1_Main).getJsonStr();
                            PgViwerAdapter.fg1List = PgViwerAdapter.getNews(json,News.TYPE_1_Main);
                            break;
                        case News.TYPE_2_TRUMP:
                            String json2=new ContentGetter(News.TYPE_2_TRUMP).getJsonStr();
                            PgViwerAdapter.fg1List= PgViwerAdapter.getNews(json2,News.TYPE_2_TRUMP);
                            break;
                        case News.TYPE_3_CHINA:
                            String json3=new ContentGetter(News.TYPE_3_CHINA).getJsonStr();
                            PgViwerAdapter.fg1List=PgViwerAdapter.getNews(json3,News.TYPE_3_CHINA);
                            break;
                        case News.TYPE_1_BUSINESS:
                            String json4=new ContentGetter(News.TYPE_1_BUSINESS).getJsonStr();
                            PgViwerAdapter.fg3List= PgViwerAdapter.getNews(json4,News.TYPE_1_BUSINESS);
                            break;
                        case News.TYPE_2_POLICY:
                            String json5=new ContentGetter(News.TYPE_2_POLICY).getJsonStr();
                            PgViwerAdapter.fg3List= PgViwerAdapter.getNews(json5,News.TYPE_2_POLICY);
                            break;
                        case News.TYPE_1_VEHICLE:
                            String jsonv=new ContentGetter(News.TYPE_1_VEHICLE).getJsonStr();
                            PgViwerAdapter.fg4List= PgViwerAdapter.getNews(jsonv,News.TYPE_1_VEHICLE);
                            break;
                        case News.TYPE_1_POLITICS:
                            String jsonp=new ContentGetter(News.TYPE_1_POLITICS).getJsonStr();
                            PgViwerAdapter.fg2List= PgViwerAdapter.getNews(jsonp,News.TYPE_1_POLITICS);
                            break;
                        case News.TYPE_2_AMERICA:
                            String jsonaam=new ContentGetter(News.TYPE_2_AMERICA).getJsonStr();
                            PgViwerAdapter.fg2List= PgViwerAdapter.getNews(jsonaam,News.TYPE_2_AMERICA);
                            break;
                        case News.TYPE_3_WORLDWIDE:
                            String jsonwd=new ContentGetter(News.TYPE_3_WORLDWIDE).getJsonStr();
                            PgViwerAdapter.fg2List=PgViwerAdapter.getNews(jsonwd,News.TYPE_3_WORLDWIDE);
                            break;
                        case News.TYPE_SEARCH:
                            if(args!=null){
                                String jsonsc=new ContentGetter(args.getString("query"),News.TYPE_SEARCH).getJsonStr();
                                PgViwerAdapter.fgSearchList=PgViwerAdapter.getNews(jsonsc,News.TYPE_SEARCH);
                            }else {
                                String jsonsc=new ContentGetter("world",News.TYPE_SEARCH).getJsonStr();
                                PgViwerAdapter.fgSearchList=PgViwerAdapter.getNews(jsonsc,News.TYPE_SEARCH);
                            }
                            break;

                    }
                }catch (JSONException | IOException | NullPointerException e ){
                    e.printStackTrace();
                    return 0;
                }
                return 1;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        View errView=fragView.findViewById(R.id.errorGroup);
        errView.setVisibility(View.GONE);
        View progressBar=fragView.findViewById(R.id.loadingPage);
        progressBar.setVisibility(View.GONE);
        View progressBarHorizen=fragView.findViewById(R.id.progress_h);
        progressBarHorizen.setVisibility(View.GONE);

        if(nextPgOperate){nextPgOperate=false;}

        if(data==1){
            setAdapter();

        }else {
            View emptyView=fragView.findViewById(R.id.emptyGroupIndicator);
            emptyView.setVisibility(View.GONE);

            errView.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {}
}