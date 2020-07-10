package Sorting;
public class MSD{
    static int R = 256;
    static int W;
    public static void sort(String[] arr, int w){
        W = w;
        String[] aux = new String[arr.length];
        sort(arr, aux, 0, arr.length-1, 0);
    }

    private static void sort(String[] arr, String[] aux, int from, int to, int d){
        if(d>=W)     return;
        if(from>=to) return;
        int len = arr.length;
        int[] count = new int[R+2];
        //count
        for(int i=from; i<=to; i++){
            count[arr[i].charAt(d)+2]++;
        }

        //Accumulate
        for(int i=1; i<R+2; i++){
            count[i] += count[i-1];
        }

        //Rearrange
        for(int i=from; i<=to; i++){
            aux[count[arr[i].charAt(d)+1]++] = arr[i];
        }

        //Overwrite
        //注意容易寫錯
        //第二次 call sort() 時，裡面的 count[] 從 0 算, aux 也從 0 放
        //但實際我們是做 arr[from]~arr[to] 間的排序 所以要 -from 才能對應
        for(int i=from; i<=to; i++){
            arr[i] = aux[i-from];
        }

        //Recursive
        for(int i=0; i<R; i++){
            sort(arr, aux, from+count[i], from+count[i+1]-1, d+1);
        }
    }
}
