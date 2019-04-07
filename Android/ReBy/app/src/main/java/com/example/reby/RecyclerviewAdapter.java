package com.example.reby;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>{
    private List<GoodsModel> mGoodsList;
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
    public RecyclerviewAdapter(List<GoodsModel> goodsModelList){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoodsModel goodsModel = mGoodsList.get(position);
        holder.goodsImage.setImageResource(goodsModel.getImageId());
        holder.goodsPrice.setText(goodsModel.getGoodsPrice());
        holder.goodsIntroduction.setText(goodsModel.getIntroduction());
        holder.goodsSale.setImageResource(goodsModel.getImg_saleId());
        holder.goodsThing.setImageResource(goodsModel.getImg_thingId());
        holder.goodNoTalk.setImageResource(goodsModel.getImg_notalkId());
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }
}
