# parseInt()  #
parseInt()方法首先查看位置0处的 字符，判断它是否是个有效数字；如果不是，该方法将返回NaN，不再继续执行其他操作。但如果该字符是有效数字，该方法将查看位置1处的字符，进行同样的 测试。这一过程将持续到发现非有效数字的字符为止，此时parseInt()将把该字符之前的字符串转换成数字。  
例如  
如果要把字符串 "1234blue "转换成整数，那么parseInt()将返回1234，因为当它检测到字符b时，就会停止检测过程。  
parseInt()方法还有基模式，可以把二进制、八进制、十六进制或其他任何进制的字符串转换成整数。  
基是由parseInt()方法的第二个参数指定的，所以要解析十六进制的值，当然，对二进制、八进制，甚至十进制（默认模式），都可以这样调用parseInt()方法。  
如果十进制数包含前导0，那么最好采用基数10，这样才不会意外地得到八进制的值。  
该方法的所有变形：  
>static int parseInt(String s)  

>static int parseInt(String s, int radix)  

参数：   
下面是参数的细节的：  
>String s : 这是十进制的字符串表示形式。
int radix : 这将用于将字符串转换为整数。
```
public class Test{   
   public static void main(String args[]){  
      int x =Integer.parseInt("9");  
      double c = Double.parseDouble("5");  
      int b = Integer.parseInt("444",16);  
//by www.yiibai.com/java  
      System.out.println(x);  
      System.out.println(c);  
      System.out.println(b);  
   }  
}  
```
