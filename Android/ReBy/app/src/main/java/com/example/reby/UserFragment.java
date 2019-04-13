package com.example.reby;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {


    public UserFragment() {
        // Required empty public constructor
    }

    private Button btn_user_info,btn_shop,btn_order,btn_collect,btn_setting;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_user, container, false);



        btn_user_info = (Button)view.findViewById(R.id.btn_user_info);
        btn_user_info.setOnClickListener(this);
        btn_shop = (Button)view.findViewById(R.id.btn_shop);
        btn_shop.setOnClickListener(this);
        btn_order = (Button)view.findViewById(R.id.btn_order);
        btn_order.setOnClickListener(this);
        btn_collect = (Button)view.findViewById(R.id.btn_collection);
        btn_collect.setOnClickListener(this);
        btn_setting = (Button)view.findViewById(R.id.btn_install);
        btn_setting.setOnClickListener(this);
        /*btn_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UserActivity.class);
                startActivity(intent);
                switch (view.getId()){
                    case R.id.btn_user_info:
                }

            }
        });*/
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_user_info:
                Intent intent = new Intent(getActivity(),UserActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_shop:
                Intent intent1 = new Intent(getActivity(),MyShopActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_order:
                Intent intent2 = new Intent(getActivity(),MyOrderActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_collection:
                Intent intent3 = new Intent(getActivity(),MyCollectionActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_install:
                Intent intent4 = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent4);
                break;

        }
    }
}
