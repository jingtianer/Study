package com.example.java_mvp.business.account.view.activity;

import android.os.Bundle;
import android.os.Handler;

import com.example.java_mvp.R;
import com.example.java_mvp.base.mvp.view.BasePresenterActivity;
import com.example.java_mvp.business.MainActivity;
import com.example.java_mvp.business.account.contract.SplashContract;
import com.example.java_mvp.business.account.presenter.SplashPresenter;

public class SplashActivity extends BasePresenterActivity<SplashContract.Presenter> implements SplashContract.View{

    // 初始页停留时间
    public static final int SPLASH_DELAYED_TIME = 1000;

    @Override
    protected SplashContract.Presenter getPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.checkToken();
            }
        }, SPLASH_DELAYED_TIME);
    }

    @Override
    public void checkTokenSuccess() {
        startActivity(MainActivity.class);
    }

    @Override
    public void checkTokenFailed(String error) {

    }
}
