package com.example.java_mvp.business.account.presenter;

import com.example.java_mvp.base.mvp.model.IDataCallBack;
import com.example.java_mvp.base.mvp.presenter.BasePresenter;
import com.example.java_mvp.business.account.contract.SplashContract;
import com.example.java_mvp.business.account.model.AccountHelper;
import com.example.java_mvp.common.CacheKey;
import com.example.java_mvp.utils.CacheUtil;

/**
 * @author YangZhaoxin.
 * @since 2019/5/3 19:30.
 * email yangzhaoxin@hrsoft.net.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    @Override
    public void checkToken() {

        IDataCallBack<String> callBack = new IDataCallBack<String>() {
            @Override
            public void onSuccess(String... data) {
                CacheUtil.put(CacheKey.TOKEN, data[0]);
                mView.checkTokenSuccess();
            }

            @Override
            public void onFailed(String error) {
                mView.checkTokenFailed(error);
            }
        };

        AccountHelper.getInstance().checkToken(this, callBack);
        mView.checkTokenSuccess();
    }
}
