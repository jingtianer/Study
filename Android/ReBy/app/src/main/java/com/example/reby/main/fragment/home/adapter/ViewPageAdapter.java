package com.example.reby.main.fragment.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class ViewPageAdapter extends PagerAdapter {
    private List<Integer> data;

    public ViewPageAdapter(List<Integer> data){
        this.data = data;
    }
    @Override
    public int getCount(){
        return data.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container,int position){
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setBackgroundResource(data.get(position));
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup comtainer, int position, Object object){
        comtainer.removeView((View)object);
    }
}
