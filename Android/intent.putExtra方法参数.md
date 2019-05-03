转载自  
https://blog.csdn.net/qing101hua/article/details/45461377

【开篇骂几句：fuck】
1.扯淡intent.putExtra()怎么使用？
2.胡说intent.putExtra();

【扯淡：其实你在问它怎么用的时候，你要明白，你知道不知道这是个什么东东，有必要问吗？有？我猜你已经知道它的基本概念了，它是用来传参数的对不对，是的，就这么简单。但你仍然在网上百度它怎么用，我不理解你为啥要这么做，哦，我又猜到了，我猜啊，你是不知道他的具体参数是怎么个用吧，对了，问题的核心来了，所有安卓开发中的问题都是方法参数的问题】  

【putExtra("A",B)中，AB为键值对，第一个参数为键名，第二个参数为键对应的值。顺便提一下，如果想取出Intent对象中的这些值，需要在你的另一个Activity中用getXXXXXExtra方法，注意需要使用对应类型的方法，参数为键名】

```
public class MyIntent extends Activity {
        
        /*声明控件对象*/
        private EditText et1, et2;
        private Button   bt;
        
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*取得控件对象*/
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        bt = (Button) findViewById(R.id.bt);
        

        /*为按钮绑定监听器*/
        bt.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                                /*取得输入框中的内容*/
                        String et1Str = et1.getText().toString();
                        String et2Str = et2.getText().toString();
                        //创建Intent对象，参数分别为上下文，要跳转的Activity类
                        Intent intent = new Intent(MyIntent.this, SecondActivity.class);
                        //将要传递的值附加到Intent对象
                        intent.putExtra("et1", et1Str);
                        intent.putExtra("et2", et2Str);
                        //启动该Intent对象，实现跳转
                        startActivity(intent);
                        }
                });
        
        
        
    }
}
```


再建第二个Activity：SecondActivity 
```
public class SecondActivity extends Activity{
        
        //声明TextView对象
        private TextView tv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.second);
                
                //取得TextView对象
                tv = (TextView) findViewById(R.id.tv);
                
                //取得启动该Activity的Intent对象
                Intent intent =getIntent();
                /*取出Intent中附加的数据*/
                String first = intent.getStringExtra("et1");
                String second = intent.getStringExtra("et2");
                
                //计算得到结果
                int result = Integer.parseInt(first) + Integer.parseInt(second);
                
                //设置TextView显示的文本
                tv.setText("计算结果为:"+String.valueOf(result));
                
                
        }
        
}
```