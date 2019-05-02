转载自  
https://www.cnblogs.com/Fndroid/p/5308713.html

https://blog.csdn.net/xiaanming/article/details/8703708



# Java回调机制

## 什么是回调？
先看定义：
>Java允许我们调用接口的方法，但是有一个前提，就是编译的时候接口的对象必须是一个具体的类，并且这个类实现了接口。当我们调用接口的方法的时候，接口的具体子类中的具体方法会被调用，这就是回调。


当然十句话不如一句代码，那就上代码瞧一下：

```
public interface A {
    void printClassName();
}
```


```
public class B implements A {
    public void printClassName() {
        System.out.println("This is class B!");
    }
}
```

```
public static void main(String[] args) {
        A a = new B();
        a.printClassName();
}
```

输出的结果就是：
`This is class B`

明显可以发现，我们在主函数中定义了接口A，也调用了接口A的方法，但是要注意，我们实例化的时候其实new的是一个实现了A接口的具体类，我们调用A中的printClassName方法的时候Java就会去回调B中的该方法。

---
## 怎么调用的？

 + Class A实现接口CallBack callback——背景1  
 + class A中包含一个class B的引用b ——背景2   
 + class B有一个参数为callback的方法f(CallBack callback) ——背景3  
 + A的对象a调用B的方法 f(CallBack callback) ——A类调用B类的某个方法 C  
 + 然后b就可以在f(CallBack callback)方法中调用A的方法 ——B类调用A类的某个方法D


那么接下来就用另一个更生动的例子来看一下(这是个异步回调机制）：
>有一天小王遇到一个很难的问题，问题是“1 + 1 = ?”，就打电话问小李，小李一下子也不知道，就跟小王说，等我办完手上的事情，就去想想答案，小王也不会傻傻的拿着电话去等小李的答案吧，于是小王就对小李说，我还要去逛街，你知道了答案就打我电话告诉我，于是挂了电话，自己办自己的事情，过了一个小时，小李打了小王的电话，告诉他答案是2

```

/**

 * 这是一个回调接口

 * @author xiaanming

 *

 */

public interface CallBack {

	/**

	 * 这个是小李知道答案时要调用的函数告诉小王，也就是回调函数

	 * @param result 是答案

	 */

	public void solve(String result);

}

```


```

/**

 * 这个是小王

 * @author xiaanming

 * 实现了一个回调接口CallBack，相当于----->背景一

 */

public class Wang implements CallBack {

	/**

	 * 小李对象的引用

	 * 相当于----->背景二

	 */

	private Li li; 

 

	/**

	 * 小王的构造方法，持有小李的引用

	 * @param li

	 */

	public Wang(Li li){

		this.li = li;

	}

	

	/**

	 * 小王通过这个方法去问小李的问题

	 * @param question  就是小王要问的问题,1 + 1 = ?

	 */

	public void askQuestion(final String question){

		//这里用一个线程就是异步，

		new Thread(new Runnable() {

			@Override

			public void run() {

				/**

				 * 小王调用小李中的方法，在这里注册回调接口

				 * 这就相当于A类调用B的方法C

				 */

				li.executeMessage(Wang.this, question); 

			}

		}).start();

		

		//小网问完问题挂掉电话就去干其他的事情了，诳街去了

		play();

	}

 

	public void play(){

		System.out.println("我要逛街去了");

	}

 

	/**

	 * 小李知道答案后调用此方法告诉小王，就是所谓的小王的回调方法

	 */

	@Override

	public void solve(String result) {

		System.out.println("小李告诉小王的答案是--->" + result);

	}

	

}

```

```

/**

 * 这个就是小李啦

 * @author xiaanming

 *

 */

public class Li {

	/**

	 * 相当于B类有参数为CallBack callBack的f()---->背景三

	 * @param callBack  

	 * @param question  小王问的问题

	 */

	public void executeMessage(CallBack callBack, String question){

		System.out.println("小王问的问题--->" + question);

		

		//模拟小李办自己的事情需要很长时间

		for(int i=0; i<10000;i++){

			

		}

		

		/**

		 * 小李办完自己的事情之后想到了答案是2

		 */

		String result = "答案是2";

		

		/**

		 * 于是就打电话告诉小王，调用小王中的方法

		 * 这就相当于B类反过来调用A的方法D

		 */

		callBack.solve(result); 

 

		

		

	}

	

}

```


```

/**

 * 测试类

 * @author xiaanming

 *

 */

public class Test {

	public static void main(String[]args){

		/**

		 * new 一个小李

		 */

		Li li = new Li();

 

		/**

		 * new 一个小王

		 */

		Wang wang = new Wang(li);

		

		/**

		 * 小王问小李问题

		 */

		wang.askQuestion("1 + 1 = ?");

	}

}

```

接下来看一个同步回调机制，这也是在安卓里比较常见的onclik()方法
```

//这个是View的一个回调接口

/**

 * Interface definition for a callback to be invoked when a view is clicked.

 */

public interface OnClickListener {

    /**

     * Called when a view has been clicked.

     *

     * @param v The view that was clicked.

     */

    void onClick(View v);

}

```


```

package com.example.demoactivity;

 

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.Toast;

 

/**

 * 这个就相当于Class A

 * @author xiaanming

 * 实现了 OnClickListener接口---->背景一

 */

public class MainActivity extends Activity implements OnClickListener{

	/**

	 * Class A 包含Class B的引用----->背景二

	 */

	private Button button;

 

	@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		button = (Button)findViewById(R.id.button1);

		

		/**

		 * Class A 调用View的方法,而Button extends View----->A类调用B类的某个方法 C

		 */

		button.setOnClickListener(this);

	}

 

	/**

	 * 用户点击Button时调用的回调函数，你可以做你要做的事

	 * 这里我做的是用Toast提示OnClick

	 */

	@Override

	public void onClick(View v) {

		Toast.makeText(getApplication(), "OnClick", Toast.LENGTH_LONG).show();

	}

 

}

```

下面是View类的setOnClickListener方法，就相当于B类咯，只把关键代码贴出来

```

/**

 * 这个View就相当于B类

 * @author xiaanming

 *

 */

public class View implements Drawable.Callback, KeyEvent.Callback, AccessibilityEventSource {

	/**

     * Listener used to dispatch click events.

     * This field should be made private, so it is hidden from the SDK.

     * {@hide}

     */

    protected OnClickListener mOnClickListener;

    

    /**

     * setOnClickListener()的参数是OnClickListener接口------>背景三

     * Register a callback to be invoked when this view is clicked. If this view is not

     * clickable, it becomes clickable.

     *

     * @param l The callback that will run

     *

     * @see #setClickable(boolean)

     */

    

    public void setOnClickListener(OnClickListener l) {

        if (!isClickable()) {

            setClickable(true);

        }

        mOnClickListener = l;

    }

    

    

    /**

     * Call this view's OnClickListener, if it is defined.

     *

     * @return True there was an assigned OnClickListener that was called, false

     *         otherwise is returned.

     */

    public boolean performClick() {

        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);

 

        if (mOnClickListener != null) {

            playSoundEffect(SoundEffectConstants.CLICK);

            

            //这个不就是相当于B类调用A类的某个方法D，这个D就是所谓的回调方法咯

            mOnClickListener.onClick(this);

            return true;

        }

 

        return false;

    }

}

```