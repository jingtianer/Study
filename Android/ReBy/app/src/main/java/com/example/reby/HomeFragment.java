package com.example.reby;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


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
    private List<GoodsModel> goodsModelList = new ArrayList<>();


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(!VIEWPAGERSCROLLSTATUS){
                viewPager.setCurrentItem(++CURRENTPAGE);
            }
            handler.sendEmptyMessageDelayed(1,3000);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());//不一样

        recyclerView.setFocusable(false);//设置页面在从顶端开始


        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

       // recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager (new GridLayoutManager(getActivity (),2,layoutManager.VERTICAL,false));//简单的更换列数
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(goodsModelList);
        recyclerView.setAdapter(adapter);

        data = new ArrayList<>();
        data.add(R.drawable.ic_flower_1);
        data.add(R.drawable.ic_tower_1);
        data.add(R.drawable.ic_flower_2);
        data.add(R.drawable.ic_tower_2);
        data.add(R.drawable.ic_tower_3);
        viewPager = view.findViewById(R.id.viewpager);
        viewPageAdapter = new ViewPageAdapter(data);
        viewPager.setAdapter(viewPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int postionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                if(position == data.size()-1){
                    CURRENTPAGE = 0;
                }else if(position == 0){
                    CURRENTPAGE = data.size() - 5;
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

        handler.sendEmptyMessageDelayed(1,3000);
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

        initGoodmodel();


        return view;


    }
    public void initGoodmodel(){
        for(int i = 0; i<10;i++){
            GoodsModel first = new GoodsModel(R.drawable.ic_uniform,"￥100.8","青春的回忆，带给你最清纯的美好",R.drawable.ic_sale,R.drawable.ic_no_thing_for_thing,R.drawable.ic_no_talk);
            goodsModelList.add(first);
            GoodsModel second = new GoodsModel(R.drawable.ic_uniform,"￥200.0","青春的回忆，带给你最清纯的美好",R.drawable.ic_sale, R.drawable.ic_thing_for_thing,R.drawable.ic_no_talk);
            goodsModelList.add(second);
            GoodsModel third = new GoodsModel(R.drawable.ic_uniform,"￥400.0","青春的回忆，带给你最清纯的美好",R.drawable.ic_no_sale,R.drawable.ic_thing_for_thing,R.drawable.ic_no_talk);
            goodsModelList.add(third);
            GoodsModel fourth = new GoodsModel(R.drawable.ic_uniform,"￥250.0","青春的回忆，带给你最清纯的美好",R.drawable.ic_sale, R.drawable.ic_thing_for_thing,R.drawable.ic_no_talk);
            goodsModelList.add(fourth);

        }
    }
}
