# Android Map类的使用方法  #
在Android系统中，有着多种存储数据的方式，例如，文件、数据库及程序内参数式存储、网络存储等。对于参数式存储时，使用的就是Map类。Map本身是Interface，Java基于该接口实现三个具体的Map类，分别是HashMap、TreeMap，以及EnumMap，常用的为HashMap，本文也主要介绍HashMap。  

Map定义了访问特定集合的标准方法，这种集合用来存储key-value类型的键值对，比如，对于name:Haiii和 age:22这两组数据来讲，其中name、age称为键（key），与此对应的是键值（value）。在一个Map集合类中,每对键或值其类型都可以是 任意的,比如int、String等都是可以的。  

Map类又是一个类模板，一个Map类对象在初始化时必须指定键的类型，可以是任何Object类，比如，Map<String,Object> mMap= new HashMap<String,Object>()。
<>里面的数据类型用于指定Map集合中“键值对”的类型。 

  *表1-1 Map集合添加和删除键值对的方法*

| 方法 | 描述 | 
| :------ | :------ | 
|clear() |删除该Map集合中的全部元素|
|remove(Objectkey)| 删除键名为key所对应的键值对|
|put(Objectkey,Objectvalue)|添加一个新的键值对|
|putAll(Mapmap)|将该Map集合的元素全部复制到新的Map中|


---
Map类没有提供直接遍历键值对的方法，要遍历所有键值对需要一个中间过程。Map提供了3个方法用于间接遍历键值对，如下：  

+ entrySet() 返回所有键值对类型为Set对象。
+ keySet() 返回所有键值对类型为Set对象。
+ valueSet() 返回所有键值对类型为Collection对象。  
要得到具体的键值对，需要再解析Set和Collection对象，但仅有这两个对象还不能获得键值对，还需要借助于Iterator类。到这里，可能觉得有些复杂，别着急，结果马上就要出来了。  
Set、Collection、Iterator实际上是Map内部进行操作的3个辅助类，要得到具体Map键值对，如代码清单1-1所示。
```
Map<String,Object> mMap = new HashMap<String,Object>();

    Iterator kv = mMap.entrySet().iterator();
    Iterator k = mMap.keySet().iterator();
    Iterator v = mMap.values().iterator();

    Int size = mMap.size();
    for(int i = 0;i<size;i++)
    {
        Map.Entryentry = (Map.Entry)kv.next();
        Object key = entry.getKey();
        Object value = entry.getValue();
    }
```
**用以上代码读取键值对时，Object可以强制转换为int类型。**