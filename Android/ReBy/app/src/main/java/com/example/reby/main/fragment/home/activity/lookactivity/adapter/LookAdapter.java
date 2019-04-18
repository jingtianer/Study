package com.example.reby.main.fragment.home.activity.lookactivity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reby.main.fragment.home.activity.lookactivity.model.LookModel;
import com.example.reby.R;

import java.util.List;

public class LookAdapter extends RecyclerView.Adapter<LookAdapter.ViewHolder>{
    private List<LookModel> mLookModelList;

    public LookAdapter(List<LookModel> lookModelList){
        mLookModelList = lookModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.look_around_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        LookModel lookModel = mLookModelList.get(i);
        holder.lookImage.setImageResource(lookModel.getImageId());
        holder.lookName.setText(lookModel.getLookName());
        holder.lookPrice.setText(lookModel.getLookPrice());
        holder.lookNmber.setText(lookModel.getViewNumber());
    }

    @Override
    public int getItemCount() {
        return mLookModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView lookImage;
        TextView lookName;
        TextView lookPrice;
        TextView lookNmber;
        public ViewHolder(View view){
            super(view);
            lookImage = (ImageView)view.findViewById(R.id.look_lady);
            lookName = (TextView)view.findViewById(R.id.look_name);
            lookPrice = (TextView)view.findViewById(R.id.look_price);
            lookNmber = (TextView)view.findViewById(R.id.look_number);
        }
    }
}
