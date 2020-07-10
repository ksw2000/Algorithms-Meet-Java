import Sorting.*;
public class SortingDemo{
    public static void main(String[] args){
        int a[] = {1,3,2,6,8,9,10,11,12,18,14,3};
        System.out.print("Insertion sort:\t");
        Insertion.sort(a);
        for(int i=0; i<a.length; i++){
            System.out.printf("%2d ", a[i]);
        }
        System.out.println();

        int b[] = {1,3,2,6,8,9,10,11,12,18,14,3};
        System.out.print("Selection sort:\t");
        Selection.sort(b);
        for(int i=0; i<b.length; i++){
            System.out.printf("%2d ", b[i]);
        }
        System.out.println();

        int c[] = {1,3,2,6,8,9,10,11,12,18,14,3};
        System.out.print("(Top-Down)\n");
        System.out.print("  Merge sort:\t");
        Merge.sort(c);
        for(int i=0; i<c.length; i++){
            System.out.printf("%2d ", c[i]);
        }
        System.out.println();

        int c2[] = {1,3,2,6,8,9,10,11,12,18,14,3};
        System.out.print("(Bottom-up)\n");
        System.out.print("  Merge sort:\t");
        MergeBU.sort(c2);
        for(int i=0; i<c2.length; i++){
            System.out.printf("%2d ", c2[i]);
        }
        System.out.println();

        int d[] = {1,3,2,6,8,9,10,11,12,18,14,3};
        System.out.print("Shell sort:\t");
        Shell.sort(d);
        for(int i=0; i<d.length; i++){
            System.out.printf("%2d ", d[i]);
        }
        System.out.println();

        int f[] = {1,3,2,6,8,9,10,11,12,18,14,3};
        System.out.print("Quick sort:\t");
        Quick.sort(f);
        for(int i=0; i<f.length; i++){
            System.out.printf("%2d ", f[i]);
        }
        System.out.println();

        System.out.println();
        System.out.print("3-way Quick sort:\n");
        int g[] = {1,7,7,7,7,23,23,9,9,9,9,1,1,1,25,25,25,25};
        ThreeWayQuick.sort(g);
        for(int i=0; i<g.length; i++){
            System.out.printf("%2d ", g[i]);
        }
        System.out.println();

        System.out.println("Counting sort:");
        int h[] = {0,1,3,6,4,5,6,7,8,9,1,2,3,6,3,4,6,5,4,2,6,0};
        Counting.sort(h, 10);
        for(int i=0; i<h.length; i++){
            System.out.printf("%2d ", h[i]);
        }
        System.out.println();

        System.out.println("LSD sort:");
        String m[] = {"TPE", "KEE", "Egg", "Pig", "SDK", "JDK", "JET", "RIP", "EAX", "EBX", "Cat" , "Dog"};
        LSD.sort(m, 3);
        for(int i=0; i<m.length; i++){
            System.out.printf("%s ", m[i]);
        }
        System.out.println();

        System.out.println("MSD sort:");
        String n[] = {"TPE", "KEE", "Egg", "Pig", "SDK", "JDK", "JET", "RIP", "EAX", "EBX", "Cat" , "Dog"};
        MSD.sort(n, 3);
        for(int i=0; i<n.length; i++){
            System.out.printf("%s ", n[i]);
        }
        System.out.println();
    }
}
