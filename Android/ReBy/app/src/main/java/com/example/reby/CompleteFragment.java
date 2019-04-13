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
public class CompleteFragment extends Fragment {


    public CompleteFragment() {
        // Required empty public constructor
    }


    List<ShopModel> shopModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete,container,false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ShopAdapter shopAdapter = new ShopAdapter(shopModelList);
        RecyclerView recyclerView = view.findViewById(R.id.or_complete_view);
        recyclerView.setAdapter(shopAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
        shopModelList.clear();
        initCompleteView();
        // Inflate the layout for this fragment
        return view;
    }

    public void initCompleteView(){
       for(int i = 0; i < 5; i++){
           ShopModel first = new ShopModel(R.drawable.ic_shop_success,"坤坤同款吊带装，穿上它，你也是灌篮高手","2050","1000","成功售出");
           shopModelList.add(first);

       }
        ShopModel second = new ShopModel(R.drawable.ic_pinrucloth,"品如同款主妇装，穿上它，感受草原的清新美好","2050","1000","成功售出");
        shopModelList.add(second);
    }
}
