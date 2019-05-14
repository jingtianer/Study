package com.nequer.android.business.account.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nequer.android.base.activity.BaseActivity;
import com.nequer.android.business.account.model.request.RegisterReqModel;
import com.nequer.android.business.account.presenter.RegisterActivityPresenter;
import com.neuqer.android.R;

import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private RegisterActivityPresenter mPresenter;

    private EditText edtRgitAccount, edtRgitPwd, edtErgitPwd;
    private Button btnRgit;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }


    @Override
    protected void initVariable(){

    }

    @Override
    protected void initView(){
        edtRgitAccount = (EditText) findViewById(R.id.edt_rgit_account);
        edtRgitPwd = (EditText) findViewById(R.id.edt_rgit_pwd);
        edtErgitPwd = (EditText) findViewById(R.id.edt_ergit_pwd);
        btnRgit = (Button) findViewById(R.id.btn_register);
        btnRgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterReqModel reqModel = new RegisterReqModel();
                reqModel.setName(edtRgitAccount.toString().trim());
                reqModel.setPassword(edtRgitPwd.toString().trim());
                reqModel.setConfirmPassword(edtErgitPwd.toString().trim());
            }
        });
    }


}
