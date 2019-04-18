package com.example.reby.main.fragment.user.activity.shop.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.reby.R;
import com.example.reby.main.fragment.user.activity.shop.fragment.onsale.ShopOnSaleFragment;
import com.example.reby.main.fragment.user.activity.shop.fragment.success.ShopSuccessFragment;

import java.util.ArrayList;
import java.util.List;

public class MyShopActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private RadioButton radio_onsell,radio_sucess;
    private Fragment fragment;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    /*
    设置页面回退
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        更改标题
         */
        getSupportActionBar().setTitle("我的小店");
        ActionBar actionBar = getSupportActionBar();
        /*
       设置返回箭头
         */
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_my_shop);

        fragments = getFragments();//将碎片添加到fragments中

         initView();

         mRadioGroup.setOnCheckedChangeListener(this);

         normalFragments();

    }
    /*
    将碎片添加到fragments中
     */
    public List<Fragment>getFragments(){
        fragments.add(new ShopOnSaleFragment());
        fragments.add(new ShopSuccessFragment());
        return fragments;
    }

    /*
    初始化界面
     */
    public void initView(){
        mRadioGroup = (RadioGroup)findViewById(R.id.radio_shop);
        radio_onsell = (RadioButton)findViewById(R.id.radio_on_sell);
        radio_sucess = (RadioButton)findViewById(R.id.radio_success_sell);
    }
    /*
    设置初始界面
     */
    public void normalFragments(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragments.get(0);
        fragmentTransaction.replace(R.id.shop_fragment,fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
    /*
    设置点击事件
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch(checkedId){
            case R.id.radio_on_sell:
                fragment = fragments.get(0);
                fragmentTransaction.replace(R.id.shop_fragment,fragment);
                break;
            case R.id.radio_success_sell:
                fragment = fragments.get(1);
                fragmentTransaction.replace(R.id.shop_fragment,fragment);
                break;
        }
        fragmentTransaction.commit();
    }
}

