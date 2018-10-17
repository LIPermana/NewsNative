package com.example.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener{
    private RecyclerView mRecyclerView, recyclerView;
    private Adapter mAdapter;
    private ArrayList<Item> mNewsItems;
    private RequestQueue mRequestQueue;
    public static final String NEWS_URL="news";
    public static final String EXTRA_IMAGE="image";
    public static final String EXTRA_TITLE="title";
    public static final String EXTRA_KONTEN="content";
    public MainActivity(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.one);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView = findViewById(R.id.two);
        recyclerView.setHasFixedSize(true);
        mNewsItems = new ArrayList<Item>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON(){
        String url = "https://newsapi.org/v2/top-headlines?country=id&category=technology&apiKey=0bf4bdd3534d4609a61c04f0380e652a";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("articles");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject resul = jsonArray.getJSONObject(i);
                        String judul = resul.getString("title");
                        String url = resul.getString("url");
                        String image = resul.getString("urlToImage");
                        String deskripsi = resul.getString("description");
                        String konten = resul.getString("content");
                        Item tambah = new Item(image, judul, deskripsi, url, konten);
                        mNewsItems.add(tambah);
                    }
                    mAdapter = new Adapter(MainActivity.this, mNewsItems);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(MainActivity.this);
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
    @Override
    public void  onItemClick(int position){
        Intent news  = new Intent(this, Detail.class);
        Item clickedItem = mNewsItems.get(position);

        news.putExtra(EXTRA_IMAGE,clickedItem.getGambar());
        news.putExtra(EXTRA_TITLE,clickedItem.getTitle());
        news.putExtra(EXTRA_KONTEN,clickedItem.getKonten());
        news.putExtra(NEWS_URL,clickedItem.getUrl());
        startActivity(news);
    }
}
