package com.nequer.android.business.account.model;
import com.nequer.android.base.mvp.IDataCallBack;
import com.nequer.android.base.mvp.impl.IBaseContract;
import com.nequer.android.base.mvp.model.BaseModel;
import com.nequer.android.business.account.contract.RegisterContract;
import com.nequer.android.business.account.model.request.RegisterReqModel;
import com.nequer.android.business.account.model.response.RegisterRspModel;
import com.nequer.android.business.account.presenter.RegisterActivityPresenter;
import com.nequer.android.network.NetWorkFactory;
import com.nequer.android.network.response.ResponseCallBack;
import com.neuqer.android.network.response.ApiResponse;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountHelper extends BaseModel {
    private AccountHelper(){

    }

    private static class AccountHelperHolder{
        private static final AccountHelper INSTANCE = new AccountHelper();
    }

    public AccountHelper getAccountHelper(){
        return AccountHelperHolder.INSTANCE;
    }

    /**
     * 注册
     *
     * @param presenter
     * @param reqModel
     * @param callback
     */
    /*public void register(IBaseContract.IBasePresenter presenter, RegisterReqModel reqModel, final IDataCallBack.Callback<String> callback){
        addNotifyListener(presenter, callback);

        NetWorkFactory.getApiService().register(reqModel).enqueue(new ResponseCallBack<ApiResponse<String>>() {
            @Override
            public void onDataResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {

            }

            @Override
            public void onDataFailure(Call<ApiResponse<String>> call, Throwable t) {

            }
        });
    }*/

    public void register (RegisterActivityPresenter presenter, RegisterReqModel reqModel){
        NetWorkFactory
                .getApiService()
                .register(reqModel)
                .enqueue();
    }


}
