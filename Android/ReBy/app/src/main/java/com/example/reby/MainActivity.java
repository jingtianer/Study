package com.example.reby;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioButton radio_btn_home, radio_btn_sale, radio_btn_message, radio_btn_user;
    private RadioGroup mRadioGroup;
    public Fragment fragment;
    private FragmentManager fragmentManager;//还没了解过
    private FragmentTransaction transaction;//没了解过
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);

         fragments = getFragments();//把获取的碎片添加到fragments里
         initView();
         mRadioGroup.setOnCheckedChangeListener(this);
         normalFragment();
    }
    public List<Fragment> getFragments(){
        fragments.add(new HomeFragment());
        fragments.add(new SaleFragment());
        fragments.add(new MessageFragment());
        fragments.add(new UserFragment());
        return fragments;
    }

    private void initView(){
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        radio_btn_home = (RadioButton)findViewById(R.id.bottom_radio_button_home);
        radio_btn_sale = (RadioButton)findViewById(R.id.bottom_radio_button_sale);
        radio_btn_message = (RadioButton)findViewById(R.id.bottom_radio_button_message);
        radio_btn_user = (RadioButton)findViewById(R.id.bottom_radio_button_user);

    }
    private void normalFragment(){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        fragment = fragments.get(0);
        transaction.replace(R.id.main_fragment,fragment);
        transaction.commit();
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch(checkedId){
            case R.id.bottom_radio_button_home:
                fragment = fragments.get(0);
                transaction.replace(R.id.main_fragment,fragment);
                break;
            case R.id.bottom_radio_button_sale:
                fragment = fragments.get(1);
                transaction.replace(R.id.main_fragment,fragment);
                break;
            case R.id.bottom_radio_button_message:
                fragment = fragments.get(2);
                transaction.replace(R.id.main_fragment,fragment);
                break;
            case R.id.bottom_radio_button_user:
                fragment = fragments.get(3);
                transaction.replace(R.id.main_fragment,fragment);
                break;
        }
        transaction.commit();//这个千万别忘，血和泪的教训
    }

}
