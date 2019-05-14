package com.nequer.android.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nequer.android.base.activity.BaseActivity;
import com.nequer.android.base.mvp.impl.IBaseContract;

public abstract class BasePresenterActivity<P extends IBaseContract.IBasePresenter> extends BaseActivity implements IBaseContract.IBaseView {
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        initPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P getPresenter();

    /**
     * 双向绑定
     */
    protected void initPresenter(){
        mPresenter = getPresenter();
        if(mPresenter != null){
            mPresenter.bindView(this);
        }
    }

    @Override
    protected void onDestroy(){
        if(mPresenter != null){
            mPresenter.unBindView();
            mPresenter = null;
        }
        super.onDestroy();
    }

}
