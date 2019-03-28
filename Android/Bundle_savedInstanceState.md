 
 # onCreate（）方法中的参数Bundle savedInstanceState 的意义用法 #
 Activity中有一个名称叫onCreate的方法。该方法是在Activity创建时被系统调用，是一个Activity生命周期的开始。可是有一点容易被忽视，就是onCreate方法的参数saveInsanceState。一般的程序开发中，很少用到这个参数。  
onCreate方法的完整定义如下:  

```
 public void onCreate(Bundle saveInsanceState){
                super.onCreate(saveInsanceState);  
      }
```


Bundle类型的数据与Map类型的数据相似，都是以key-value的形式存储数据的。  
从字面上看saveInsanceState，是保存实例状态的。实际上，saveInsanceState也就是保存Activity的状态的。那么，saveInsanceState中的状态数据是从何处而来的呢？下面我们介绍Activity的另一个方法saveInsanceState。    
onsaveInsanceState方法是用来保存Activity的状态的。当一个Activity在生命周期结束前，会调用该方法保存状态。
如下所示：  

```
    public void onSaveInsanceState(Bundle saveInsanceState){
       super.onSaveInsanceState(saveInsanceState);
   }
```
在实际应用中，当一个Activity结束前，如果需要保存状态，就在onsaveInsanceState中，将状态数据以key-value的形式放入到saveInsanceState中。这样，当一个Activity被创建时，就能从onCreate的参数saveInsanceState中获得状态数据。
状态这个参数在实现应用中有很大的用途，比如：一个游戏在退出前，保存一下当前游戏运行的状态，当下次开启时能接着上次的继续玩下去。再比如：电子书程序，当一本小说被阅读到第199页后退出了（不管是内存不足还是用户自动关闭程序），当下次打开时，读者可能已忘记了上次已阅读到第几页了，但是，读者想接着上次的读下去。如果采用saveInstallState参数，就很容易解决上述问题。

使用例子：  
```
import android.app.Activity;
 import android.os.Bundle;
 import android.util.Log
public class AndroidTest extends Activity {
      private static final String TAG = "MyNewLog";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // If an instance of this activity had previously stopped, we can get the original text it started with.
        if(null != savedInstanceState)
        {//因为Activity的生命周期原因 ，if 语句放着不一定能执行得到 应该结合实际情况
               int IntTest = savedInstanceState.getInt("IntTest");
               String StrTest = savedInstanceState.getString("StrTest");
         }
        setContentView(R.layout.main);
    }
      @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save away the original text, so we still have it if the activity needs to be killed while paused.
      savedInstanceState.putInt("IntTest", 0);
      savedInstanceState.putString("StrTest", "savedInstanceState test");
      super.onSaveInstanceState(savedInstanceState);
       }
   @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);
      int IntTest = savedInstanceState.getInt("IntTest");
      String StrTest = savedInstanceState.getString("StrTest");
      }
}
```