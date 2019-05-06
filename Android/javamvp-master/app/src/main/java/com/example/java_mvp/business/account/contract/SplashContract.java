package com.example.java_mvp.business.account.contract;

import com.example.java_mvp.base.mvp.impl.IBaseContract;

/**
 * @author YangZhaoxin.
 * @since 2019/5/3 19:27.
 * email yangzhaoxin@hrsoft.net.
 */

public interface SplashContract {

    interface Presenter extends IBaseContract.IBasePresenter {

        /**
         * 检查token是否有效
         */
        void checkToken();
    }

    interface View extends IBaseContract.IBaseView {

        void checkTokenSuccess();
        void checkTokenFailed(String error);
    }
}
