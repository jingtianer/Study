package com.example.java_mvp.network.response;

import android.util.Log;

import com.example.java_mvp.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 22:41.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class ResponCallBack<T> implements Callback<T> {

    public abstract void onDataResponse(Call<T> call, Response<T> response);

    public abstract void onDataFailure(Call<T> call, Throwable t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            if (response.code() != 200) {   // 请求失败
                GlobalAPIErrorHandler.handler(response.code());
                Log.i(TAG, "onResponse: " + response.code());
            } else {    // 请求成功
                onDataResponse(call, response);
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t != null) {
            onDataFailure(call, t);
            if (t instanceof ApiException) {
                GlobalAPIErrorHandler.handler((ApiException) t);
            } else {
                ToastUtil.showToast("网络连接失败，请稍后再试");
            }
        }
    }
}
