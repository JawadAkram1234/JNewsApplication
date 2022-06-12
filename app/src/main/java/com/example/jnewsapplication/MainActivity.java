package com.example.jnewsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imagearea = findViewById(R.id.imgarea);

        OkHttpClient client = new OkHttpClient();
        String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            private Call call;
            private IOException e;

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                this.call = call;
                this.e = e;
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ListView listV = findViewById(R.id.listV);
                                ArrayList<news_object> news_array = new ArrayList<>();
                                JSONObject jreader = new JSONObject(myResponse);
                                JSONArray all_data = jreader.getJSONArray("data");
                                for (int i = 0; i < all_data.length(); i++) {
                                    JSONObject news_instance = all_data.getJSONObject(i);
                                    String image_url = news_instance.getString("url");
                                    String h = news_instance.getString("heading");
                                    String l = news_instance.getString("reference");
                                    String d = news_instance.getString("description");
                                    String t = news_instance.getString("time");
                                    news_object instance_object = new news_object();
                                    instance_object.set_news_h(h);
                                    instance_object.set_news_l(l);
                                    instance_object.set_news_d(d);
                                    instance_object.set_image_url(image_url);
                                    instance_object.set_news_t(t);
                                    news_array.add(instance_object);
                                    CustomAdapter mycusAdapter = new CustomAdapter(news_array, MainActivity.this);
                                    listV.setAdapter(mycusAdapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}