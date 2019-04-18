package com.example.reby.account.register.activity;

import android.content.ContentValues;
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
import com.example.reby.account.login.activity.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edit_register_account,edit_register_pwd,edit_register_re_pwd;
    private Button btn_register_cancle,btn_register_commit;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();//设置取消ActionBar
        if(actionBar != null){
            actionBar.hide();
        }
        initActivity();
        dbHelper = new DBHelper(this,"Data.db",null,1);
    }
    protected void initActivity(){
        edit_register_account = (EditText) findViewById(R.id.et_register_account);
        edit_register_account.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    edit_register_account.clearFocus();
                }
                return false;
            }
        });
        edit_register_pwd = (EditText) findViewById(R.id.et_register_password);
        edit_register_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edit_register_pwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    edit_register_pwd.clearFocus();
                }
                return false;
            }
        });
        edit_register_re_pwd = (EditText) findViewById(R.id.et_re_register_password);
        edit_register_re_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edit_register_re_pwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    edit_register_re_pwd.clearFocus();
                }
                return false;
            }
        });
        btn_register_cancle = (Button) findViewById(R.id.btn_register_cancel);
        btn_register_cancle.setOnClickListener(this);
        btn_register_commit = (Button) findViewById(R.id.btn_register_commit);
        btn_register_commit.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_register_cancel:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class );
                startActivity(intent);
                break;
            case R.id.btn_register_commit:
                if(checkIsRegistered(edit_register_account.getText().toString().trim())){
                    Toast.makeText(this,"注册失败 username'"+edit_register_account.getText().toString()+"' already taken",Toast.LENGTH_SHORT).show();
                }else if(edit_register_account.getText().toString().trim().equals("")|edit_register_pwd.getText().toString().trim().equals("")|edit_register_re_pwd.getText().toString().trim().equals("")){
                    Toast.makeText(this,"请完善您的信息",Toast.LENGTH_SHORT);
                } else{
                    if(edit_register_pwd.getText().toString().equals(edit_register_re_pwd.getText().toString())){
                        registerUser(edit_register_account.getText().toString(),edit_register_pwd.getText().toString());
                        Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();
                        Intent intentMainActivity = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intentMainActivity);
                    }else{
                        Toast.makeText(this,"两次密码输入不一致，请确认",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;

        }

    }
    public boolean checkIsRegistered(String account){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "Select * from usertable where username = ?";
        Cursor cursor = db.rawQuery(Query, new String[]{account});
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
    public void registerUser(String account,String password){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", account);
        values.put("password", password);
        db.insert("usertable", null, values);
        db.close();
    }
}
