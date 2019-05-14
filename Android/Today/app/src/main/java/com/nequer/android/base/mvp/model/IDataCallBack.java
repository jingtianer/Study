package com.nequer.android.base.mvp.model;

public interface IDataCallBack<T> {
    /**
     * 数据请求成功回调
     */
    void onSuccess(T...data);

    /**
     * 数据请求回调
     */
    void onFailed(String error);
}
