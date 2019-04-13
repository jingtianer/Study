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
public class UnCompleteFragment extends Fragment {


    public UnCompleteFragment() {
        // Required empty public constructor
    }

    private List<SaleModel>saleModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_un_complete,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.un_complete_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());



        UncompleteAdapter uncompleteAdapter = new UncompleteAdapter(saleModelList);

        recyclerView.setFocusable(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(uncompleteAdapter);

        initUncompleteView();

        // Inflate the layout for this fragment
        return view;
    }

    public void initUncompleteView(){

        for(int i = 0; i<10; i++){
            SaleModel first = new SaleModel(R.drawable.ic_blue_skirte,"淑女学生蕾丝连衣裙，修身甜美小清新","189.2","2019.4.17","1000");
            saleModelList.add(first);
        }

    }

}
