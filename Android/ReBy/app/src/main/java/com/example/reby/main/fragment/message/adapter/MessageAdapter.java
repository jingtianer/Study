package com.example.reby.main.fragment.message.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reby.R;
import com.example.reby.main.fragment.message.model.MessageModel;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<MessageModel> messageModelList;

    public  interface OnItemClickListener{
        void onClick(int position);
    }

    private MessageAdapter.OnItemClickListener listener;
    public void setOnItemClickListener(MessageAdapter.OnItemClickListener listener){
        this.listener = listener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView portrait;
        TextView name;
        TextView reason;
        TextView time;
        public ViewHolder(View view){
            super(view);
            portrait = (ImageView)view.findViewById(R.id.message_portrait);
            name = (TextView)view.findViewById(R.id.message_name);
            reason = (TextView)view.findViewById(R.id.message_reason);
            time = (TextView)view.findViewById(R.id.message_time);
        }
    }

    public MessageAdapter(List<MessageModel> mesageModelList){
        messageModelList = mesageModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        MessageModel messageModel = messageModelList.get(position);
        holder.portrait.setImageResource(messageModel.getPortraitId());
        holder.name.setText(messageModel.getName());
        holder.reason.setText(messageModel.getReason());
        holder.time.setText(messageModel.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }
}
