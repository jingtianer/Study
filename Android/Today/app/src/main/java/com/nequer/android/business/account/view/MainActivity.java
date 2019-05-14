/*
 * Copyright 2019. techflowing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nequer.android.business.account.view;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.nequer.android.base.activity.BaseActivity;
import com.nequer.android.base.activity.BaseNoBarActivity;
import com.neuqer.android.R;

/**
 * 示例类
 *
 * @author techflowing
 * @since 2019/4/21 11:42 PM
 */
public class MainActivity extends BaseActivity {


    private EditText edtLoginAccount,edtLoginPassword;
    private Button btnLogin;
    private LinearLayout linerRegsiter;
    @Override
    protected int setToolBar(){
        return View.VISIBLE;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        edtLoginAccount = (EditText) findViewById(R.id.edt_account);
        edtLoginPassword = (EditText) findViewById(R.id.edt_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    @Override
    protected String getActivityTitle() {
        return "登陆";
    }
}
