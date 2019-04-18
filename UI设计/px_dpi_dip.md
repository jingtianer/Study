
# android - px(像素)、dpi(像素密度)、dip(密度无关像素)之间的关系 

-->[android - px(像素)、dpi(像素密度)、dip(密度无关像素)之间的关系 ](https://www.cnblogs.com/yongdaimi/p/6170982.html)


## 使用ImageView会遇到的问题
 
在Android应用中，都少不了图片的显示，ImageView，轮播图，ViewPager等等，很多都是来显示图片的，很多时候，我们都希望图片能够在宽度上填充父窗体，这样比较符合人的审美观点，但是问题就随之而来了，那就是高度如何定义？？先来看一个普通的ImageView的 Xml布局文件的定义：
```
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >
    
    
    <ImageView
        android:id="@+id/iv_img"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:src="@drawable/play"
        android:background="#0000ff"
        />
    
    
    <TextView 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="描述文字信息..."
        />
    
</LinearLayout>
```
为了方便查看，我在ImageView下面又加上了一句描述的信息的TextView，这时，父控件都是填充父窗体，而ImageView则是：横向填充父窗体，纵向包裹内容；text都是包裹内容；那么来看看显示效果：
![](https://images2015.cnblogs.com/blog/653161/201612/653161-20161213171743839-1934689525.png)  

上面那个蓝色的小框就是ImageView的范围，这种效果一般都不会是我们想要的，那么如果想要ImageView中的图片能够填满ImageView的整个窗体怎么办？添加一个属性：scaleType，如下：
```
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >
    
    
    <ImageView
        android:id="@+id/iv_img"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:src="@drawable/play"
        android:background="#0000ff"
        android:scaleType="fitXY"
        />
    
    
    <TextView 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="描述文字信息..."
        />
    
</LinearLayout>
```

![](https://images2015.cnblogs.com/blog/653161/201612/653161-20161213171932370-1613779788.png)

可以看到，填是填满了，但是也由于纵向拉伸而使图片变形了。那要怎么做呢？
 
我们仔细观察一下，不难发现ImageView的纵向高度是包裹内容：wrapcontent，有些同学可能想到，能够直接在这里给ImageView一个特定的dip值，让这个ImageView符合图片的宽高比呢？这样做无疑是可以的，但是却不具有通用性。。。下面还是用上面的例子来讲解：  
 
我们先来实现一下，制定ImageView的高度，来达到让这张图片在这个特定模拟器下显示比例正常的过程：  
 
上面的图片实际像素的尺寸是：828*314，宽度和高度的比例大约是2.63，而我们这里使用的模拟器的尺寸是1280*768(单位是px，也就是像素)，也就是说宽度上的像素是768，那么我们要设置这个ImageView的高度为多少dip才能够让其正好符合2.63的比例呢。    

---  

## Android设备上px(像素)、dpi(像素密度)、dip(密度无关像素)之间的关系：

+ **分辨率(Resolution)**：
>表示设备屏幕上像素点的总数，比如上面的模拟器，屏幕像素尺寸是480(px)*800(px)

+ **dpi(像素密度)**：
>是指每英寸的像素，所以同分辨率的两个设备，它们的dpi很可能不一样；如果一个手机分辨率5寸是1080*1920，而一个平板9.7寸分辨率也是1080*1920，那么 手机的dpi会比平板高出很多。

+ **dp/dip**：
>全称是Density-independent pixel ，中文名是 “密度无关像素”，也就是我们经常在xml文件中写的长度单位dp。为什么叫做密度无关像素呢，这其实是为了解决不同分辨率设备显示效果统一的一个解决方案，试想，如果一个两个手机屏幕都是一样大小，比如5寸，A手机的分辨率是 720*1280，而B手机的分辨率是1080*1920；那么如果我们想在上面显示一个图片分辨率为：200*200的图片，就会发现，在A手机上显示的图片，比B手机上显示的图片要小了很多；直观的来看，A手机的宽度是720，显示200*200的图片差不多要占据将近1/3的宽度，而反观B手机，宽度是1080，显示200*200的图片，则只需要占据1/5不到的宽度，而两个手机的尺寸又都是5寸，所以就会在显示同样分辨率的图片时，产生大小的差异。这种差异明显不是我们想要的。 


所以dip/dp，密度无关像素就应运而生；它是这样规定的，dip与一个dpi(像素密度)为160dpi的设备的px(像素)值是相等的，而对于其他像素密度的设备，则依据转换公式来计算对应的dip值，这个公式是根据dpi(相当于比例)，来转换px(像素)和dip(密度无关像素)的：
```
px = dip * (dpi / 160)

dip = px / (dpi / 160)
```

---

## 重写onMeasure方法，重新测量控件高度，实现多种屏幕下自适应图片显示

思路就是让控件(ImageView)自己根据不同的设备帮我们来计算这个高度，而不需要我们自己去计算，那要怎么做呢？  
首先要明确的一点就是，自定的view在调用view.measure()之前，是得不到控件的宽和高的，下面就一步步来写：

思路是首先写一个SmartImageView来继承自ImageView，并且添加相应的构造：

```
package com.example.test.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SmartImageView extends ImageView {

    
    // initWithFrame
    public SmartImageView(Context context) {
        super(context);
    }

    // initWithCoder awakeFromXib
    public SmartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    
}
```

然后在SmartImageView中，添加一个float类型的成员变量ratio作为图片的比例值，并且给它暴露一个setter方法，以便于设置图片比例。
```
/**
     * 图片宽高比
     * 
     */
    private float ratio = 2.63f;
    
    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
```

然后我们来重写onMeausre方法，如下：
```
@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        
        // 宽度方向上的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 高度方向上的测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        
        // paddingLeft 类似于 iOS中的 UIEdgeInsert
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        
        /**
         * 判断依据
         * 宽度为Exactly,也就是填充父窗体或者指定宽度
         * 且高度不为Exactly,代表设置的既不是fill_parent也不是具体的值，需要具体测量
         * 且图片宽度比已经赋值完毕，不再是0.0f
         * 表示宽度确定，需要确定高度
         * MeasureSpec类似于ios中的CGSize,比CGSize多了一个mode属性
         */
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio != 0.0f) {
            height = (int) (width / ratio + 0.5f); // 高度实际值(px)
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY && ratio != 0.0f) {
            // 判断依据与上面相反,表示高度确定,需要测量宽度
            width = (int) (height * ratio + 0.5f);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }
        
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
```

对于onMeasure方法，有几点需要注意的：  
+ 父容器传过来的两个参数widthMeasureSpec和heightMeasureSpec，通过MeasureSpec.getMode()来获取参数中的模式，与控件的填充方式都是有对应关系的  

    + xml布局文件中的fill_parent或具体值，或者是直接设置控件的LayoutParams中的width和height的具体值或者LayoutParams.FILL_PARENT填充父容器方式，都会对应让上面通过getMode获取参数中的模式为：MeasureSpect.EXACTLY，代表精确取值，因为除了直接指定值之外，填充父容器，也是精确值
  
    + xml布局文件中设置wrap_content方式或者是在代码中设置LayoutParams.WRAP_CONTENT方式，都会让getMode变成MeasureSpect.AT_MOST
 
+ 对于父容器传过来的高度或者宽度的值，不一定就是控件想要的宽度或者高度的值，这是因为模式不一样，这个值代表的意义也不一样，所以才会需要通过测量来改变高度或者宽度的值来达到想要的效果。
其中，如果是模式是EXACTLY，那么传过来的值就是具体指，也可以说是父容器想要我们的控件变成这个具体的大小。
但是模式如果是AT_MOST，那么传过来的值，就不会是具体的值，一般会是一个最大值，因为AT_MOST代表，不超过多少，那么这个值就是不超过的上限。
 
+ 可以看到我们通过拿到父容器传过来的高度，宽度的模式和值，然后经过两种if-else判断来重新测量值的大小，这两种判断的依据就是：  
    + 当宽度确定时(宽度为EXACTLY)，高度模式不是EXACTLY时(也即高度不确定时)，高度按照ratio的比例来重新测量  
    + 当高度确定时(高度为EXACTLY)，高度模式不是EXACTLY时(也即高度不确定时)，宽度按照ratio的比例来重新测量
 
+ 在测量完毕之后，因为已经得到了想要的宽度或者高度的具体的精确的值，我们再通过MeasureSpec.makeMeasureSpec()方法来调用精确的值和精确的模式，来合成一个宽度/高度方向上的合成值，最后将合成好的值传递给super.onMeasure(widthMeasureSpec, heightMeasureSpec);设置控件为我们想要的大小。
 
然后我们就可以在XML布局文件中，将之前的ImageView改成：com.example.imageviewdemo.SmartImageView
 
然后在代码中将其通过findviewbyid拿到它的对象，然后通过setRatio来设定图片的比例，如下：
```
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >
    
    
    <com.example.test.view.SmartImageView
        android:id="@+id/iv_img"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:src="@drawable/play"
        android:background="#0000ff"
        />
    
    
    <TextView 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="描述文字信息..."
        />
    
</LinearLayout>
```

```
package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import com.example.test.view.SmartImageView;

public class MainActivity extends Activity {

    
    private SmartImageView iv_img;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        iv_img = (SmartImageView) findViewById(R.id.iv_img);
        iv_img.setRatio(2.63f);
        
    /*    *//**
         * 获取屏幕尺寸参数
         * ios获取方式 [UIScreen mainScreen].bounds
         *//*
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        

        Log.i("test", "宽度(px): "+metrics.widthPixels+" 高度(px): "+metrics.heightPixels+" dpi: "+metrics.densityDpi+" dpi/160: "+metrics.density);*/
        
    }

    
}
```