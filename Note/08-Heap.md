# Heap

## Top- M problem

> Challenge. Find the largest M items in a stream of N items.
> 
> + sorting?
> 
> + main a pool that record largest seen so far?

## Swim

> 該元素從下方開始向上游到他應有的位置

```java
private void swim(int k){
    while(k >= 1 && less(k/2, k){
        swap(k, k/2);
    }
}
```

## Sink

> 讓元素從上方開始向下沉到他應有的位置

```java
private void sink(int k){
    while(2*k <= N){
        // 如果 j+1 比較大 向右沉, 反之向左沉
        if(j<N && less(j, j+1)) j++;
        if(!less(k, j)) break;
        swap(k, j);
        k = j;
    }
}
```

## Insertion in a heap

> Add node at end, then swim it up. 從最下方加入，並使該元素往上 swim

```java
public void insert(Key x){
    pq[++N] = x;
    swim(N);
}
```

## Delete the maximum in a heap

> Exchange root with node at end, then sink it down. 先拿最後一個元素與最上面的元素做交換，接著由上往下沉

```java
public Key delMax(){
    Key max = pq[1];
    swap(1, N--);
    sink(1);
    return max;
}
```

## Priority queue API

```java
public class PQ<Key extends Comparable<Key>>{
    PQ();               //Create an empty priority queue
    PQ(Key[] a)         //Create a priority queue with given keys
    void insert(Key v)  //Insert a key into the priority queue
    Key delMin()        //Return and remove the largest key
    boolean isEmpty()   //Is the priority queue empty?
    Key max()           //Return the largest key
    int size()          //Number of entries in the priority queue
}
```

## Sink-based Heap sort

```java
public class Heap{
    public static void sort(Comparable[] a){
        int len = a.length;
        //對 inner Node a.k.a 樹去掉最下面那排(葉) 做 sink
        //因為葉的部份已經沉到底了根本不用 sink

        //複雜度 : N
        for(int k = len/2; k>=1; k--){
            sink(a, k, len);
        }

        //複雜度 : NlgN
        while(len > 1){
            //將最大的接去後面
            swap(a, 1, len);
            //將新來的重新定位
            //(只做到 len-1 因為 len已經做好了)
            sink(a, 1, --len);
        }
    }
    private static void sink(Comparable[] a, int k, int len){
    }
    private static boolean less(Comparable[] a, int i, int j){
    }
}
```

## Mathematical analysis

**Proposition.** Sink-based Heap construction uses ≤ 2N compares and ≤ N exchanges

**Proposition.** Heapsort uses ≤ 2NlgN compares and exchanges.

**Significance.** In-place sorting algorithm with NlogN worst-case.

> Merge sort: no, linear extra space (not in-place)
> 
> Quick sort: no, quadratic time in worst case
> 
> Heap sort: yes!

**Bottom line.** Heapsort is optimal for both time and space, but:

Inner loop longer than quicksort's makes poor use of cache. Besides, Heapsort is not stable.

---

The number of exchanges when constructs a sink-based heap tree.

Assume that N = $2^{h+1}-1$

> 第1層最多要交換 h 次
> 
> 第2層最多要交換 h-1 次且第 2 層有 2 個點
> 
> 第3層最多要交換 h-2 次且第 3 層有 4 個點

$$
h+2(h-1)+4(h-2)+8(h-3)+...+2^h(0)
$$

$$
= h+2h+4h+...+(2^h)h - (1*2+2*4+3*8+...+h*2^h)
$$

$$
=h(1+2+4+...+2^h)-\Sigma_{i=1}^h (i*2^i)
$$

---

> $$
> \Sigma_{i=1}^h (i*2^i)=?
> $$
> 
> $$
> S = 1*2+2*4+3*8+...+h*2^h\\
2S = 1*4+2*8+3*16+...+h*2^{h+1}\\
2S-S = -1*2-1*4-1*8-...-1*2^{h}+h*2^{h+1} = S
> $$
> 
> $$
> S = h*2^{h+1}-\frac{2(2^h-1)}{2-1} = h*2^{h+1}-2^{h+1}+2
> $$

$$
=h(2^{h+1}-1)/(2-1)-((h-1)2^{h+1}+2)
$$

$$
h(2^{h+1})-h-(h-1)2^{h+1}-2
$$

$$
= 2^{h+1}-h-2
$$

$$
= N- (h-1)
$$

$$
\leq N
$$

Best case: array 中的值都一樣時會有 Best case，<mark>已經排序好的並不是最佳的 case</mark>
