package com.nequer.android.network.converter;

import android.support.annotation.Nullable;

import com.nequer.android.common.Config;
import com.nequer.android.network.ApiService
import com.google.gson.Gson;
import com.neuqer.android.network.exception.ApiException;
import com.neuqer.android.network.response.ApiResponse;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 21:31.
 * email yangzhaoxin@hrsoft.net.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Nullable
    @Override
    public T convert(ResponseBody value) throws IOException {
        //将返回的json数据储存在String类型的response中
        String response = value.string();
        //将外层的数据解析到ApiResponse中
        ApiResponse apiResponse = gson.fromJson(response, ApiResponse.class);

        //遍历正确码
        for (int code : Config.NET_CORRECT_CODE) {
            if (apiResponse.getCode() == code) {
                //直接解析，正确请求不会导致json解析异常
                return gson.fromJson(response, type);
            }
        }

        //通过抛出自定义异常传递错误码及错误信息
        throw (ApiException) gson.fromJson(response, ApiException.class);
    }
}
