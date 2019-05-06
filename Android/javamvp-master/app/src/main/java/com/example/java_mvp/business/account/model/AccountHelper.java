package com.example.java_mvp.business.account.model;

import com.example.java_mvp.base.mvp.impl.IBaseContract;
import com.example.java_mvp.base.mvp.model.BaseModel;
import com.example.java_mvp.base.mvp.model.IDataCallBack;
import com.example.java_mvp.network.NetWorkFactory;
import com.example.java_mvp.network.response.ApiResponse;
import com.example.java_mvp.network.response.ResponCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author YangZhaoxin.
 * @since 2019/5/3 19:58.
 * email yangzhaoxin@hrsoft.net.
 */

public class AccountHelper extends BaseModel {

    private AccountHelper() {

    }

    private static class AccountHelperHolder {
        private static final AccountHelper INSTANCE = new AccountHelper();
    }

    /**
     * 获得实例，同时保证单例，线程安全
     * @return
     */
    public static AccountHelper getInstance() {
        return AccountHelperHolder.INSTANCE;
    }

    public void checkToken(final IBaseContract.IBasePresenter presenter, final IDataCallBack<String> callBack) {

        NetWorkFactory.getService().checkToken().enqueue(new ResponCallBack<ApiResponse<String>>() {
            @Override
            public void onDataResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {

            }

            @Override
            public void onDataFailure(Call<ApiResponse<String>> call, Throwable t) {

            }
        });
    }
}
