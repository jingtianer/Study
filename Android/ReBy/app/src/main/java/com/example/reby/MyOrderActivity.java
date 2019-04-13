package com.example.reby;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

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



    private RadioGroup mRadioGroup;
    private RadioButton radioUncomplete,radioComplete;
    public Fragment fragment;
    private List<Fragment>fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         /*
        更改标题
         */
        getSupportActionBar().setTitle("我的订单");
        ActionBar actionBar = getSupportActionBar();
        /*
       设置返回箭头
         */
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.activity_my_order);
        fragments = getFragments();
        initOrderView();
        mRadioGroup.setOnCheckedChangeListener(this);
        normalFragment();

    }
    public List<Fragment> getFragments(){
        fragments.add(new UnCompleteFragment());
        fragments.add(new CompleteFragment());
        return fragments;
    }
    public void initOrderView(){
        mRadioGroup = (RadioGroup)findViewById(R.id.radio_group_order);
        radioUncomplete = (RadioButton)findViewById(R.id.radio_un_complete);
        radioComplete = (RadioButton)findViewById(R.id.radio_complete);
    }
    public void normalFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragments.get(0);
        fragmentTransaction.replace(R.id.order_fragment,fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
    @Override
    public void onCheckedChanged(RadioGroup group,int checkedId){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.radio_un_complete:
                fragment = fragments.get(0);
                fragmentTransaction.replace(R.id.order_fragment,fragment);
                break;
            case R.id.radio_complete:
                fragment = fragments.get(1);
                fragmentTransaction.replace(R.id.order_fragment,fragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
