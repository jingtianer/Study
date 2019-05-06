package com.example.java_mvp.base.mvp.impl;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 9:57.
 * email yangzhaoxin@hrsoft.net.
 */

public interface IBaseContract {

    interface IBaseView {

    }

    interface IBasePresenter {

        /**
         * P层绑定V层的生命周期
         * @param view
         */
        void bindView(IBaseView view);

        /**
         * P层解绑V层的生命周期
         */
        void unBindView();

        /**
         * P层是否与V层绑定
         * @return
         */
        boolean isBindView();
    }

    interface IBaseModel {

    }
}
