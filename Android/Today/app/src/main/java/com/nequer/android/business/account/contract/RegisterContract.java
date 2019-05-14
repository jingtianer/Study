package com.nequer.android.business.account.contract;

import com.nequer.android.base.mvp.impl.IBaseContract;
import com.nequer.android.business.account.model.request.RegisterReqModel;

public  interface RegisterContract {

    interface View extends IBaseContract.IBaseView{
        void registerSuccess();
        void registerFailed();
    }

    interface Presenter extends IBaseContract.IBasePresenter{
        void register(RegisterReqModel registerReqModel);
    }
}
