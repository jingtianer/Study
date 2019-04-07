package com.example.reby;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
public class SaleFragment extends Fragment {


    public SaleFragment() {
        // Required empty public constructor
    }

    private List<SaleModel> saleModelList = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.sale_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setFocusable(false);//设置页面在从顶端开始
        SaleAdapter saleAdapter = new SaleAdapter(saleModelList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(saleAdapter);
        initSaleView();
        return view;
    }

    public void initSaleView(){
        for(int i = 0;i < 5;i++){
            SaleModel first = new SaleModel(R.drawable.ic_sale_lady, "淑女学生蕾丝连衣裙，修身甜美小清新",
                    "180.0", "2019.4.17","10");
            saleModelList.add(first);
            SaleModel second = new SaleModel(R.drawable.ic_sale_lady, "淑女学生蕾丝连衣裙，修身甜美小清新",
                    "190.0", "2019.4.17","11");
            saleModelList.add(second);
            SaleModel third = new SaleModel(R.drawable.ic_sale_lady, "淑女学生蕾丝连衣裙，修身甜美小清新",
                    "280.0", "2019.4.17","30");
            saleModelList.add(third);
            SaleModel fourth = new SaleModel(R.drawable.ic_sale_lady, "淑女学生蕾丝连衣裙，修身甜美小清新",
                    "140.0", "2019.4.17","20");
            saleModelList.add(fourth);
            SaleModel fifth = new SaleModel(R.drawable.ic_sale_lady, "淑女学生蕾丝连衣裙，修身甜美小清新",
                    "90.0", "2019.4.17","13");
            saleModelList.add(fifth);
        }
    }

}
