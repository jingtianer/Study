package com.nequer.android.network.response;

import android.util.Log;

import com.nequer.android.Application;
import com.nequer.android.network.GlobalAPIErrorHandler;
import com.neuqer.android.network.exception.ApiException;
import com.neuqer.android.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public abstract class ResponseCallBack<T> implements Callback<ApiResponse<T>> {

   public abstract void onSuccess(T data);
   public abstract void onFailed();

    @Override
    public void onResponse(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response) {
        if (response.code() <= 400) {
            if (response.body().getCode() == 0) {
                onSuccess(response.body().getData());
            } else {
                String str = response.body().getMessage();
                ToastUtil.show(Application.getInstance(),str);
                GlobalAPIErrorHandler.handler(response.body().getCode());
                onFailed();
            }
        } else {
            GlobalAPIErrorHandler.handler(response.code());
            onFailed();
        }
    }

    @Override
    public void onFailure(Call<ApiResponse<T>> call, Throwable t) {

        onFailed();

    }

    /*
   @Override
    public void onResponse(Call<ResponseCallBack<T>> call,Response<ResponseModel<T>> response)
*/

}
