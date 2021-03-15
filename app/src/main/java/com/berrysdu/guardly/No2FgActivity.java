package com.berrysdu.guardly;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class No2FgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_frag_layout);

        getSupportFragmentManager().beginTransaction().replace(R.id.backgroundLayout,new No2Fragment()).commit();

    }

}