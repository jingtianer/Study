package com.example.reby.main.fragment.home.activity.lookactivity.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.reby.R;

public class GoodsIntroActivity extends AppCompatActivity implements View.OnClickListener {


    private Button contectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_intro);
        contectBtn = (Button) findViewById(R.id.contect);
        contectBtn.setOnClickListener(this);
        initActionBar();
    }

    @Override
    public void onClick(View view){
        Toast.makeText(GoodsIntroActivity.this,"商家暂时不在线，估计是跑路了，我们正在联系她",Toast.LENGTH_SHORT).show();

    }

    /**
     * 设置ActionBar
     */
    private void initActionBar(){
        getSupportActionBar().setTitle("商品信息");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
}
