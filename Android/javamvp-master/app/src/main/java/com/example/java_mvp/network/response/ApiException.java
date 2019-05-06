package com.example.java_mvp.network.response;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 22:22.
 * email yangzhaoxin@hrsoft.net.
 */

public class ApiException extends IOException{

    // 错误码
    @SerializedName("code")
    private int code;

    // 错误信息
    @SerializedName("message")
    private String message;


    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
