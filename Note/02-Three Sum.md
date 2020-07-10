# Three Sum

## Binary Search

```java
public class BinarySearch{
    public static int rank(int key, int[] a){
        int lo = 0; hi = a.length-1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if   (key < a[mid]) hi = mid - 1;
            else if(key > a[mid]) lo = mid + 1;
            else return mid;
        }
    }
}
```

## Two Sum ── Brute Force

```java
import java.util.Arrays;

public class TwoSumFast{
    public static int count(int[] a){
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for(int i=0; i<N; i++){
            if(BinarySearch.rank(-a[i], a) > i){    //避免重複抓到同一組
                cnt++;            
            }
            return cnt;
        }
    }
}
```

## Two Sum ── By Binary Search

```java
import java.util.Arrays;

public class TwoSumFast{
    public static int count(int[] A){
        Arrays.sort(A);
        int N = A.length;
        int cnt=0;
        for(int i=0; i<N; i++){
            if(BinarySearch.rank(-A[i], A)>i){
                cnt++;
            }
        }        
        return cnt;
    }
}
```

## Three Sum ── Brute Force

```java
public class ThreeSum{
    public static int count(int[] a){
        int N = a.length;
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                for(int k=j+1; j<N; j++){
                    if(A[i]+A[j]+A[k]==0)
                        cnt++;                
                }
            }
        }
        return cnt;
    }
}
```

## Three Sum ── By Binary Search

```java
import java.util.Arrays;

public class ThreeSumFast{
    public static int count(int[] A){
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

## Theory of Algorithm

Lower bound: 至少要做什麼(比如linear search一定要全部掃過一次)

Upper bound:  最差情況

Optimal case: lower bound = upper bound

> Three Sum
> 
> **Upper bound**: A specific algorithm.
> 
> Ex. Improved algorithm for 3-Sum
> 
> Running time of the optimal algorithm for 3-Sum is $O(N^2logN)$
> 
> **Lower bound**: Proof that no algorithm can do better.
> 
> Ex. Have to examine all N entries to solve 3-Sum
> 
> Running time of the optimal algorithm for solving 3-Sum is $\Omega(N)$

因此要證明自己的演算法是最佳時，除了把 Upper bound 往下找，也可以把 Lower bound 往上找，找到 Upper bound = Lower bound 時，就是最佳。

## Open Problem

+ Optimal algorithm for 3-Sum？

+ Subquadratic algorithm for 3-Sum?

+ Quadratic lower bound for 3-Sum?

## Comparing programs

### Typical memory usage for primitive types and arrays

| type    | bytes |
|:-------:|:-----:|
| boolean | 1     |
| byte    | 1     |
| char    | 2     |
| int     | 4     |
| float   | 4     |
| long    | 8     |
| double  | 8     |

Array overhead. 24 bytes

| type     | bytes   |
|:--------:|:-------:|
| char[]   | 2N + 24 |
| int[]    | 4N + 24 |
| double[] | 8N + 24 |

| type       | bytes  |
|:----------:|:------:|
| char[][]   | \~2M N |
| int[][]    | \~M N  |
| double[][] | \~8M N |

### Typical memory usage for objects in Java

Object overhead. 16 Bytes.

Reference. 8 Bytes.

Padding. Each object uses a multiple of 8 bytes.

#### Ex. 1

```java
public class Date{          //16 bytes
    private int day;        //4 bytes
    private int month;      //4 bytes
    private int year;       //4 bytes
                            //padding 4 bytes
}

// 32 bytes
```

#### Ex. 2

length N 的 String 長度約 2N 

```java
public class String{
    private char[] value;
    private int offset;
    private int count;
    private int hash;
    ...    
}
```

#### Typical memory usage summary

+ Primitive type: 4 bytes for int, 8 bytes for double, ...

+ Object reference: 8 bytes

+ Array: 24 bytes + memory for each array entry.

+ Object: 16 bytes + memory for each instance variable.

+ Padding: round up to multiple of 8 bytes.

Shallow memory usage: Don't count referenced objected.

Deep memory usage: If array entry or instance variable is a reference, count memory (recursively) for referenced object.

---

##### How mush memory does a *ResizingArrayImplementation* use to store N strings in the best case? Worst case?

**Proposition.** Uses between \~8N and \~32N bytes to represent a stack with items.

\~8N when full.

\~32N when one-quarter full

##### How mush memory does a *ResizingArrayImplementation* use to store N strings in the best case? Worst case?

**Proposition.** Every operation takes constant time in the worst case. A stack with N items. uses \~40N bytes.
