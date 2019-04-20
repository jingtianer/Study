# trim函数
trim()的作用是去掉字符串两端的多余的空格，注意，是两端的空格，且无论两端的空格有多少个都会去掉，当然中间的那些空格不会被去掉，如：  
String s = "  a s f g      ";   
String s1 = s.trim();  
那么s1就是"a s f g"，可见，这和上面所说的是一样的。
# 设置下划线和光标的颜色
在style.xml文件里，添加如下代码。
```
<style name="MyEditText" parent="Theme.AppCompat.Light">
        <item name="colorControlNormal">@android:color/darker_gray</item>
        <item name="colorControlActivated">@android:color/holo_orange_dark</item>
    </style>
```
然后在Edittext里添加
```
 android:theme="@style/MyEditText"
```
# 设置按钮的描边
在drawable文件里，新建文件，在Root element中输入shape，然后在新文件里输入
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <stroke
        android:width="2dp"//边框宽度
        android:color="#FFC125"//边框颜色
        />
    <solid android:color="#FFFFFF">//内部主体颜色
    </solid>

</shape>
```  
然后在布局文件中加入
```
<Button
        android:id="@+id/btn_sign_up"
        android:layout_width="@dimen/width_s75"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="@dimen/margin_s3"
        android:background="@drawable/button_shape"
        android:text="@string/text_sign_up"
        android:textSize="@dimen/size_s6"
        >
``` 
直接引用这个文件。 

参考的文章 [Android Button设置圆角和边框和渐变](https://blog.csdn.net/yh18668197127/article/details/84848018
)
# 设置Edittext的输入限制  
```
android:lines="1"
```
# 状态栏的透明与半透明
+ 半透明
```
if (Build.VERSION.SDK_INT >= 21) {      

    getWindow().setFlags(

        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,

        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

}

```
+ 透明
```

        if(Build.VERSION.SDK_INT >= 21) {

            Window window = getWindow();

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            window.setStatusBarColor(Color.TRANSPARENT);

        }

```
位置一般是放在onCreat()方法后面，setContentView()方法前面；
# ActionBar的删除 
```

public class MainActivity extends AppCompatActivity {

 

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();//主要的就是这三行

        if(actionBar != null){//这三行

            actionBar.hide();//三行

        }

    }

``` 
# 状态栏的颜色设置
敬请期待
# 活动的启动模式
![](http://img.blog.csdn.net/20170303202108396?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSVRlcm1lbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
在使用默认启动方式的时候，我每次打开一个活动，它就新建一个，导致需要按多次去返回，而采用singleTask方法，在Manifest文件里添加
```
<activity android:name=".LoginActivity"
            android:launchMode="singleTask">//这个，就是用来注册的
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```
便很好地解决了这个问题。  
具体内容-->[Android：图解四种启动模式及实际应用场景解说 ](https://www.cnblogs.com/claireyuancy/p/7387696.html)
# 密码的隐藏
这个只需要一行代码解决
```
edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
```
# 删除远程仓库的文件夹
[这个就直接看教程，看这里](https://blog.csdn.net/smss007/article/details/80974219
)  

---  
# 在按钮之间画一条直线  

很简单，直接在按钮之间写
```
 <View
        android:background="@color/colorAccent"
        android:layout_width="match_parent"
        android:layout_height="1dp"/>
```
然后就有线了。
# 设置背景色的透明度
我是万万没想到啊，安卓居然能调颜色的透明度。

---
# 等比缩放
android:scaleType可控制图片的缩放方式，示例代码如下：
```

<ImageView android:id="@+id/img" 

    android:src="@drawable/logo"

    android:scaleType="centerInside"

    android:layout_width="60dip"

    android:layout_height="60dip"

    android:layout_centerVertical="true"/>

```
说明：centerInside表示按比例缩放图片，使得图片长 (宽)的小于等于视图的相应维度。
　　注意：控制的图片为资源而不是背景，即android:src="@drawable/logo"，而非android:background="@drawable/logo"，我就笨笨地犯了这个低级错误，导致错怪人家scaleType不起作用。程序中动态加载图片也类似，如：应该imgView.setImageResource(R.drawable.*); 而非imgView.setBackgroundResource(R.drawable.*); 

**附：更详细的scaleType说明：**

android:scaleType是控制图片如何resized/moved来匹对ImageView的size。  
ImageView.ScaleType / android:scaleType值的意义区别：  
CENTER /center  按图片的原来size居中显示，当图片长/宽超过View的长/宽，则截取图片的居中部分显示    

CENTER_CROP / centerCrop  按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽)      

CENTER_INSIDE / centerInside  将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长/宽等于或小于View的长/宽  

FIT_CENTER / fitCenter  把图片按比例扩大/缩小到View的宽度，居中显示  

FIT_END / fitEnd   把图片按比例扩大/缩小到View的宽度，显示在View的下部分位置  

FIT_START / fitStart  把图片按比例扩大/缩小到View的宽度，显示在View的上部分位置  

FIT_XY / fitXY  把图片不按比例扩大/缩小到View的大小显示  

MATRIX / matrix 用矩阵来绘制，动态缩小放大图片来显示。  

** 要注意一点，Drawable文件夹里面的图片命名是不能大写的。



# RecyclerView如何从顶端开始
嵌套RecyclerView进入页面不在顶部会在RecyclerView的顶部问题，是因为RecyclerView获取了焦点，通过设置
recyclerView.setFocusable(false);可以解决。   

---

# 解决RecyclerView+Fragment出现的item重复加载的问题
很简单，比如我是定义了一个方法initSale()（举个例子），那我就在初始化方法之前加上一句 saleModelLsit.clear(),把里面的数据清空，再重新加载就行了

# 解决轮播图自动加快的问题

我也不知道这样做对不对，反正加上一句viewPager.clear()貌似就好了。  

---

#  LinearLayoutManager的不同使用
在活动中，`LinearLayoutManager layoutManager = new LinearLayoutManager(this)`  

在fragment中，`LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());`