package com.example.reby;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UncompleteAdapter extends RecyclerView.Adapter<UncompleteAdapter.ViewHolder> {
    private List<SaleModel> mSaleModelList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageId;
        TextView name;
        TextView price;
        TextView deadLine;
        TextView participant;

        public ViewHolder(View view){
            super(view);
            imageId = (ImageView)view.findViewById(R.id.un_complete_image);
            name = (TextView)view.findViewById(R.id.un_complete_name);
            price = (TextView)view.findViewById(R.id.un_complete_price);
            deadLine = (TextView)view.findViewById(R.id.un_complete_deadLine);
            participant = (TextView)view.findViewById(R.id.un_complete_participant);
        }

    }
    public UncompleteAdapter(List<SaleModel> saleModelList){
        mSaleModelList = saleModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_uncomplete_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        SaleModel saleModel = mSaleModelList.get(i);
        holder.imageId.setImageResource(saleModel.getImgLadyId());
        holder.name.setText(saleModel.getSaleName());
        holder.price.setText(saleModel.getSalePrice());
        holder.deadLine.setText(saleModel.getDeadLine());
        holder.participant.setText(saleModel.getParticipateNumber());
    }


    @Override
    public int getItemCount() {
        return mSaleModelList.size();
    }
}
