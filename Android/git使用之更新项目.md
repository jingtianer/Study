# Git上传项目
为了更好地管理自己的代码，我尝试了git上传自己的安卓项目，在这其中遇到了一点小问题。
步骤
+ 直接在本地仓库里把自己的项目文件夹粘贴上，不过好像不能放在根目录下，所以我放在了Android文件夹下。
+ 然后就是 git add ReBy; git commit -m "xx" ; git push origin master
然后就完美上传了。
+ 然鹅，今天我的代码炸了，想把改过的代码上传到github上，然后，然后就这样了
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190403223327938.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyODk4Mjk5,size_16,color_FFFFFF,t_70)
>Untracked files:  
FragmentAdapter.md  

既然你FragmentAdapter.md没被tracked，那咱就tracked一下  
git add FragmentManager.md  
git push origin master  
然后就又没反应了？原来我没有把改过的文件重新替换旧文件，替换之后，  
git add   
git commit  
git push  
error。
这个错如下图所示
![](https://img-blog.csdnimg.cn/20190403222453417.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyODk4Mjk5,size_16,color_FFFFFF,t_70)
应该是传输大小受到限制
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019040322434123.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyODk4Mjk5,size_16,color_FFFFFF,t_70)
解决方法如上。