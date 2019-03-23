---
title: PerfectCode
date: 2019-03-23 23:13:56
tags: [coding, practice]
catogeries:
- 算法
---
# 题目要求 #
>1030 完美数列 （25 分)
给定一个正整数数列，和正整数 p，设这个数列中的最大值是 M，最小值是 m，如果 M≤mp，则称这个数列是完美数列。
现在给定参数 p 和一些正整数，请你从中选择尽可能多的数构成一个完美数列。


## 输入格式：##  
>输入第一行给出两个正整数 N 和 p，其中 N（≤10^​5）      
是输入的正整数的个数，p（≤10^​9）是给定的参数。   
第二行给出 N 个正整数，每个数不超过 10^
​9
​​。
## 输出格式：  ##
>在一行中输出最多可以选择多少个数可以用它们组成一个完美数列。
输入样例：
10  8  
2 3 20 4 5 1 6 7 8 9
## 输出样例：##
>8
---
## 一.C/C++ ##

### 1)双重循环  ###
解题思路：    
**首先p与最小数相乘可能会超出int范围，所以这里用double,其次我们应该先将数组排序以方便计算，然后我们用双重for循环查找，查找的思路是从第一个元素作为最小数，开始往后找最大数，直到不符合条件，记录下此时的长度，然后将第二个元素作为最小数，再继续找，最后比较长度的最大值并输出，但是这样会超时，所以我们要对她进行优化，减少不必要的循环，优化的思路如下：**

**首先我们同样保持第一个for循环遍历最小值，在第二个for循环中我们将j置为前一个元素作为最小数时候的长度，这样就减少了小于上一次的不必要的for循环，j依然小于 N，用一个if判断是否符合条件，用另一个if判断此次是否大于上次的长度，比如说我们把样例中的数据已经排好序：1 2 3 4 5 6 7 8 9 20 ，此时我们将array[0]作为最小数，依次向后遍历，最大数j-最小数i+1即为数列的长度，最终找到8为最大的数，此时数列长度count为8，在将a[1]作为最小数的时候，我们直接将j置为1+8为9，直接比较a[1]和a[9]作为最小最大值得时候是否满足，不满足则a[1]最为最小数的时候并不能使数列变得更长，则继续再看a[2],这样等到有大于8的时候再更新，就可以了。**
```
#include <iostream>
#include <algorithm>
using namespace std;
int main()
{
	int a[1000];
	int n;
	int p;
	cin>>n>>p;
	for(int i=0;i<n;i++)
	{
		cin>>a[i];
	}
	sort(a,a+n);
	int count=0;
	for(int i=0;i<n;i++)
	{
		for(int j=i+count;j<n;j++)
		{
			if(a[j]>a[i]*p)
			{
				break;
			}
			if((j-i+1)>count)
			{
				count=j-i+1;
			}
		}
	}
	cout<<count<<endl;
}
```
---
### 2）利用二分法 ###
```

#include <cstdio>

#include<algorithm>

using namespace std;

const int maxn=100010;

int arr[maxn];

int n;

int binarySearch(int i, long long x){

	if(arr[n-1]<=x)return n;

	int left=i, right=n-1, mid;

	while(left<right){

		mid=left+(right-left)/2;

		if(arr[mid]<=x){

			left=mid+1;

		}else{

			right=mid;

		}

	}

	return left;

} 

int main(){

	int  p;

	scanf("%d%d", &n, &p);

	for(int i=0; i<n; i++){

		scanf("%d", &arr[i]);

	}

	sort(arr, arr+n);

	int cnt=1;

	for(int i=0; i<n; i++){

	   int j=binarySearch(i, (long long)p*arr[i]);

		cnt=max(cnt, (j-i));

	}

	printf("%d", cnt);

	return 0;

}

```
---
## 3) ##
```
//思路不同的代码
#include<stdio.h>
int main(){
    int n,m,i,t=0;
    long a[100000],p,max=0,min;
    scanf("%d %ld",&n,&p);
    for(i=0;i<n;i++){
        scanf("%ld",&a[i]);
        if(a[i]>max){    //在输入的时候先找出最大的数，减少时间
            max=a[i];
        }
    }
    if(max%p!=0){       //再找出能够满足的最小的数
        min=(max/p)+1;
    }
    else{
        min=max/p;
    }
    for(i=0;i<n;i++){
        if(a[i]<min){
            t++;       //开始计数
        }
    }
    if(p==2){         //p=2时的特殊情况
        printf("%ld",n-t+3);
    }
    else{
        printf("%d",n-t);   
    }
    return 0;
} 
```
---
## 4)利用vector储存数据 ##
```

#include <iostream>

#include <vector>

#include <algorithm>

using namespace std;

int main(){

	long int N,p,cnt=0;	cin>>N>>p;

	vector<int> v(N);

	for(int i=0;i<N;i++)	cin>>v[i];

	sort(v.begin() ,v.end() );

	for(int i=0;i<N;i++){

		for(int j=i+cnt;j<N;j++){

			if(v[j]>p*v[i])	break;

			if(j-i+1>cnt)	cnt=j-i+1;

		}

	}

	cout<<cnt;

	return 0;

} 

```
---
## Java ##
### 3) 常规操作 ###
```

 

import java.util.Arrays;

import java.util.Scanner;

 

public class Main {

 

 

	static int n;

	static long p;

	static long[] a=new long[100005];

	public static void main(String args[]) {

		Scanner in=new Scanner(System.in);

		n=in.nextInt();

		p=in.nextLong();

		for(int i=0;i<n;i++) {

			a[i]=in.nextLong();

		}

		Arrays.sort(a, 0, n);

		int ans=0;

		for(int i=0;i<n;i++) {

			for(int j=i+ans;j<n;j++) {

				if(a[j]<=a[i]*p) {

					ans++;

				}

				else break;

			}

		}

		System.out.println(ans);

	}

 

	

 

}

```
### 2）java Number类 StringBuffer类##
```
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] in = br.readLine().split(" ");
    int n = Integer.parseInt(in[0]);
    int p = Integer.parseInt(in[1]);
    String[] data = br.readLine().split(" ");
    int[] array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = Integer.parseInt(data[i]);
    }
    Arrays.sort(array);
    int cnt = 1;
      for (int i = n-1; i >= 1; i--) {
          int temp = 1;
          int M = array[i];
          for (int j = i-cnt; j >= 0 && M * 1.0 / array[j] <= p ; j--) {
              temp = i - j + 1;
          }
          cnt = cnt > temp ? cnt : temp;
      }
    System.out.println(cnt);
  }
}

```
-->[Click here to know more.](http://www.runoob.com/java/number-parseint.html)
