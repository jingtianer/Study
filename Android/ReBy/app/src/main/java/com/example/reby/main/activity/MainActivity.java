package com.example.reby.main.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.reby.main.fragment.home.fragment.HomeFragment;
import com.example.reby.main.fragment.message.fragment.MessageFragment;
import com.example.reby.R;
import com.example.reby.main.fragment.sale.fragment.SaleFragment;
import com.example.reby.main.fragment.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioButton homeRbtn, saleRbtn, messageRbtn, userRbtn;
    private RadioGroup mRadioGroup;
    public Fragment fragment;
    private FragmentManager fragmentManager;//还没了解过
    private FragmentTransaction transaction;//没了解过
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 设置状态栏的半透明
         */
        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        setContentView(R.layout.activity_main);

         fragments = getFragments();//把获取的碎片添加到fragments里
         initView();//初始化视图
         mRadioGroup.setOnCheckedChangeListener(this);//给下方导航栏里的button设置监听器
         normalFragment();//设置打开时跳出的第一个碎片界面
    }

    /**
     * 将碎片添加到容器
     */
    public List<Fragment> getFragments(){
        fragments.add(new HomeFragment());
        fragments.add(new SaleFragment());
        fragments.add(new MessageFragment());
        fragments.add(new UserFragment());
        return fragments;
    }

    /**
     * 初始化界面
     */
    private void initView(){
        mRadioGroup = (RadioGroup) findViewById(R.id.group_main);
        homeRbtn = (RadioButton)findViewById(R.id.rbtn_main_home);
        saleRbtn = (RadioButton)findViewById(R.id.rbtn_main_sale);
        messageRbtn = (RadioButton)findViewById(R.id.rbtn_main_message);
        userRbtn = (RadioButton)findViewById(R.id.rbtn_main_user);
        Drawable drawableHomeNormal = getResources().getDrawable(R.drawable.ic_bottom_home_normal);
        drawableHomeNormal.setBounds(0,0,80,80);
        homeRbtn.setCompoundDrawables(null,drawableHomeNormal,null,null);

        Drawable drawableSale = getResources().getDrawable(R.drawable.ic_bottom_sale_normal);
        drawableSale.setBounds(0,0,80,80);
        saleRbtn.setCompoundDrawables(null,drawableSale,null,null);

        Drawable drawableMessage = getResources().getDrawable(R.drawable.ic_bottom_message_normal);
        drawableMessage.setBounds(0,0,80,80);
        messageRbtn.setCompoundDrawables(null,drawableMessage,null,null);

        Drawable drawableUser = getResources().getDrawable(R.drawable.ic_bottom_user_normal);
        drawableUser.setBounds(0,0,80,80);
       userRbtn.setCompoundDrawables(null,drawableUser,null,null);

    }

    /**
     * 设置打开的首页
     */
    private void normalFragment(){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        fragment = fragments.get(0);
        transaction.replace(R.id.main_fragment,fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 根据选择替换不同的页面
     * @param group 首页下方几个radiobutton的集合
     * @param checkedId 被点击的id
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch(checkedId){
            case R.id.rbtn_main_home:
                fragment = fragments.get(0);
                transaction.replace(R.id.main_fragment,fragment);
                break;
            case R.id.rbtn_main_sale:
                fragment = fragments.get(1);
                transaction.replace(R.id.main_fragment,fragment);
                break;
            case R.id.rbtn_main_message:
                fragment = fragments.get(2);
                transaction.replace(R.id.main_fragment,fragment);
                break;
            case R.id.rbtn_main_user:
                fragment = fragments.get(3);
                transaction.replace(R.id.main_fragment,fragment);
                break;
        }
        transaction.commit();//这个千万别忘，血和泪的教训
    }


}
