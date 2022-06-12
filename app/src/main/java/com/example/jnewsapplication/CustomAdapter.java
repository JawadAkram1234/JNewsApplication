package com.example.jnewsapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<news_object> news_array = new ArrayList<>();
    Context main_Context;


    public CustomAdapter(ArrayList<news_object> news_list, Context context) {

        this.news_array = news_list;
        this.main_Context = context;

    }

    @Override
    public int getCount() {
        return news_array.size();
    }

    @Override
    public Object getItem(int i) {
        return news_array.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void Share_Intent(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String value = url;
        intent.putExtra(Intent.EXTRA_TEXT, value);
        main_Context.startActivity(Intent.createChooser(intent, "Forward via"));
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(convertView ==null)
        {
            convertView = LayoutInflater.from(main_Context).inflate(R.layout.activity_main,parent,false);
        }
        news_object news_iter = (news_object) getItem(i);
        TextView news_head = (TextView) convertView.findViewById(R.id.Heading_news);
        TextView news_desc = (TextView) convertView.findViewById(R.id.Desc_news);
        TextView news_time = (TextView) convertView.findViewById(R.id.Time_Of_News);
        TextView news_link = (TextView) convertView.findViewById(R.id.Link_Of_News);
        ImageView news_img = (ImageView) convertView.findViewById(R.id.imgarea);

        Button sharebtn = (Button) convertView.findViewById(R.id.sharebtn);
        sharebtn.setVisibility(View.VISIBLE);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Share_Intent(news_iter.get_news_l());
            }
        });


        news_head.setText(""+news_iter.get_news_h());
        news_desc.setText(""+news_iter.get_news_d());
        news_time.setText(""+news_iter.get_news_t());
        news_link.setText(""+news_iter.get_news_l());

        Glide.with(main_Context).load(news_iter.get_image_url()).override(500,400).into(news_img);
        return convertView;
    }
}