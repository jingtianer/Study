package com.example.java_mvp.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 10:35.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class BaseNoBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData(savedInstanceState);
        initView();
    }
}