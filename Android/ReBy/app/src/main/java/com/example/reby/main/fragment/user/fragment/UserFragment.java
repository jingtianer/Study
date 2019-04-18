package com.example.reby.main.fragment.user.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.reby.R;
import com.example.reby.main.fragment.user.activity.detail.UserActivity;
import com.example.reby.main.fragment.user.activity.collection.MyCollectionActivity;
import com.example.reby.main.fragment.user.activity.order.activity.MyOrderActivity;
import com.example.reby.main.fragment.user.activity.setting.SettingActivity;
import com.example.reby.main.fragment.user.activity.shop.activity.MyShopActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {


    public UserFragment() {
        // Required empty public constructor
    }


    private LinearLayout userInfoLinearLayout, shopLinearLayout, orderLinearLayout, collectionLinearLayout, settingLinearLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化
     *
     * @param view
     */
    public void initView(View view) {
        userInfoLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_info);
        userInfoLinearLayout.setOnClickListener(this);
        shopLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_shop);
        shopLinearLayout.setOnClickListener(this);
        orderLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_order);
        orderLinearLayout.setOnClickListener(this);
        collectionLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_collection);
        collectionLinearLayout.setOnClickListener(this);
        settingLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_setting);
        settingLinearLayout.setOnClickListener(this);
    }

    /**
     * 设置点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout_info:
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                break;
            case R.id.linearLayout_shop:
                Intent intent1 = new Intent(getActivity(), MyShopActivity.class);
                startActivity(intent1);
                break;
            case R.id.linearLayout_order:
                Intent intent2 = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent2);
                break;
            case R.id.linearLayout_collection:
                Intent intent3 = new Intent(getActivity(), MyCollectionActivity.class);
                startActivity(intent3);
                break;
            case R.id.linearLayout_setting:
                Intent intent4 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent4);
                break;

        }
    }
}
