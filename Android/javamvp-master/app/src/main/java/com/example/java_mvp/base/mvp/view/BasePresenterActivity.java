package com.example.java_mvp.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.java_mvp.base.activity.BaseNoBarActivity;
import com.example.java_mvp.base.mvp.impl.IBaseContract;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 10:49.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class BasePresenterActivity<P extends IBaseContract.IBasePresenter> extends BaseNoBarActivity implements IBaseContract.IBaseView{

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P getPresenter();

    /**
     * 双向绑定
     */
    protected void initPresenter() {
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.bindView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unBindView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
