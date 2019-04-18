package com.example.reby.main.fragment.user.activity.setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reby.R;
import com.example.reby.account.login.activity.LoginActivity;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        backBtn = (Button) findViewById(R.id.back);
        backBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
