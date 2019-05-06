package com.example.java_mvp.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.java_mvp.App;

/**
 * @author YangZhaoxin.
 * @since 2019/5/3 17:42.
 * email yangzhaoxin@hrsoft.net.
 */

public class ToastUtil {

    private static Toast sToast;

    private ToastUtil() {

    }

    /**
     * toast显示msg
     * @param msg
     */
    public static void showToast(final String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
        }

        // TODO： 解决线程问题
        sToast.show();
    }


    public static void showToast(@StringRes final int resId) {
        showToast(resId);
    }

    /**
     * 取消toast
     */
    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }
}
