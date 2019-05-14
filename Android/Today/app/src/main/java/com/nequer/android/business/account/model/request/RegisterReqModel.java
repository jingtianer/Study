package com.nequer.android.business.account.model.request;

import com.nequer.android.base.mvp.model.BaseBean;

public class RegisterReqModel extends BaseBean {

    /**
     * name : lal
     * password : 8282481aa
     * signature :
     * confirmPassword : 1
     * sex : -1
     */

    private String name;
    private String password;
    private String signature;
    private String confirmPassword;
    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public boolean checkValue(){
        return true;
    }
}
