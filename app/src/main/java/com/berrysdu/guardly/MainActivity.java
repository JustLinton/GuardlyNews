package com.berrysdu.guardly;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    public static int type=News.TYPE_1_POLITICS;
    static Context context;
    public static int loaderNum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGUI();
        initTabFrag();
        context=this;

       // runLoader(1);
        //startActivity(new Intent(this,No2FgActivity.class));
    }


   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_top_right_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView=(androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle=new Bundle();
                bundle.putString("qu",query);
                bundle.putBoolean("firstTime",false);
                Intent intent=new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void initGUI(){
        androidx.appcompat.widget.Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView=(NavigationView)findViewById(R.id.main_side_drawer_naviView);
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.main_side_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();
        toolbar.setSubtitle(new FormatManager().getCurMonthMMM()+" "+new FormatManager().getCurDayDD());



        toolbar.inflateMenu(R.menu.main_top_right_menu);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.side_search:
                        Bundle bundle=new Bundle();
                        bundle.putBoolean("firstTime",true);
                        Intent intent=new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.side_main_menu:
                        onClickMain();
                        break;
                    case R.id.T1_I1:
                        onClickTrump();
                        break;
                    case R.id.T1_I2:
                        onClickCovid19();
                        break;
                    default:
                        Toast.makeText(MainActivity.this,"default.",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.help:
                        Toast.makeText(MainActivity.this,"otherHelp1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.new_game22:
                        Toast.makeText(MainActivity.this,"otherNg22",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this,"otherDefault.",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void initTabFrag(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragPlaceHolder,new HomeFragment()).commit();
    }

    private void onClickMain(){
        if(PgViwerAdapter.fg1Type!=News.TYPE_1_Main){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragPlaceHolder,new HomeFragment()).commit();
            PgViwerAdapter.fg1Type=News.TYPE_1_Main;
            PgViwerAdapter.fg2Type=News.TYPE_1_POLITICS;
            PgViwerAdapter.fg3Type=News.TYPE_1_BUSINESS;
            PgViwerAdapter.fg4Type=News.TYPE_1_VEHICLE;
            MainFragment.fgPage=1;
            No2Fragment.fgPage=1;
            No3Fragment.fgPage=1;
            No4Fragment.fgPage=1;
            MainFragment.nextPgOperate=false;
            No2Fragment.nextPgOperate=false;
            No3Fragment.nextPgOperate=false;
            No4Fragment.nextPgOperate=false;
            androidx.appcompat.widget.Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
            toolbar.setSubtitle(new FormatManager().getCurMonthMMM()+" "+new FormatManager().getCurDayDD());
            closeDrawer();

        }else {
            Toast.makeText(this,"Have been here.",Toast.LENGTH_SHORT).show();
        }
    }


    private void onClickTrump(){
        if(PgViwerAdapter.fg1Type!=News.TYPE_2_TRUMP){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragPlaceHolder,new TrumpFragment()).commit();
        PgViwerAdapter.fg1Type=News.TYPE_2_TRUMP;
        PgViwerAdapter.fg2Type=News.TYPE_2_AMERICA;
        PgViwerAdapter.fg3Type=News.TYPE_2_POLICY;
            MainFragment.fgPage=1;
            No2Fragment.fgPage=1;
            No3Fragment.fgPage=1;
            No4Fragment.fgPage=1;
            MainFragment.nextPgOperate=false;
            No2Fragment.nextPgOperate=false;
            No3Fragment.nextPgOperate=false;
            No4Fragment.nextPgOperate=false;
            androidx.appcompat.widget.Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
            toolbar.setSubtitle("Learning about Trump");
            closeDrawer();
        }else {
            Toast.makeText(this,"Have been here.",Toast.LENGTH_SHORT).show();
        }
    }

    private void onClickCovid19(){
        if(PgViwerAdapter.fg1Type!=News.TYPE_3_CHINA){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragPlaceHolder,new Covid19Fragment()).commit();
        PgViwerAdapter.fg1Type=News.TYPE_3_CHINA;
        PgViwerAdapter.fg2Type=News.TYPE_3_WORLDWIDE;
            MainFragment.fgPage=1;
            No2Fragment.fgPage=1;
            No3Fragment.fgPage=1;
            No4Fragment.fgPage=1;
            MainFragment.nextPgOperate=false;
            No2Fragment.nextPgOperate=false;
            No3Fragment.nextPgOperate=false;
            No4Fragment.nextPgOperate=false;
            androidx.appcompat.widget.Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
            toolbar.setSubtitle("Learning about Covid-19");
            closeDrawer();
        }else {
            Toast.makeText(this,"Have been here.",Toast.LENGTH_SHORT).show();
        }
    }




    private void closeDrawer(){
       // NavigationView navigationView=(NavigationView)findViewById(R.id.main_side_drawer_naviView);
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.main_side_drawer);
        drawerLayout.closeDrawers();
    }


        /*private void runLoader(int i){
        LoaderManager loaderManager= getSupportLoaderManager();
        loaderManager.initLoader(i,null,this).forceLoad();
    }*/

    /*private void initTab(int type){
        PagerAdapter pgAdapter=new PgViwerAdapter(getSupportFragmentManager(),type);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.mainTabLayout);
        ViewPager pager=(ViewPager)findViewById(R.id.MainPgViwer);

        pager.setAdapter(pgAdapter);
        tabLayout.setupWithViewPager(pager);
        Log.i("trump",PgViwerAdapter.tabType+"");
    }*/


   /* @NonNull
    @Override
    public Loader<HashMap<Integer, ArrayList<News>>> onCreateLoader(int id, @Nullable Bundle args) {

        return new AsyncTaskLoader<HashMap<Integer, ArrayList<News>>>(this) {

            @Nullable
            @Override
            public HashMap<Integer, ArrayList<News>> loadInBackground() {
                HashMap<Integer,String> result=new HashMap<>();

                switch (PgViwerAdapter.fg2Type){
                    case News.TYPE_1_POLITICS:
                            result.put(type,new ContentGetter(type).getJsonStr());
                        case News.TYPE_2_AMERICA:
                            result.put(type,new ContentGetter(type).getJsonStr());

                }


                HashMap<Integer,String> input = result;
                HashMap<Integer, ArrayList<News>> rss=new HashMap<>();



                if(input.containsKey(News.TYPE_1_POLITICS)){
                    ArrayList<News> newsList=new ArrayList<>();
                    String jsonStr=input.get(News.TYPE_1_POLITICS);
                    newsList.add(new News(jsonStr,jsonStr,jsonStr,jsonStr));

                    Log.d("processTsk",jsonStr);
                    PgViwerAdapter.fg2List=newsList;

                    rss.put(2,newsList);
                }

                if(input.containsKey(News.TYPE_2_AMERICA)){
                    ArrayList<News> newsList=new ArrayList<>();
                    String jsonStr=input.get(News.TYPE_2_AMERICA);
                    newsList.add(new News("jsonStr","jsonStr","jsonStr","jsonStr"));

                    Log.d("processTsk",jsonStr);
                    PgViwerAdapter.fg2List=newsList;

                    rss.put(2,newsList);
                }




                return rss;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<HashMap<Integer, ArrayList<News>>> loader, HashMap<Integer, ArrayList<News>> data) {
        //initTab(PgViwerAdapter.tabType);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<HashMap<Integer, ArrayList<News>>> loader) {

    }


    private ArrayList<News> processStyle1(String json){
        ArrayList<News> result=new ArrayList<>();
        result.add(new News(json,json,json,json));
        return result;
    }*/


  /*  private void onClickTrump(){
        if(PgViwerAdapter.fg2Type!=News.TYPE_2_AMERICA){
            PgViwerAdapter.fg2Type=News.TYPE_2_AMERICA;
            PgViwerAdapter.tabType=PgViwerAdapter.TYPE_TRUMP;
            setContentView(R.layout.activity_reading);
            setContentView(R.layout.activity_no2_fg);
            runLoader(2);
            initGUI();
            Log.i("trump",PgViwerAdapter.tabType+"");
        }
    }*/


    /*public void testOpen(View view){
        startActivity(new Intent(this,ReadingActivity.class));
    }*/
}