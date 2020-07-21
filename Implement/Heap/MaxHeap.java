package Heap;
import java.util.*;

// This is a simple example of implementing max heap
// If you want to know how to implement generic heap
// visit MinHeap.java in the same folder

public class MaxHeap{
    private int[] list;
    private int cap;
    private int top;

    public MaxHeap(){
        this.cap = 8;
        this.top = 1;
        list = new int[8]; //we can use 1 ~ 7
    }

    private void resize(int new_cap){
        if(new_cap < 8) return;
        int[] new_list = new int[new_cap];
        System.arraycopy(list, 0, new_list, 0, top);
        this.list = new_list;
    }

    private boolean less(int a, int b){
        return list[a] < list[b];
    }

    private void swim(int i){
        //if its parents is smaller than its child, swap()
        while(i>1 && less(i >> 1, i)){
            swap(i >> 1, i);
            i >>= 1;
        }
    }

    private void sink(int i){
        while((i << 1) <= top){
            int j = i << 1;
            // attemp to compare to the bigger one.
            if(j+1 <= top && less(j, j+1)){
                j++;
            }
            // equivalent to if (i>=j) than break
            if(!less(i, j)) break;
            swap(i, j);
            i = j;
        }
    }

    private void swap(int i, int j){
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public void insert(int v){
        if(top == cap){
            resize(cap << 1);
        }

        list[top] = v;
        swim(top);
        top++;
    }

    public int remove(int i){
        if(top < (cap >> 2)){
            resize(cap >> 1);
        }

        int tmp = list[i];
        list[i] = list[--top];
        sink(i);
        return tmp;
    }

    public boolean isEmpty(){
        return this.top == 1;
    }
}
