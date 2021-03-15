package com.berrysdu.guardly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class No3FgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_frag_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.backgroundLayout,new No3Fragment()).commit();
    }
}