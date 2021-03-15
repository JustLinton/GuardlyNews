package com.berrysdu.guardly;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DefaultNewsAdapter extends ArrayAdapter<News> {
    private int type;
    private String tag="tag";

    public DefaultNewsAdapter(@NonNull Context context,ArrayList<News> news,int type_) {
        super(context, 0, news);
        type=type_;
    }

    public DefaultNewsAdapter(@NonNull Context context,ArrayList<News> news,int type_,String tag_) {
        super(context, 0, news);
        type=type_;
        tag=tag_;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listContentView=convertView;
        News news=getItem(position);


        if(convertView==null){
            listContentView= LayoutInflater.from(getContext()).inflate(R.layout.default_news_adapter_layout,parent,false);
        }


        listContentView.findViewById(R.id.titleKit).setVisibility(View.VISIBLE);
        listContentView.findViewById(R.id.nextPgKit).setVisibility(View.GONE);

        try {
            TextView titleview=(TextView)listContentView.findViewById(R.id.perNewsMainTitle);
            String str=news.getTitle();
            titleview.setText(str);
            TextView tagview=(TextView)listContentView.findViewById(R.id.perNewsTag);
            String strTag=news.getTag();

            if(type==News.TYPE_SEARCH){
                tagview.setText(tag);
                tagview.setBackgroundColor(getContext().getColor(R.color.colorTagSearch));
            }else {
                tagview.setText(strTag);
            }

            TextView dateview=(TextView)listContentView.findViewById(R.id.perNewsDate);
            String strDate=news.getPublishTime();
            dateview.setText(strDate);
        }catch (NullPointerException e){
            e.printStackTrace();
        }


        try {
            if(news.hasCover()){
                CircleImageView imageView=listContentView.findViewById(R.id.perNewsImg);
                imageView.setVisibility(View.VISIBLE);
                imageView.setBorderWidth(0);
                imageView.setImageBitmap(news.getCover());
            }else {
                CircleImageView imageView=listContentView.findViewById(R.id.perNewsImg);
                imageView.setVisibility(View.GONE);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(position==getLastPosition()){

            listContentView.findViewById(R.id.titleKit).setVisibility(View.GONE);
            listContentView.findViewById(R.id.nextPgKit).setVisibility(View.VISIBLE);
            View pageView=listContentView.findViewById(R.id.nextPgKit);
            TextView pageNum=pageView.findViewById(R.id.pageNum);
            Log.d("page",news.getPageAt());
            pageNum.setText("Page "+news.getPageAt());

        }

        return listContentView;
    }

    private int getLastPosition(){
        switch (type){
            default:return 19;
        }
    }
}
