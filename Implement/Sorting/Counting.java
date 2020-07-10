package Sorting;
public class Counting{
    public static void sort(int[] arr, int r){
        int len = arr.length;
        int[] count = new int[r+1];
        //Count
        for(int i=0; i<len; i++){
            count[arr[i]+1]++;
        }
        //Accumulate
        for(int i=1; i<r+1; i++){
            count[i] += count[i-1];
        }
        int[] aux = new int[len];
        //Distribute arr to aux by count[]
        for(int i=0; i<len; i++){
            aux[count[arr[i]]++] = arr[i];
        }
        //Replace arr with aux
        for(int i=0; i<len; i++){
            arr[i] = aux[i];
        }
    }
}
