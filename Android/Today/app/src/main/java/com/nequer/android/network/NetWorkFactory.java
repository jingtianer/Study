package com.nequer.android.network;

import com.nequer.android.common.CacheKey;
import com.nequer.android.common.Config;
import com.nequer.android.network.converter.ResponseConverterFactory;
import com.nequer.android.util.SharedPreferencesUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import static com.nequer.android.common.Config.BASE_URL;

public  final class NetWorkFactory {

    private static OkHttpClient sOkHttpClient;
    private static Retrofit sRetrofit;
    private static ApiService sApiService;

    /**
     * 生成service接口
     *
     * @retrun RetrofitService
     */
    public static ApiService getApiService(){
        if (sApiService == null ) {
            sApiService = getsRetrofit().create(ApiService.class);
        }
        return sApiService;
    }

    /**
     * 构造Retrofit，设置相关参数
     *
     * @return Retrofit
     */
    private static Retrofit getsRetrofit() {
        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .client(getsOkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ResponseConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    public static OkHttpClient getsOkHttpClient() {
        if( sOkHttpClient == null ) {
            sOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(Config.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addNetworkInterceptor(getNetworkInterceptor())
                    .build();

        }
        return sOkHttpClient;
    }

    public static Interceptor getNetworkInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = SharedPreferencesUtil.getSP().getString(CacheKey.TOKEN, "");
                Request request = chain.request().newBuilder()
                        .header(CacheKey.TOKEN,token)
                        .build();
                return chain.proceed(request);
            }
        };

    }


}
