# Retrofit 2.0 使用教程 #
搬运自  
[这是一份很详细的 Retrofit 2.0 使用教程（含实例讲解)](https://blog.csdn.net/carson_ho/article/details/73732076)  
[网络请求框架 —— Retrofit](https://www.jianshu.com/p/c99dbf612740)
    
 ---  
## 前言 ##
+ 在Andrroid开发中，网络请求十分常用  
+ 而在Android网络请求库中，Retrofit是当下最热的一个网络请库  
+ 下图是它的依赖库
![依赖库](http://upload-images.jianshu.io/upload_images/944365-9be33c763c56a914.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
---

## 目录 ##

![图片](http://upload-images.jianshu.io/upload_images/944365-2bd80b234ae9d155.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)   

---
## 1.简介 ##
![简介](http://upload-images.jianshu.io/upload_images/944365-a3109ad0446b0540.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
注意：
+ Retrofit 是一个 RESTful 的 HTTP 网络请求框架的封装。
+ 网络请求的工作本质上是 OkHttp 完成，而 Retrofit 仅负责 网络请求接口的封装  
本质流程
![progess](http://upload-images.jianshu.io/upload_images/944365-b5194f1d16673589.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
+ App应用程序通过 Retrofit 请求网络，实际上是使用 Retrofit 接口层封装请求参数、Header、Url 等信息，之后由 OkHttp 完成后续的请求操作
+ 在服务端返回数据之后，OkHttp 将原始的结果交给 Retrofit，Retrofit根据用户的需求对结果进行解析

---
## 2.使用 ##
使用 Retrofit 的步骤共有7个：

步骤1：添加 依赖   
步骤2：创建 接收服务器返回数据的类   
步骤3：创建 用于描述网络请求的接口   
步骤4：创建 Retrofit 实例   
步骤5：创建 网络请求接口实例并配置网络请求参数   
步骤6：发送 网络请求（异步 / 同步） 
### 步骤一 前期准备 ###
#### 1）添加依赖 #### 
+ okHttp依赖
+ Gson依赖
```
implementation 'com.squareup.retrofit2:retrofit:2.0.2'
    // Retrofit库
    implementation 'com.squareup.okhttp3:okhttp:3.10.0'
    // Okhttp库
    implementation 'com.squareup.retrofit2:converter-gson:2.0.2'
    //Gson库
```
#### 2）添加网络权限 ####
*AndroidManifest.xml*  
`<uses-permission android:name="android.permission.INTERNET"/>`  

---
### 步骤二：创建 接收服务器返回数据的类 ###
接下来看一个实例
>获取一下都有哪些大神对 Retrofit 做出了贡献  

1)创建 Java 类对象Contrbutor
```
public class Contributor {

    /**
     * login : JakeWharton
     * avatar_url : https://avatars0.githubusercontent.com/u/66577?v=4
     * html_url : https://github.com/JakeWharton
     */

    private String login;  // 人名
    private String avatar_url;  // 头像的 Url 地址
    private String html_url;  // 个人 GitHub 主页地址
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
```
>这里使用了GsonFormat进行自动构建类  
详情参考:  
[还在手动写Bean？赶紧来用GsonFormat吧](https://blog.csdn.net/u013700502/article/details/78765574)   
---
### 步骤三  创建 API 接口（GitHub 提供的是 GET 请求） ###
Retrofit将 Http请求 抽象成 Java接口：采用 注解 描述网络请求参数 和配置网络请求参数  
>用 动态代理 动态 将该接口的注解“翻译”成一个 Http 请求，最后再执行 Http 请求   
  注：接口中的每个方法的参数都需要使用注解标注，否则会报错

*GetRequest_Interface.interface*
```
public interface MainService {

    @GET("/repos/square/retrofit/contributors")
    Call<List<Contributor>> getCall();
}
```
--->Retrofit 网络请求接口的注解类型
![tu](http://upload-images.jianshu.io/upload_images/944365-ee747d1e331ed5a4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![kdj](http://upload-images.jianshu.io/upload_images/944365-f9f8994497df7fd3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### 步骤四，五，六：创建 Retrofit 实例(异步和同步我不太清楚) ###
```
private void initData() {
    // 创建一个非常简单的REST适配器，它指向 GitHub 的 API
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // 创建一个我们的 GitHub API 接口的实例。
    MainService service = retrofit.create(MainService.class);

    // 创建一个调用实例来查找都有哪些大神对 Retrofit 做出了贡献。
    Call<List<Contributor>> call = service.getCall();
   

    //发送网络请求(异步)
    call.enqueue(new Callback<List<Contributor>>() {
        @Override
        public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
            if (response.isSuccessful()) {
                
                list = response.body();
              

                if (list != null && list.size() > 0) {
                    mAdapter.setData(list);
                }
            }
        }

        @Override
        public void onFailure(Call<List<Contributor>> call, Throwable t) {

        }
    });
}
```
差不多就是这样了。