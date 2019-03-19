---
title: Adapter
date: 2019-03-17 11:14:00
tags: [Java] 
categories :
- Java
- Adapter
---
# Adapter #
在安卓学习中经常碰到适配器，之前都是按照书上的代码去写，今天看了一下Java教材，发现了它的用法。
>适配器模式：将一个类的接口转换成客户希望的另一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作   



适配器属于结构模式  
结构目标包含：  
+ 目标  
+ 被适配者  
+ 适配者 

## 一.类适配器 ##   
原理：通过继承实现适配      
当我们要访问的接口A中没有我们想要的方法 ，却在另一个接口B中发现了合适的方法，我们又不能改变访问接口A，在这种情况下，我们可以定义一个适配器p来进行中转，这个适配器p要实现我们访问的接口A，这样我们就能继续访问当前接口A中的方法（虽然它目前不是我们的菜），然后再继承接口B的实现类BB，这样我们可以在适配器P中访问接口B的方法了，这时我们在适配器P中的接口A方法中直接引用BB中的合适方法，这样就完成了一个简单的类适配器。  
>详见下方实例：我们以ps2与usb的转接为例  

ps2接口：Ps2
```
public interface Ps2 {
     void isPs2();
 }
```
USB接口：Usb  
```
 public interface Usb {
    void isUsb();
}
```  
USB接口实现类：Usber  
```
public class Usber implements Usb {

    @Override
    public void isUsb() {
        System.out.println("USB口");
    }

}
```
适配器：Adapter  
```
public class Adapter extends Usber implements Ps2 {

    @Override
    public void isPs2() {
        isUsb();
    }

}
```  
测试方法：Clienter  
```
public class Clienter {

    public static void main(String[] args) {
        Ps2 p = new Adapter();
        p.isPs2();
    }

}
```
显示结果：  
```
USB口
```  
## 二.对象适配器模式 ##
　　原理：通过组合来实现适配器功能。  
　　当我们要访问的接口A中没有我们想要的方法 ，却在另一个接口B中发现了合适的方法，我们又不能改变访问接口A，在这种情况下，我们可以定义一个适配器p来进行中转，这个适配器p要实现我们访问的接口A，这样我们就能继续访问当前接口A中的方法（虽然它目前不是我们的菜），然后在适配器P中定义私有变量C（对象）（B接口指向变量名），再定义一个带参数的构造器用来为对象C赋值，再在A接口的方法实现中使用对象C调用其来源于B接口的方法。
>下方实例用直流电和交流电之间的转换来演示    

Target：DirectCurrent接口  
```
//目标
public interface DirectCurrent {
	public String giveDirectCurrent();

}
```  
Adaptee: 一个已经存在的接口或者抽象类 在本例子中就是      AlternateCurrent接口。  
```
//被适配者(Adaptee)，一个已经存在的接口或者抽象类
public interface AlternateCurrent {
	public String giveAlternateCrruent();

}
```
Adapter: 对被适配者接口（抽象类）与目标接口进行适配。本例中为ElectricAdapter  
```
public class ElectricAdapter implements DirectCurrent{

	AlternateCurrent out;

	ElectricAdapter(AlternateCurrent out) {
		this.out=out;
	}

	@Override
	public String giveDirectCurrent() {
		String m = out.giveAlternateCrruent();
		StringBuffer str = new StringBuffer(m);
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='0')
			{
				str.setCharAt(i, '1');
			}
		}
		m=new String(str);
		return m;
	}
	
}

```
Application 
```
public class Application {
	public static void main(String[] args) {
		AlternateCurrent aElectric = new PowerCompany();
		Wash wash = new Wash();	
		wash.turnOn(aElectric);
		DirectCurrent dElectric = new ElectricAdapter(aElectric);
		Recorder recorder = new Recorder();
		recorder.turnOn(dElectric);
		
	}

}
class PowerCompany implements AlternateCurrent{

	
	public String giveAlternateCrruent() {
	
		return "101010101010101010";
	}	
}
class Wash{
	String name;
	Wash(){
		name="洗衣机";
	}
	Wash(String s){
		name=s;
	}
	public void turnOn(AlternateCurrent a) {
		String s= a.giveAlternateCrruent();
		System.out.println(name+"使用交流电：\n"+s);
		System.out.println("开始洗衣服");
	}
}
class Recorder{
	String name;
	Recorder(){
		name="录音机";
	}
	Recorder(String s){
		name=s;
	}
	public void turnOn(DirectCurrent a) {
		String s = a.giveDirectCurrent();
		System.out.println(name+"使用直流电：\n"+s);
		System.out.println("开始录音");
	}
}
```


结果：  
```
洗衣机使用交流电：
101010101010101010
开始洗衣服
录音机使用直流电：
111111111111111111
开始录音
```
## 三.接口适配器模式 ##
　　原理：通过抽象类来实现适配，这种适配稍别于上面所述的适配。  
　　当存在这样一个接口，其中定义了N多的方法，而我们现在却只想使用其中的一个到几个方法，如果我们直接实现接口，那么我们要对所有的方法进行实现，哪怕我们仅仅是对不需要的方法进行置空（只写一对大括号，不做具体方法实现）也会导致这个类变得臃肿，调用也不方便，这时我们可以使用一个抽象类作为中间件，即适配器，用这个抽象类实现接口，而在抽象类中所有的方法都进行置空，那么我们在创建抽象类的继承类，而且重写我们需要使用的那几个方法即可。  
目标接口：A    
```
public interface A {
    void a();
    void b();
    void c();
    void d();
    void e();
    void f();
}
```
适配器：Adapter
```
public abstract class Adapter implements A {
    public void a(){}
    public void b(){}
    public void c(){}
    public void d(){}
    public void e(){}
    public void f(){}
}
```
 
实现类：Ashili    
```
public class Ashili extends Adapter {
    public void a(){
        System.out.println("实现A方法被调用");
    }
    public void d(){
        System.out.println("实现d方法被调用");
    }
}
```

测试类：Clienter  
```
public class Clienter {

    public static void main(String[] args) {
        A a = new Ashili();
        a.a();
        a.d();
    }

}
```

