package com.example.reby.main.fragment.message.fragment;


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

import com.example.reby.main.fragment.message.adapter.MessageAdapter;
import com.example.reby.R;
import com.example.reby.main.fragment.message.activity.MessageDetailActivity;
import com.example.reby.main.fragment.message.model.MessageModel;
import com.example.reby.util.CircleImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private List<MessageModel> messageModelList = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        MessageAdapter messageAdapter = new MessageAdapter(messageModelList);
        itemClick(messageAdapter);
        initRecyclerView(view,messageAdapter);
        messageModelList.clear();
       initMessageView();
       return view;
    }

    private void itemClick(MessageAdapter messageAdapter){
        messageAdapter.setOnItemClickListener(new  MessageAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position){
                Intent intent = new Intent(getActivity(), MessageDetailActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initRecyclerView(View view, MessageAdapter messageAdapter){

        RecyclerView recyclerView = view.findViewById(R.id.message_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(messageAdapter);

    }

    public void initMessageView(){
        MessageModel first = new MessageModel(R.drawable.ic_sale_lady,"阿里郎","更换货物","2018.9.12");
        messageModelList.add(first);

        MessageModel second = new MessageModel(R.drawable.ic_sale_lady,"ck67","申请退款","2019.4.17");
        messageModelList.add(second);
    }



}
