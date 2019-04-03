package com.example.reby;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    public HomeFragment() {
        // Required empty public constructor
    }


    ViewPager viewPager;
    ViewPageAdapter viewPageAdapter;
    List<Integer> data;
    List<String> titleData;
    int CURRENTPAGE = 0;
    boolean VIEWPAGERSCROLLSTATUS = false;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(!VIEWPAGERSCROLLSTATUS){
                viewPager.setCurrentItem(++CURRENTPAGE);
            }
            handler.sendEmptyMessageDelayed(1,1000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        data = new ArrayList<>();
        data.add(R.drawable.hua_1);
        data.add(R.drawable.hua_2);
        data.add(R.drawable.lou_1);
        data.add(R.drawable.lou_2);
        data.add(R.drawable.lou_3);
        viewPager = (ViewPager)getView().findViewById(R.id.viewpager);
        viewPageAdapter = new ViewPageAdapter(data);
        viewPager.setAdapter(viewPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int postionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == data.size()-1){
                    CURRENTPAGE = 1;
                }else if(position == 0){
                    CURRENTPAGE = data.size() - 2;
                }else{
                    CURRENTPAGE = position;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE){
                    VIEWPAGERSCROLLSTATUS = false;
                    viewPager.setCurrentItem(CURRENTPAGE,false);
                }else{
                    VIEWPAGERSCROLLSTATUS = true;
                }

            }
        });
        handler.sendEmptyMessageDelayed(1,1000);
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                int width = page.getWidth();
                if(position < -1){
                    page.setScrollX((int)(width * 0.75 * -1));
                }else if(position <= 1){
                    if(position < 0){
                        page.setScrollX((int)(width * 0.75*position));
                    }else{
                        page.setScrollX((int)(width*0.75*position));
                    }
                }else{
                    page.setScrollX((int)(width*0.75));
                }

            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }



}
