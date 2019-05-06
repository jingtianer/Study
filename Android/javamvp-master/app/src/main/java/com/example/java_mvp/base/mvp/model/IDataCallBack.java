package com.example.java_mvp.base.mvp.model;

/**
 * @author YangZhaoxin.
 * @since 2019/5/3 19:53.
 * email yangzhaoxin@hrsoft.net.
 */

public interface IDataCallBack<T> {

    /**
     * 数据请求成功回调
     * @param data
     */
    void onSuccess(T... data);

    /**
     * 数据请求失败回调
     * @param error
     */
    void onFailed(String error);
}
