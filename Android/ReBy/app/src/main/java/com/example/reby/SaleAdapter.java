package com.example.reby;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder> {
    private List<SaleModel> mSaleModelList;

    public  interface OnItemClickListener{
        void onClick(int position);
    }

    private SaleAdapter.OnItemClickListener listener;
    public void setOnItemClickListener(SaleAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView saleLady;
        TextView saleName;
        TextView salePrice;
        TextView saleDedLine;
        TextView saleParticipateNumer;
        public ViewHolder(View view){
            super(view);
            saleLady = (ImageView)view.findViewById(R.id.sale_lady);
            saleName = (TextView)view.findViewById(R.id.sale_name);
            salePrice = (TextView)view.findViewById(R.id.sale_price);
            saleDedLine = (TextView)view.findViewById(R.id.deadline);
            saleParticipateNumer = (TextView)view.findViewById(R.id.participant);
        }
    }
    public SaleAdapter(List<SaleModel> saleModelList){
        mSaleModelList = saleModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sale_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        SaleModel saleModel = mSaleModelList.get(position);
        holder.saleLady.setImageResource(saleModel.getImgLadyId());
        holder.saleName.setText(saleModel.getSaleName());
        holder.salePrice.setText(saleModel.getSalePrice());
        holder.saleDedLine.setText(saleModel.getDeadLine());
        holder.saleParticipateNumer.setText(saleModel.getParticipateNumber());

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
        return mSaleModelList.size();
    }
}
