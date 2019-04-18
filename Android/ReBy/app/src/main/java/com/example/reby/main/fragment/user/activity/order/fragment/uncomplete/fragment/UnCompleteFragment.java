package com.example.reby.main.fragment.user.activity.order.fragment.uncomplete.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reby.R;
import com.example.reby.main.fragment.user.activity.order.fragment.uncomplete.adapter.UncompleteAdapter;
import com.example.reby.main.fragment.sale.model.SaleModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnCompleteFragment extends Fragment {


    public UnCompleteFragment() {
        // Required empty public constructor
    }

    private List<SaleModel> saleModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_un_complete, container, false);
        UncompleteAdapter uncompleteAdapter = new UncompleteAdapter(saleModelList);
        initUncompleteView();
        initRecyclerView(view, uncompleteAdapter);
        return view;
    }

    /**
     * 初始化RecyclerView
     * @param view
     * @param uncompleteAdapter
     */

    private void initRecyclerView(View view, UncompleteAdapter uncompleteAdapter) {
        RecyclerView recyclerView = view.findViewById(R.id.un_complete_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(uncompleteAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
    }

    /**
     * 给RecyclerView添加数据
     */

    public void initUncompleteView() {

        for (int i = 0; i < 10; i++) {
            SaleModel first = new SaleModel(R.drawable.ic_blue_skirte, "淑女学生蕾丝连衣裙，修身甜美小清新",
                    "189.2", "2019.4.17", "1000");
            saleModelList.add(first);
        }

    }

}
