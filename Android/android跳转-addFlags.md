# android跳转-addFlags #
Activity的两种启动模式：FLAG_ACTIVITY_CLEAR_TOP和FLAG_ACTIVITY_REORDER_TO_FRONT
 
1. 如果已经启动了四个Activity：A，B，C和D。在D Activity里，我们要跳到B Activity，同时希望C finish掉，可以在startActivity(intent)里的intent里添加flags标记，如下所示：
```
Intent intent = new Intent(this, B.class);   
intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
startActivity(intent);    
```
这样启动B Activity，就会把D，C都finished掉，如果你的B Activity的启动模式是默认的（multiple） ，则B Activity会finished掉，再启动一个新的Activity B。
  如果不想重新再创建一个新的B Activity，则在上面的代码里再加上：
  ```
  intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   
  ```
  这样B Activity就不会再创建一个新的了，而是会重用之前的B Activity，同时调用B Activity的onNewIntent()方法。  

  ---
  2. 如果已经启动了四个Activity：A，B，C和D，在D Activity里，想再启动一个Actvity B，但不变成A,B,C,D,B，而是希望是A,C,D,B，则可以像下面写代码：
  ```
  Intent intent = new Intent(this, MainActivity.class);  
intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);   
startActivity(intent);   
  ```

  