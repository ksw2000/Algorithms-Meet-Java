# Three Sum
這次的題目比 LeetCode 上要求的簡單一些，首先，改分程式會丟給 T_sum() 一個整數陣列，這個整數陣列中的數字皆不重複，請找出能有幾種能讓 3 個數加起來合為零的組合

## 爆力解法
三個for迴圈，基本上就是個垃圾

## 基礎解法
第一次遇到這題時，相當頭痛，但後來經過仔細的思考後才發現沒那麼困難，首先我們先介紹耀中哥提供的作法
```java
import java.util.Arrays;
public class ThreeSum{
    public static int T_sum(int[] A){
        Arrays.sort(A);
        int N = A.length;
        int cnt = 0;
        for(i=0; i<N; i++){
            for(j=i+1; j<N; j++){
                if(BinarySearch.rank(-A[i]-A[j], A)>j){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
```
這個作法很明顯是 O(N<sup>2</sup>logN) 主要的缺點在於第2個 for loop 與 binarySearch 其實可以不用這麼吃力把每一組都試一遍

### 缺點
+ binarySearch 範圍是否可以只搜尋 j+1~N-1之間就好
+ 第一個數一定是負數，是否可綁定 i 值範圍
+ 第三個數一定是正數，是否可直接綁定尋找範圍

## 維基快速解法
試想，當今天我把陣列由小到大排好後，我只要固定第一個for迴圈，並從第二個for迴圈漸漸向右(漸漸變大)，那麼為了使三個數和為零，第三個數一定是得漸漸向左靠近

```java
import java.util.Arrays;
public class ThreeSum{
    public static int T_sum(int[] A){
        Arrays.sort(A);
        int i,j,k;
        int cnt=0;
        for(i=0; i<N; i++){
            for(j=i+1; j<N; j++){
                //k由N-1逐漸向j前進
                //盡可能行成 A[i]+a[j]+a[k] == 0
                if(A[i]+A[j]+A[k] > 0){
                    //和太大 k 向左
                    k--;
                }else if(A[i]+A[j]+A[k] < 0){
                    //和太小 j 向右
                    j++;
                }else{
                    //若和為零 (k向左 j向右 夾擠)
                    cnt++;
                    k--;
                    j++;
                }
            }
        }
    }
}
```

以上是維基百科提供的作法

### 缺點
+ 第一個數一定是負數，是否可綁定 i 直範圍
+ 第三個數一定是正數，是否可直接綁定尋找範圍

## 加速
綜合以上缺點，很明顯看出不管是耀中哥提供的「兩for一bin」還是維基提供的「神祕夾擠」法都花費了不必要的for迴圈

我們仔細思考一下，要讓三數和為零 由小到大鐡定得

+ 負 + 負 + 正
+ 負 + 零 + 正
+ 負 + 正 + 正

要是我們一開始就鎖定最外層迴圈的範圍，僅讓他遍歷負數區，在正數與負數幾乎一樣多的情況下，我們就少考慮了一半的迴圈(雖然實際不到一半)

要是我們也鎖定 k 的範圍也可以減少迴圈範圍(實測後發現效果不顯著)

```java
/*
binarySearchClosest() 反回最接近但大於等於該數字的 index
*/
import java.util.Arrays;
public class ThreeSum{
    public static int T_sum(int[] A){
        Arrays.sort(A);

        int real_len = A.length;
        int len_for_start = binarySearchClosest(0, real_len-1, A, 0);
        int start, mid, end;
        int count = 0;
        //start 僅從 最小到 0 之前
        for(start=0; start<len_for_start; start++){
            mid = start+1;
            end = real_len-1;
            while(end > mid){
                sum = A[start] + A[mid] + A[end];
                if(sum > 0){
                    end--;
                }else if(sum < 0){
                    mid++;
                }else{
                    count++;
                    end--;
                }
            }
        }
        return count;
    }

    static int binarySearchClosest(int start, int end, int array[], int target){
        if(target < array[start]) return -2;
        if(target > array[end]) return -1;
        if(target == array[start]) return start;
        int mid;
        while(start+1 < end){
            mid = (start+end) >> 1; //利用位元運算做加速
            if(array[mid] > target){
                end = mid;
            }else if(array[mid] < target){
                start = mid;
            }else{
                return mid;
            }
        }
        return end;
    }
}
```

### 缺點
+ 雖然是比一般人快出許多，但是因為測資不大，所以無法脫穎而出，是否能更節省迴圈開銷？

## 多執行緒加速創最佳成績
+ 第一個for迴圈做的事一模一樣也不會影響變數使用，很明顯可以使用平行處理的精神將其分散給不同處理器進行處理

要拆分我想到兩種作法
+ 連續切成 n 份 (但若陣列小於 n 份會出錯)
+ 利用 mod 分成 n 份

### 第一種(連切 n 等份)
```java
int threadNum = 8;
int log_2_threadNum = 3;    //方便利用位元運算加速

for(int j=0; j<threadNum; j++){
    final int i = j;
    collaborate[i] = new Thread(new Runnable(){
        public void run(){
            int start, mid, end, sum, tmp, count=0;
            for(start=(len_for_start*i) >> log_2_threadNum; start<(len_for_start*(i+1))>> log_2_threadNum; start++){
                // TODO
            }
            //計算完的 count 加入 全域 counter 中 (_count)
            //全域的 counter 必需加入 volatile 必免同時存取發生錯誤

        }
    });
    collaborate[i].start();
}

//將所有多執行緒 join 起來等待所有結束
try{
    for(int i=0; i<threadNum; i++){
        collaborate[i].join();
    }
}catch(InterruptedException e){}
```

>   注意，使用此種方法必需注意陣列不可小於執行緒數量

### 第二種(利用 同餘 切成 n 等份)

``` java
for(int j=0; j<threadNum; j++){
    final int i=j;
    collaborate[i] = new Tread(new Runnable(){
        public void run(){
            for(start=j; start<len_for_start; start+=threadNum){
                // TODO
            }
        }
    });

}
```
>   此種方法可避免第一種陣列過小所發生的錯誤

### 小結
>   以第一種方法切成 n 等份不是明智的作法，因為越是後面，所花費的時間越少，而需要等待其他較前方的執行緒。
>
>   但實際上並不然，經過多次實驗，發現第一種方法平均比第二種方法還要來的快
> #### 快取問題猜測
> heapsort 理論上來看是最快的 sorting 方式，但實際上並不是。為什麼會談到 heapsort 呢？因為我猜這個執行緒切法的效能跟 heapsort 快不起來有關
>
> heapsort 最大的缺點就是他無法有效的利用「快取」，作業系統在存取記憶體時會一次取出一堆相鄰的位置，所以如果要存取陣列時，盡可能連續存取附近的陣列，而不是跳著存取，跳著存取無法利用快取達到提速效果
>
> 而我們的第一種切法是切成 n 等份(每等份間的記憶體位置是相鄰的)，第二種方法則是跳著存取，這會導致第二種方法無法得到快取的支持，反而會比第一種切法來的慢

## 總結
基本上以上這些方法最快的就是最後一種，開執行緒比沒開平均快4~5倍，也因此我在程式碼最後放上嚣張的「線程魔法使」，希望這不會是我演算法課程唯一一支第零名。

我比[瑋哥 @wei-coding](https://github.com/wei-coding)快，我猜是因為我有用 binarySearchClosest 去鎖住第一層 for 迴圈範圍，而他沒有

另外，我也附上我用R語言寫的測資產生器
