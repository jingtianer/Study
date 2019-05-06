package com.example.java_mvp.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 10:28.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 得到布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 启动新活动
     * @param newActivity
     */
    protected void startActivity(Class<?> newActivity) {
        startActivity(newActivity, null);
    }

    /**
     * 启动新活动（传输简单数据）
     * @param newActivity
     * @param bundle
     */
    protected void startActivity(Class<?> newActivity, Bundle bundle) {
        Intent intent = new Intent(BaseActivity.this, newActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
