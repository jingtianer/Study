package com.example.reby.main.fragment.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reby.main.fragment.home.model.GoodsModel;
import com.example.reby.R;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<GoodsModel> mGoodsList;
    public  interface OnItemClickListener{
       void onClick(int position);
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView goodsImage;
        TextView goodsPrice;
        TextView goodsIntroduction;
        ImageView goodsSale;
        ImageView goodsThing;
        ImageView goodNoTalk;
        public ViewHolder(View view){
            super(view);
            goodsImage = (ImageView) view.findViewById(R.id.goodsImage);
            goodsPrice = (TextView) view.findViewById(R.id.price);
            goodsIntroduction = (TextView) view.findViewById(R.id.goods_introduction);
            goodsSale = (ImageView) view.findViewById(R.id.sale);
            goodsThing = (ImageView) view.findViewById(R.id.thing_for_thing );
            goodNoTalk = (ImageView) view.findViewById(R.id.no_talk);
        }
    }
    public HomeAdapter(List<GoodsModel> goodsModelList){
        mGoodsList = goodsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        GoodsModel goodsModel = mGoodsList.get(position);
        holder.goodsImage.setImageResource(goodsModel.getImageId());
        holder.goodsPrice.setText(goodsModel.getGoodsPrice());
        holder.goodsIntroduction.setText(goodsModel.getIntroduction());
        holder.goodsSale.setImageResource(goodsModel.getImg_saleId());
        holder.goodsThing.setImageResource(goodsModel.getImg_thingId());
        holder.goodNoTalk.setImageResource(goodsModel.getImg_notalkId());
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
        return mGoodsList.size();
    }



}
