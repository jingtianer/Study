# 底部导航栏的实现-RadioButton+Fragment 
安卓的底部导航栏的实现方法是真的多啊，乱花渐欲迷人眼，差一点就让我迷失在代码的海洋里。  根据我在网上看到那么多代码，对于底部导航栏的实现主要有两种方式    + TextView+IamgeView+Fragment+ RadioGroup+Fragment  代码都比较长而且对于我这样的初学者来说，也是比较复杂，最后写出的代码也是吃了百家饭，才算是实现了一下基本的底部导航栏。
## RadioGroup+Fragment   
首先来看看利用RadioGroup来实现的（因为自己就是用的这个方法，所以熟悉一些）   Fragment，碎片，咱们先放在一边，先把基础的脸面画好——xml。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190402115031816.PNG)  
首先你要新建四个Fragment,跟新建类其实差不多，只不过它有一个选项写着Fragment,你新建的时候就知道了。

主要的代码就是home,message,sale,user，和一个main。
首先先看一下activity_main
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#ffffff"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <FrameLayout
        android:id="@+id/main_fragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1">
    </FrameLayout>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="68dp">
        <RadioGroup
            android:id="@+id/main_radio_group"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal"
            android:background="#EDEDED">

            <RadioButton
                android:id="@+id/bottom_radio_button_home"
                style="@style/radiobutton_style"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableTop="@drawable/bottom_radio_button_home_selector"
                android:text="首页" />
            <RadioButton
                android:id="@+id/bottom_radio_button_sale"
                style="@style/radiobutton_style"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableTop="@drawable/bottom_radio_button_sale_selector"
                android:text="拍卖" />
            <RadioButton
                android:id="@+id/bottom_radio_button_message"
                style="@style/radiobutton_style"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableTop="@drawable/bottom_radio_button_message_selector"
                android:text="消息" />
            <RadioButton
                android:id="@+id/bottom_radio_button_user"
                style="@style/radiobutton_style"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableTop="@drawable/bottom_radio_button_user_selector"
                android:text="用户" />
        </RadioGroup>
    </LinearLayout>


</LinearLayout>
```
其他代码都好理解，但是这个style是个啥？selector又是啥？我们一个一个看。
### style
其实style就是一种自定义的风格，用来节省我们的代码量，它在values文件夹里的styles.xml文件里。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190402115550797.PNG)
看看代码：
```
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
    <style name="MyEditText" parent="Theme.AppCompat.Light">
        <item name="colorControlNormal">@android:color/darker_gray</item>
        <item name="colorControlActivated">@android:color/holo_orange_dark</item>
    </style>
    <style name="radiobutton_style">
        <item name="android:layout_width">0dp</item>
        <item name="android:padding">2dp</item>
        <item name="android:layout_height">match_parent</item>
        <item name="android:background">@null</item>
        <item name="android:layout_weight">1</item>
        <item name="android:button">@null</item>
        <item name="android:gravity">center</item>
        <item name="android:textSize">12sp</item>
        <item name="android:textColor">@drawable/bottom_radio_button_text_selector</item>
    </style>
</resources>

```
这里有一个小技巧，radio button一般情况下都有一个小点点，怎么去掉？
```
<item name="android:button">@null</item>
```
就靠这一句。  

然后你会发现，最后一句
```
<item name="android:textColor">@drawable/bottom_radio_button_text_selector</item>
```
它也引用了一个xxx_selector，那我们就去找找selector在哪。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190402120709243.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyODk4Mjk5,size_16,color_FFFFFF,t_70)

selector是我们在新建drawable文件时需要自己选择的类型，你打开就可以看见。
比如我的bottom_radio_button_home_selector里，就有如下代码：
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/ic_bottom_home_normal" android:state_checked="false"></item>
    <item android:drawable="@drawable/ic_bottom_home_pressed" android:state_checked="true"></item>
</selector>
```
那么在这里，我们可以看到，只有两个item，你如果还看不懂，可以看看一个单词，state_checked状态被检查，如果是false,没被点击，就引用一个灰色的图，如果点击了，就引用一个橘黄色的图（当然这些图都是我们提前加好的），其他四个底部图标也是如此。
当然，点击之后，字体颜色也要发生变化，所以也有一个text_selector。

而且，因为字体只是改变颜色，所以我们只需要把字体的风格统一加到style文件里的button_style里了，但是图片每个图标都不同，所以这个要分别在每个button里面添加。
```
<RadioButton
                android:id="@+id/bottom_radio_button_sale"
                style="@style/radiobutton_style"//引用style
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableTop="@drawable/bottom_radio_button_sale_selector"//引用图片的selector
                android:text="拍卖" />
```

---
## 布局文件弄好了，接下来就该看活动和碎片了

在我的理解中，fragment是必须寄生在某个活动下面的，就比如这四个碎片，就寄生在MainActivity里面，代码注释我觉得自己写的很浅显，不过也方便自己理解。

```
package com.example.reby;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        /*
        设置状态栏的半透明
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

    /*
    将四个碎片添加到容器中
     */
    public List<Fragment> getFragments(){
        fragments.add(new HomeFragment());
        fragments.add(new SaleFragment());
        fragments.add(new MessageFragment());
        fragments.add(new UserFragment());
        return fragments;
    }

    /*
    初始化界面
     */
    private void initView(){
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        radio_btn_home = (RadioButton)findViewById(R.id.bottom_radio_button_home);
        radio_btn_sale = (RadioButton)findViewById(R.id.bottom_radio_button_sale);
        radio_btn_message = (RadioButton)findViewById(R.id.bottom_radio_button_message);
        radio_btn_user = (RadioButton)findViewById(R.id.bottom_radio_button_user);

    }

    /*
    设置打开的首页
     */
    private void normalFragment(){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        fragment = fragments.get(0);
        transaction.replace(R.id.main_fragment,fragment);
        transaction.commitAllowingStateLoss();
    }

    /*
    根据选择，替换不同的碎片
     */
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

```

关于transaction.commit()方法我暂时没有找到比较好的解释，有点用的解释都是关于同步异步的，还不太懂，所以我先码住，以后懂了再看：
>FragmentTransaction是异步的，commit()仅是相当于把操作加入到FragmentManager的队列，然后FragmentManager会在某一个时刻来执行，并不是立即执行。所以，真正开始执行commit()时，如果Activity的生命周期发生了变化，比如走到了onPause，或者走到了onStop，或者onDestroy都走完了，那么就会报出IllegalStateException。    

-->[Android实战技巧：Fragment的那些坑](http://toughcoder.net/blog/2015/04/30/android-fragment-the-bad-parts/)  

>当你调用了碎片空的构造器的时候，你的碎片并不会执行生命周期的方法，如onCreateView（）等   
什么时候会执行生命周期呢？
```
FragmentManager manager = getSupportFragmentManager();
FragmentTransaction transaction = manager.beginTransaction();
transaction.add(mContainerId, mFragment);
transaction.commit();
```
>当你把这个碎片add进fragmentmanager（碎片栈），或者replace（是remove和add的结合体）进去的时候，你才会进入碎片的生命周期。  

-->[Android碎片事务提交transaction.commit()和transaction.commitnow()的区别以及源码完全解析](https://blog.csdn.net/qq_36523667/article/details/78656078
)

---
## Fragment
这里面其实先不用动，等以后开发其他功能，也是有很多要写的。