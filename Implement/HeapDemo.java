import Heap.*;
import java.util.*;

public class HeapDemo{
    public static void main(String[] args){
        System.out.println("Max heap demo:");
        MaxHeap mh = new MaxHeap();
        mh.insert(10);
        mh.insert(20);
        mh.insert(30);
        mh.insert(40);
        System.out.println(mh.remove(1));   // 40
        System.out.println(mh.remove(1));   // 30
        mh.insert(50);
        mh.insert(60);
        mh.insert(70);
        mh.insert(80);
        mh.insert(90);
        mh.insert(100);
        mh.insert(110);
        mh.insert(120);
        mh.insert(130);
        mh.insert(140);
        mh.insert(150);
        mh.insert(160);
        mh.insert(170);
        System.out.println(mh.remove(1));   // 170
        System.out.println(mh.remove(1));   // 160
        System.out.println(mh.remove(1));   // 150
        System.out.println(mh.remove(1));   // 140
        System.out.println(mh.remove(1));   // 130
        System.out.println(mh.remove(1));   // 120
        System.out.println(mh.remove(1));   // 110
        System.out.println(mh.remove(1));   // 100
        System.out.println(mh.remove(1));   // 90
        System.out.println(mh.remove(1));   // 80
        System.out.println(mh.remove(1));   // 70

        System.out.println("\nHeap sort demo:");
        int[] arr = {3, 4, 5, 7, 1, 9, 5, 8, 18};
        HeapSort.sort(arr);
        for(int i=0; i<arr.length; i++){
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }
}
