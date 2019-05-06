package com.example.java_mvp.base.mvp.presenter;

import com.example.java_mvp.base.mvp.impl.IBaseContract;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 10:12.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class BasePresenter<V extends IBaseContract.IBaseView> implements IBaseContract.IBasePresenter {

    protected V mView;

    public BasePresenter() {
        // 完成初始化数据库等操作
    }

    /**
     * P层绑定V层的生命周期
     * @param view
     */
    @Override
    public void bindView(IBaseContract.IBaseView view) {
        this.mView = (V) view;
    }

    /**
     * P层解绑V层的生命周期
     */
    @Override
    public void unBindView() {
        if (mView != null) {
            mView = null;
        }
    }

    /**
     * P层是否与V层绑定
     * @return
     */
    @Override
    public boolean isBindView() {
        return mView != null;
    }
}
