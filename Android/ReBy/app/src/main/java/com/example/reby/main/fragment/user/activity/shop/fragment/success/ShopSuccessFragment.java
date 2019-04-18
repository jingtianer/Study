package com.example.reby.main.fragment.user.activity.shop.fragment.success;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reby.R;
import com.example.reby.main.fragment.user.activity.shop.model.ShopModel;
import com.example.reby.main.fragment.user.activity.shop.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopSuccessFragment extends Fragment {


    public ShopSuccessFragment() {
        // Required empty public constructor
    }

    List<ShopModel>shopModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_success,container,false);
        ShopAdapter shopAdapter = new ShopAdapter(shopModelList);
        shopModelList.clear();
        initShopView();
        initRecyclerView(view, shopAdapter);
        // Inflate the layout for this fragment
        return view;
    }

    public void initRecyclerView(View view, ShopAdapter shopAdapter){
        RecyclerView recyclerView = view.findViewById(R.id.shop_success_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setFocusable(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(shopAdapter);
    }


    public void initShopView(){
        ShopModel first = new ShopModel(R.drawable.ic_shop_success,"坤坤同款吊带装，穿上它，你也是灌篮高手","2050",
                "1000","成功售出");
        shopModelList.add(first);
    }
}
