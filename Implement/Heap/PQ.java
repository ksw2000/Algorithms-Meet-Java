package Heap;
import Heap.MinHeap;

public class PQ<Key extends Comparable<Key>>{
    private MinHeap<Key> h = new MinHeap<Key>();;

    //Create an empty priority queue
    public PQ(){

    }

    public PQ(Comparable[] a){
        for(Comparable v : a){
            insert(v);
        }
    }

    public void insert(Comparable v){
        h.insert(v);
    }

    //Return and remove the largest key
    public Key delMin(){
        return (Key)h.remove(1);
    }

    //Is the priority queue empty?
    public boolean isEmpty(){
        return h.isEmpty();
    }

    //Number of entries in the priority queue
    public int size(){
        return h.size();
    }
}
