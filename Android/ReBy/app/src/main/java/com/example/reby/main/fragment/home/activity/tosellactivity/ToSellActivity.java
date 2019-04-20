package com.example.reby.main.fragment.home.activity.tosellactivity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.reby.R;
import com.example.reby.SpreadActivity;


public class ToSellActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titleEdit;
    private Button spreadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_to_sell);
        titleEdit = (EditText)findViewById(R.id.edt_title);
        spreadButton = (Button)findViewById(R.id.btn_spread);
        spreadButton.setOnClickListener(this);
        initActionBar();
    }



    @Override
    public void onClick(View view){
        Intent intent = new Intent(ToSellActivity.this, SpreadActivity.class);
        String title = titleEdit.getText().toString().trim();
        intent.putExtra("data",title);
        startActivity(intent);
    }

    /**
     * 初始化ActionBar
     */
    private void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("有货出手");
    }

    /**
     * 设置返回
     */
   public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
