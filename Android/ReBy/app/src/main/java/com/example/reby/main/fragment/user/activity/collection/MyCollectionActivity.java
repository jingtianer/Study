package com.example.reby.main.fragment.user.activity.collection;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.reby.R;
import com.example.reby.main.fragment.sale.adapter.SaleAdapter;
import com.example.reby.main.fragment.sale.model.SaleModel;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends AppCompatActivity {



    List<SaleModel>saleModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        SaleAdapter saleAdapter = new SaleAdapter(saleModelList);
        initActionBar();
        initRecyclerView(saleAdapter);
        initCllectionView();
    }

    /**
     * 设置ActionBar
     */
    public void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("我的收藏");
    }

    /**
     * 设置返回事件
     * @param item
     * @return
     */

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

    /**
     * 初始化RecyclerView
     * @param saleAdapter
     */

    public void initRecyclerView(SaleAdapter saleAdapter){
        RecyclerView recyclerView = findViewById(R.id.collection_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(saleAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 初始化RecyclerView里的数据
     */

    public void initCllectionView(){
        for(int i = 0; i < 10; i++ ){
            SaleModel first = new SaleModel(R.drawable.ic_sale_lady, "淑女学生蕾丝连衣裙，修身甜美小清新",
                    "180.0", "2019.4.17","10");
            saleModelList.add(first);
        }
    }
}
