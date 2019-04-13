package com.example.reby;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends AppCompatActivity {

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    List<SaleModel>saleModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         /*
        更改标题
         */
        getSupportActionBar().setTitle("我的收藏");
        ActionBar actionBar = getSupportActionBar();
        /*
       设置返回箭头
         */
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        setContentView(R.layout.activity_my_collection);

        RecyclerView recyclerView = findViewById(R.id.collection_view);
        SaleAdapter saleAdapter = new SaleAdapter(saleModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(saleAdapter);
        recyclerView.setLayoutManager(layoutManager);
        initCllectionView();
    }
    public void initCllectionView(){
        for(int i = 0; i < 10; i++ ){
            SaleModel first = new SaleModel(R.drawable.ic_sale_lady, "淑女学生蕾丝连衣裙，修身甜美小清新",
                    "180.0", "2019.4.17","10");
            saleModelList.add(first);
        }
    }
}
