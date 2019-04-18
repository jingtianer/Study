package com.example.reby.account.login.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reby.util.DBHelper;
import com.example.reby.main.activity.MainActivity;
import com.example.reby.R;
import com.example.reby.account.register.activity.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_account, edit_password;
    private Button btn_login,btn_register;
    private DBHelper dbHelper;
    private DBHelper DB;
    int NUMBER = 0;
    final boolean isOffLine = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏的全透明
       /* if (Build.VERSION.SDK_INT >= 21) {
           View decorView = getWindow().getDecorView();
           decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
           getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/
       //设置状态栏的半透明
        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_login);



        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        DB = new DBHelper(this,DBHelper.DATABASE_NAME,null,DBHelper.DATABASE_VERSION);
        SQLiteDatabase dB = DB.getReadableDatabase();
        Cursor cur = dB.rawQuery("select * from usertable", null);
        NUMBER = cur.getCount();
        if(NUMBER>0 && !isOffLine){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
        activityInit();

//        finish();
    }
    public void activityInit(){
        edit_account = (EditText) findViewById(R.id.et_login_account);
        edit_account.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    edit_account.clearFocus();
                }
                return false;
            }
        });
        edit_password = (EditText)findViewById(R.id.et_login_password);
        //密码隐藏
        edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edit_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    edit_password.clearFocus();
                }
                return false;
            }
        });
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_sign_up);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_login:
                if(edit_account.getText().toString().trim().equals("")|edit_password.getText().toString().trim().equals("")){
                    Toast.makeText(this,"请完善您的信息",Toast.LENGTH_SHORT).show();
                }else{
                    userInfoIsRight();
                }
                break;
            case R.id.btn_sign_up:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }
    protected void userInfoIsRight(){
        if(isRight(edit_account.getText().toString(),edit_password.getText().toString())){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isRight(String edit_account, String edit_password){
        dbHelper=new DBHelper(this,DBHelper.DATABASE_NAME,null,DBHelper.DATABASE_VERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Select * from usertable where username = ? and password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{edit_account, edit_password});
        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        }
        return false;
    }
}
