package com.berrysdu.guardly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class No4FgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_frag_layout);

        getSupportFragmentManager().beginTransaction().replace(R.id.backgroundLayout,new No4Fragment()).commit();

    }
}