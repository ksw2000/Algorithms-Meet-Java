import Heap.*;
import java.util.*;

class Person implements Comparable<Person>{
    private int age;
    private String name;

    Person(int age, String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("[%d, \"%s\"]", age, name);
    }

    @Override
    public int compareTo(Person that){
        return this.age - that.age;
    }
}

public class PQDEMO{
    public static void main(String[] args){
        // Create some data
        Person ririri = new Person(14, "ririri");
        Person yuna = new Person(15, "yuna");
        Person maikichi = new Person(15, "maikichi");
        Person yupon = new Person(16, "yupon");
        Person sueka = new Person(17, "sueka");

        System.out.println("Min heap DEMO:");
        MinHeap<Person> mh = new MinHeap<Person>();
        mh.insert(yuna);
        mh.insert(yupon);
        mh.insert(sueka);
        mh.insert(ririri);
        mh.insert(maikichi);

        System.out.println(mh.remove(1));   // [14, "ririri"]
        System.out.println(mh.remove(1));   // [15, "maikichi"]
        System.out.println(mh.remove(1));   // [15, "yuna"]
        System.out.println(mh.remove(1));   // [16, "yupon"]
        System.out.println(mh.remove(1));   // [17, "sueka"]

        System.out.println();
        System.out.println("Priority Queue DEMO:");
        PQ<Person> pq = new PQ<Person>();
        pq.insert(yuna);
        pq.insert(yupon);
        pq.insert(sueka);
        pq.insert(ririri);
        pq.insert(maikichi);

        System.out.println(pq.delMin());   // [14, "ririri"]
        System.out.printf("Is queue empty? %b\n", pq.isEmpty());    // false
        System.out.println(pq.delMin());   // [15, "maikichi"]
        System.out.printf("Is queue empty? %b\n", pq.isEmpty());    // false
        System.out.println(pq.delMin());   // [15, "yuna"]
        System.out.printf("Is queue empty? %b\n", pq.isEmpty());    // false
        System.out.println(pq.delMin());   // [16, "yupon"]
        System.out.printf("Is queue empty? %b\n", pq.isEmpty());    // false
        System.out.println(pq.delMin());   // [17, "sueka"]
        System.out.printf("Is queue empty? %b\n", pq.isEmpty());    // true
    }
}
