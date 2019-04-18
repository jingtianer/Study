package com.example.reby.main.fragment.home.activity.lookactivity.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.reby.main.fragment.home.activity.lookactivity.adapter.LookAdapter;
import com.example.reby.main.fragment.home.activity.lookactivity.model.LookModel;
import com.example.reby.R;

import java.util.ArrayList;
import java.util.List;

public class LookAroundActivity extends AppCompatActivity {

    private List<LookModel> lookModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_around);
        changeActionBar();
        initRecyclerView();
        lookModelList.clear();
        initLookView();
    }

    /**
     * 初始化recycler view
     */
    private void initRecyclerView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.look_around_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LookAdapter adapter = new LookAdapter(lookModelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    /**
     * 改变ActionBar
     */
    private void changeActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("随便逛逛");
    }

    /**
     * 退回上一层
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
     * 在recyclerview里面添加数据
     */
    private void initLookView() {
        for (int i = 0; i < 10; i++) {
            LookModel first = new LookModel(R.drawable.ic_sale_lady, "淑女学生蕾丝连衣裙，" +
                    "修身甜美小清新", "108", "996");
            lookModelList.add(first);
        }
    }
}
