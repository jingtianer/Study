package com.nequer.android.business.account.contract;

import com.nequer.android.base.mvp.impl.IBaseContract;

public interface SplashContract {
    interface Presenter extends IBaseContract.IBasePresenter{
        void checkToken();
    }
    interface View extends IBaseContract.IBaseView{
        void checkTokenOnSuccess();
        void checkTokenOnFailed(String error);
    }
}
