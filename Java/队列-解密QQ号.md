# 队列-解密QQ号
+ 来源--《啊哈算法》  

## 题目要求
给你一串数字，你要按照一定规则进行变换，从而得到结果；  
规则：  
首先将第一个数字删除，紧接着将第二个数字放在这串数的末尾，在将第三个数字删除并将第四个数字放到这串数字的末尾······


## Java实现
我把它分成了两个类，一个主类
```
public class Main {

	public static void main(String[] args) {
		Queue q = new Queue();
		q.setHead(0);	
		q.setTail(0);
		q.setArray();
		while(q.getHead()<q.getTail()) {//判断类内容不为空
			System.out.print(q.getArray(q.getHead()));//输出被删除的数字
			q.setHead( q.getHead() + 1);//出列，队头移动到下一个
			q.setArray(q.getTail(),q.getArray(q.getHead()));//将第二个数字转移到最后一位
			q.setTail(q.getTail()+1);//增大数组容量
			q.setHead(q.getHead()+1);//继续出列
		}
		
	}
}

```
一个Queue类
```
import java.util.*;

public class Queue {
	
	private int head;
	private int tail;
	private int[] a = new int[100];
	
    public void setHead(int head) {
    	this.head = head;
    }
    
    public void setTail(int tail) {
    	this.tail = tail;
    }
    Scanner in = new Scanner(System.in);
    public void setArray(){
    	for(int i = 0; i<9; i++) {    	
    		
    		this.a[i] = in.nextInt();
    		this.tail = this.tail + 1;
    	}
    }
    
    int getHead() {
    	return head;
    }
    
    int getTail() {
    	return tail;
    }
    
    int getArray(int index) {
    	return a[index];
    }
    
    public void setArray(int index, int num) {
    	a[index] = num;
    }
}

```

---
## C++实现的方法
```
#include <iostream> 
using namespace std;
struct queue
{
	int data[100];
	int head;
	int tail;
};
int main()
{
	struct queue q;
	q.head = 0;
	q.tail = 0;
	for(int i = 0; i < 9; i++)
	{
		cin>>q.data[q.tail++];
	}
	while(q.tail>q.head)
	{
		cout<<q.data[q.head++];
		q.data[q.tail++] = q.data[q.head];
		q.head++;
	}
}
```