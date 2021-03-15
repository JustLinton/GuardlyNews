package com.berrysdu.guardly;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

public class ReadingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Integer> {

    String apiUrl=null;

    Bitmap bitmap=null;
    String article=null;
    NewsSerable news=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);


        try {
            initIntent();
        }catch (NullPointerException npe){
            Toast.makeText(this,"NullPointer",Toast.LENGTH_LONG).show();
        }

        initUI();
        startLoader();
    }

    private void initUI(){
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Reading Article");
        toolbar.setSubtitle(news.getTag());

    }

    private void initIntent() throws NullPointerException{
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        apiUrl=bundle.getString("apiUrl");
        byte[] byteArray=bundle.getByteArray("pic");

        if(byteArray!=null){
            bitmap= BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        }

        news=(NewsSerable) bundle.getSerializable("newsObj");

        Toast.makeText(this,apiUrl,Toast.LENGTH_LONG).show();
    }

    private void startLoader(){
        if(apiUrl!=null){
            MainActivity.loaderNum++;
            getSupportLoaderManager().initLoader(MainActivity.loaderNum,null,this).forceLoad();
            View loadingBar=findViewById(R.id.progress_h);
            loadingBar.setVisibility(View.VISIBLE);
            View artScroll=findViewById(R.id.scrollViewArt);
            artScroll.setVisibility(View.GONE);
        }
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<Integer>(this) {
            @Nullable
            @Override
            public Integer loadInBackground() {
                try {
                    article=new ContentGetter(apiUrl).getJsonStr();
                    article=PgViwerAdapter.getArticle(article);
                }catch (IOException | JSONException e){
                    return 0;
                }

                return 1;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        if(data.equals(1)){
            TextView title=findViewById(R.id.artTitle);
            title.setText(news.getTitle());
            TextView tag=findViewById(R.id.artTag);
            tag.setText(news.getTag());

            tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent intent=new Intent(ReadingActivity.this,TagActivity.class);

                        Bundle queryBundle=new Bundle();
                        queryBundle.putString("qu",news.getTag());

                        intent.putExtras(queryBundle);
                        startActivity(intent);
                }
            });

            TextView date=findViewById(R.id.artDate);
            date.setText(news.getPublishTime());

            TextView para1=findViewById(R.id.articlePara1);
            para1.setText(article);

            if(bitmap!=null){
                ImageView articleImg=(ImageView)findViewById(R.id.articleImg);
                articleImg.setImageBitmap(bitmap);
            }else {
                ImageView articleImg=(ImageView)findViewById(R.id.articleImg);
                articleImg.setVisibility(View.GONE);
            }
        }else {
            Toast.makeText(this,"error.",Toast.LENGTH_LONG).show();
        }


        new Handler().postDelayed(new Runnable(){
            public void run() {
                View loadingBar=findViewById(R.id.progress_h);
                loadingBar.setVisibility(View.GONE);
            }
        }, 4500);

        View artScroll=findViewById(R.id.scrollViewArt);
        artScroll.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.reader_menu,menu);
        return true;
    }
}