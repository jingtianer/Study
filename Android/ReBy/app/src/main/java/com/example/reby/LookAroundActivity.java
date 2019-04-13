package com.example.reby;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class LookAroundActivity extends AppCompatActivity {

    ActionBar actionBar;
    private List<LookModel> lookModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("随便逛逛");
        setContentView(R.layout.activity_look_around);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.look_around_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LookAdapter adapter = new LookAdapter(lookModelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        lookModelList.clear();
        initLookView();
    }
    private void initLookView(){
        for(int i = 0; i < 10; i++){
            LookModel first = new LookModel(R.drawable.ic_sale_lady,"淑女学生蕾丝连衣裙，修身甜美小清新","108","996");
            lookModelList.add(first);
        }
    }
}
