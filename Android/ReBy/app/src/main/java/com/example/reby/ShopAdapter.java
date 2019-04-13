package com.example.reby;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private List<ShopModel> mShopModelList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView shopImage;
        TextView shopName;
        TextView shopPrice;
        TextView shopView;
        TextView shopIsOn;
        public ViewHolder(View view){
            super(view);
            shopImage = (ImageView)view.findViewById(R.id.shop_image);
            shopName = (TextView)view.findViewById(R.id.shop_name);
            shopPrice = (TextView)view.findViewById(R.id.shop_price);
            shopView = (TextView)view.findViewById(R.id.shop_view);
            shopIsOn = (TextView)view.findViewById(R.id.shop_is_on);

        }
    }
    public ShopAdapter(List<ShopModel> shopModelList){
        mShopModelList = shopModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopModel shopModel = mShopModelList.get(position);
        holder.shopImage.setImageResource(shopModel.getShopImageId());
        holder.shopName.setText(shopModel.getShopName());
        holder.shopPrice.setText(shopModel.getShopPrice());
        holder.shopView.setText(shopModel.getShopView());
        holder.shopIsOn.setText(shopModel.getShopIsOn());
    }

    @Override
    public int getItemCount() {
        return mShopModelList.size();
    }
}
