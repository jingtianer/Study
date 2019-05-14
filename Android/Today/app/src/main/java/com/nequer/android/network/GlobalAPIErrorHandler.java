package com.nequer.android.network;

import com.nequer.android.Application;
import com.neuqer.android.network.exception.ApiException;
import com.neuqer.android.util.ToastUtil;

public class GlobalAPIErrorHandler {

    public static void handler ( int code ) {
        switch ( code ) {
            case 200 : ToastUtil.show(Application.getInstance(),"200");
            case 400 : ToastUtil.show(Application.getInstance(),"400");
            case 500 : ToastUtil.show(Application.getInstance(),"服务器崩了");
            default:
                ToastUtil.show(Application.getInstance(),"请求不被允许");
                break;
        }
    }

    public  static void handler (ApiException e ) {
        switch ( e.getCode() ) {
            default :
                ToastUtil.show(Application.getInstance(), e.getMsg());
                break;
        }
    }
}
