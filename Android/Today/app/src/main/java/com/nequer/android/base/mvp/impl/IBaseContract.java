package com.nequer.android.base.mvp.impl;

import com.nequer.android.base.mvp.INotifyListener;

public interface IBaseContract {
    interface IBaseView{

    }

    interface IBasePresenter{
        /**
         * 绑定V层的生命周期
         */
        void bindView(IBaseView view);

        /**
         * 解绑V层
         */
        void unBindView();
        /**
         * 判断是否与V层绑定
         */
        boolean isBindView();

        void registerModel(IBaseModel model);
    }
    interface IBaseModel {

        void addNotifyListener(IBaseContract.IBasePresenter presenter, INotifyListener listener);

        void removeNotifyListener(IBaseContract.IBasePresenter presenter);

        void removeAllNotifyListener();
    }
}
