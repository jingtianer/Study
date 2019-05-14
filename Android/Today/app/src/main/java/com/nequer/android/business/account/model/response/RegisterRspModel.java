package com.nequer.android.business.account.model.response;

import com.nequer.android.base.mvp.model.BaseBean;

public class RegisterRspModel extends BaseBean {

    /**
     * code : 0
     * data : 88
     */

    private int code;
    private int data;

    @Override
    public boolean checkValue(){
        return true;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
