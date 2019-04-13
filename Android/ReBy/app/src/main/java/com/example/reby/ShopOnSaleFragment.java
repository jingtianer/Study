package com.example.reby;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopOnSaleFragment extends Fragment {


    public ShopOnSaleFragment() {
        // Required empty public constructor
    }

    private List<ShopModel> shopModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop_on_sell,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.shop_on_sell_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setFocusable(false);
        ShopAdapter shopAdapter = new ShopAdapter(shopModelList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(shopAdapter);
        shopModelList.clear();
        initShopView();
        // Inflate the layout for this fragment
        return view;
    }
    public void initShopView(){
        for(int i=0;i<10;i++){
            ShopModel first = new ShopModel(R.drawable.ic_on_sell,"这是一件很好看的衣服,赶紧买吧，不买就没了，快点","108.9","1000","正在进行中");
            shopModelList.add(first);
        }
    }

}
