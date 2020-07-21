package Heap;
import java.util.*;

// This is a simple example for generic min heap

public class MinHeap<Key extends Comparable<Key>>{
    private Object[] list;
    private int cap;
    private int top;

    public MinHeap(){
        this.cap = 8;
        this.top = 1;
        list = new Object[8]; //we can use 1 ~ 7
    }

    private boolean less(int a, int b){
        Key ka = (Key)list[a];
        Key kb = (Key)list[b];
        return ka.compareTo(kb) < 0;
    }

    private void resize(int new_cap){
        if(new_cap < 8) return;
        Object[] new_list = new Object[new_cap];
        System.arraycopy(list, 0, new_list, 0, top);
        this.list = new_list;
    }

    private void swim(int i){
        //if its child is smaller than its parents, swap()
        while(i>1 && less(i, i >> 1)){
            swap(i >> 1, i);
            i >>= 1;
        }
    }

    private void sink(int i){
        while((i << 1) <= top){
            int j = i << 1;
            // attemp to compare to the less one.
            if(j+1 <= top && less(j+1, j)){
                j++;
            }
            // equivalent to if (j>=i) than break
            // i.e. if all of its children are bigger than its parent than break
            if(!less(j, i)) break;
            swap(i, j);
            i = j;
        }
    }

    private void swap(int i, int j){
        Object temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public void insert(Object v){
        if(top == cap){
            resize(cap << 1);
        }
        list[top] = v;
        swim(top);
        top++;
    }

    public Object remove(int i){
        if(top < (cap >> 2)){
            resize(cap >> 1);
        }

        Object tmp = list[i];
        list[i] = list[--top];
        sink(i);
        return tmp;
    }

    public boolean isEmpty(){
        return this.top == 1;
    }

    public int size(){
        return top - 1;
    }
}
