package com.example.reby;


import android.content.Intent;
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
public class MessageFragment extends Fragment {

    private CircleImageView iv_circle;
    public MessageFragment() {
        // Required empty public constructor
    }


    private List<MessageModel> messageModelList = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.message_view);
        MessageAdapter messageAdapter = new MessageAdapter(messageModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(messageAdapter);

        messageAdapter.setOnItemClickListener(new  MessageAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position){
                Intent intent = new Intent(getActivity(),MessageDetailActivity.class);
                startActivity(intent);
            }
        });

        messageModelList.clear();
       initMessageView();

       // iv_circle = (CircleImageView)view.findViewById(R.id.image1);
        // Inflate the layout for this fragment

        return view;
    }

    public void initMessageView(){
        MessageModel first = new MessageModel(R.drawable.ic_sale_lady,"阿里郎","更换货物","2018.9.12");
        messageModelList.add(first);

        MessageModel second = new MessageModel(R.drawable.ic_sale_lady,"ck67","申请退款","2019.4.17");
        messageModelList.add(second);
    }



}
