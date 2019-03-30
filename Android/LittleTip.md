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
