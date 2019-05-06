package com.example.java_mvp.network.response;

import com.example.java_mvp.utils.ToastUtil;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 22:46.
 * email yangzhaoxin@hrsoft.net.
 */

public class GlobalAPIErrorHandler {

    public static void handler(int code) {
        switch (code) {
            default:
                ToastUtil.showToast("请求不被允许，请确定是否有权进行该操作");
                break;
        }
    }

    public static void handler(ApiException e) {
        switch (e.getCode()) {
            default:
                ToastUtil.showToast(e.getMessage());
                break;
        }
    }
}
