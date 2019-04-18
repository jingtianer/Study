我们细化到一个App示例中去，假设一个App具有如下功能：  
1. 基础支持功能

2. 网络请求
3. 图片处理
4. 数据库
……

业务功能

1. 新闻相关功能模块  
2. 电影相关功能模块  
3. 音乐相关功能模块  
……

对于以上示例App，我们按照功能模块来分包：  

+ 将网络功能相关代码归到一个包，如com.example.network，至于具体用OkHttp还是Volley或者自己封装HttpURLConnection都无所谓。    
+ 将图片加载、缩放、缓存等相关功能代码归到一个包，如com.example.image，同样跟使用哪种图片框架无关。   
+ 将Sqlite数据库相关操作的代码归到一个包，如com.example.db。   
+ 将新闻功能模块的相关业务代码归到一个包，如com.example.news，同时新闻功能相关的所有Activity和Fragment都放到这个包中。  
+ 将电影功能模块的相关业务代码归到一个包，如com.example.movie，同时电影功能相关的所有Activity和Fragment都放到这个包中。  
+ 将音乐功能模块的相关业务代码归到一个包，如com.example.music，同时音乐功能相关的所有Activity和Fragment都放到这个包中。   
+ 对于adapter，如是封装的通用的adapter，则可归到com.example.adapter中，如仅仅 某个Activity自己用的adapter，也可放到业务模块的包中，这里推荐所有adapter放到一+ 个独立的包中，因为我们查找代码时，往往从功能模块角度入口，根据功能分包原则，可以+ 快速找到对应包中的Activity，找到了Activity，其用到的adapter、entity便也可以迅速找到，因此不必也放到功能模块的包中。  
+ 对于自定义控件，可以统一归到一个包，如com.example.widget。  
+ 对于某些基类，如BaseActivity和BaseFragment等可以放到一个叫com.example.base的包中。  
+ 对于数据对象实体，可统一放到com.example.entity中，不要放到具体功能模块的包中，原因第7点已提及，另外实体还可能重用、继承等。  
+ 对于业务无关的公用方法和工具类，可以放到com.example.util中。  
……

基于上述分包策略，在Android Studio中一个App的层次结构示意图如下：

```
java
           |--- com
                |---example
                    |--- base
                    |    |--- BaseActivity.java
                    |    |--- BaseFragment.java
                    |    |--- xxx.java
                    |
                    |--- network
                    |    |--- HttpClient.java
                    |    |--- xxx.java
                    |
                    |--- image
                    |    |--- ImageManager.java
                    |    |--- xxx.java
                    |
                    |--- db
                    |    |--- DbManager.java
                    |    |--- xxx.java
                    |
                    |--- news
                    |    |--- NewsActivity.java
                    |    |--- NewsFragment.java
                    |    |--- xxx.java
                    |
                    |--- movie
                    |    |--- MovieActivity.java
                    |    |--- MovieFragment.java
                    |    |--- xxx.java
                    |
                    |--- music
                    |    |--- MusicActivity.java
                    |    |--- MusicFragment.java
                    |    |--- xxx.java
                    |
                    |--- entity
                    |    |--- Movie.java
                    |    |--- News.java
                    |    |--- xxx.java
                    |
                    |--- adapter
                    |    |--- AbsAdapter.java
                    |    |--- MovieAdapter.java
                    |
                    |--- widget
                    |    |--- CircleImageView.java
                    |    |--- xxx.java
                    |    
                    |--- util
                         |--- ToastUtil.java
                         |--- xxx.java
```