# Sorting Array

The goal of this assignment, sorting int32 array, is simple. However, how to optimize your code is the big problem.

My program is based on "Merge sort". Because it is not gambling like "Quick sort". I guess that there are a lot of bad test data, provided by the students who want to paralyze quick sort. Additionally, if quick sort calls itself so many times that it will break down since stack overflow.

However, in fact, the 0th rank, Bod Lee, said that he had chosen "quick sort" instead of "merge sort". The 3rd rank, Liang Wei, said that he had chosen "shell sort".

It is worth mentioning that，the rank 9th, [@tomy0000000](https://github.com/tomy0000000), and the 13th rank, [@twJmy](https://github.com/twJmy), used `java.Arrays.sort` , the former used soruce code copied from web, the later called function straightforwardly, lol.

The 17th rank, Wenchuang, said that his program had been based on "3-way quick sort". In fact, he got better score in the past test but he dropped to bad rank at the deadline.

The 56th rank, [@wei-coding](https://github.com/wei-coding), used "shell sort" like the 3rd rank, but he added function that check duplicate key in array. He thought too much, :rofl:.

**Rank**

|Rank|ID          |Time       |
|----|------------|-----------|
| 0  | 4107056040 | 0.104965s |
| 1  | 4107056017 | 0.107164s |
| __2__  | __4107056019__ | __0.110711s__ |
| 3  | 4107056032 | 0.113625s |
| 4  | 4107056035 | 0.115924s |
| ...| ...        | ...       |
| 9  | 4107056042 | 0.123173s |
| ...| ...        | ...       |
| 13 | 4108056020 | 0.126954s |
| ...| ...        | ...       |
| 17 | 4107056002 | 0.132729s |
| ...| ...        | ...       |
| 56 | 4105053128 | 0.225196s |

## How to optimize merge sort?

### Use cut off
In simple merge sort, we cut array until the length of array is 2. But, actually, we do not need to cut them so small. We can sort them by using "insertion sort" when their length is shorter than 7 or 8 or 9.

I recommand choose 8, because our computer is good at processing the number whose base is 2.

```java
if(from + 9 > to){
    insertion_sort(from, to);
    return;
}
```

### Fast merge
If you have two sorted array, the one is i\~j, and the other is m\~n. If j<=m, you can quickly merge them by copying instead of comparing.

```java
void merge(int start, int mid, int end){
    if(arr[mid-1] <= arr[mid]) return;
    System.arraycopy(arr, start, aux, start, end-start);
    //skip
}
```

### Replace <, >, <=, >= to == , != as possible

Our computer processes "equal" or "not equal" more efficiently than processes "less than" or "greater than"

### Multi-thread (Useless, Unhelpful)

I tried to use multi-thread for optimizing many times, but they are obviously slower than the programs which are single-thread.

I think there are two assumptions can interpret.

First, initialize a thread cost a lot of time. Maybe we need an enough long array to see that "multi-thread run faster than single-thread".

Second, many threads want to get data in array simultaneously, so "cache" cannot work, instead, they get the data by memory without "cache". This is just my view point, maybe it is not correct in the real world.
