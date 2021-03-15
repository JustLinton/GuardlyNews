package com.berrysdu.guardly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

public class TagActivity extends AppCompatActivity {

    private String query="world";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        initIntent();
        initUI();
    }

    private void initIntent(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String qu=bundle.getString("qu");
        query=qu;
    }

    private void initUI(){

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("Tags List");
        toolbar.setSubtitle(query);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragPlaceHolder,STFragment.newInstance(query)).commit();
    }
}