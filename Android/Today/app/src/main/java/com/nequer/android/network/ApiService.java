package com.nequer.android.network;

import java.util.Observable;

import com.nequer.android.business.account.model.request.RegisterReqModel;
import com.nequer.android.business.account.model.response.RegisterRspModel;
import com.nequer.android.network.response.*;
import com.neuqer.android.network.response.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    /**
     * 注册
     */
    @POST("user/register")
    Call<ApiResponse<RegisterRspModel>> register (@Body RegisterReqModel registerReqModel);
}
