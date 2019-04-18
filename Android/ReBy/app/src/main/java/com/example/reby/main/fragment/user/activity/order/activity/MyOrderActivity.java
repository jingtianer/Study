package com.example.reby.main.fragment.user.activity.order.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.reby.main.fragment.user.activity.order.fragment.complete.fragment.CompleteFragment;
import com.example.reby.R;
import com.example.reby.main.fragment.user.activity.order.fragment.uncomplete.fragment.UnCompleteFragment;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private RadioButton uncompleteRbtn, completeRbtn;
    public Fragment fragment;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();
        setContentView(R.layout.activity_my_order);
        fragments = getFragments();
        initOrderView();
        mRadioGroup.setOnCheckedChangeListener(this);
        normalFragment();

    }

    /**
     * 初始化ActionBar
     */
    public void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        getSupportActionBar().setTitle("我的订单");
    }

    /**
     * 设置ActionBar的返回
     * @param item
     * @return
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

    /**
     * 将两个fragments加到容器中
     * @return
     */
    public List<Fragment> getFragments() {
        fragments.add(new UnCompleteFragment());
        fragments.add(new CompleteFragment());
        return fragments;
    }

    /**
     * 初始化布局
     */
    public void initOrderView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.group_order);
        uncompleteRbtn = (RadioButton) findViewById(R.id.rbtn_un_complete);
        completeRbtn = (RadioButton) findViewById(R.id.rbtn_complete);
    }

    /**
     * 设置初始fragment
     */
    public void normalFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragments.get(0);
        fragmentTransaction.replace(R.id.fragment_order, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 设置点击选择事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId) {
            case R.id.rbtn_un_complete:
                fragment = fragments.get(0);
                fragmentTransaction.replace(R.id.fragment_order, fragment);
                break;
            case R.id.rbtn_complete:
                fragment = fragments.get(1);
                fragmentTransaction.replace(R.id.fragment_order, fragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
