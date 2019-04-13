package com.example.reby;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btn_back = (Button) findViewById(R.id.back);
        btn_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
