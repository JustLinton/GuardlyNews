package com.berrysdu.guardly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    private String query="world";
    private boolean firstTime=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initIntent();
        initUI();
    }

    private void initIntent(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String qu=bundle.getString("qu");
        firstTime=bundle.getBoolean("firstTime");
        query=qu;
    }

    private void initUI(){

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Search");

        if(!firstTime){
            View indictaor=findViewById(R.id.searchIndicator);
            indictaor.setVisibility(View.GONE);
        }

        if(!firstTime){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragPlaceHolder,STFragment.newInstance(query)).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_top_right_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView=(androidx.appcompat.widget.SearchView) searchItem.getActionView();

        if(!firstTime){
            searchView.setIconified(false);
            searchView.setQuery(query,false);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String queSub) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragPlaceHolder,STFragment.newInstance(queSub)).commit();
                View indictaor=findViewById(R.id.searchIndicator);
                indictaor.setVisibility(View.GONE);
                Toolbar toolbar=findViewById(R.id.toolbar);
                toolbar.setSubtitle("For "+queSub);
                STFragment.fgPage=1;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }
}