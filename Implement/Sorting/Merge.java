package Sorting;
public class Merge{
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void merge(int[] arr,int[] aux, int start, int mid, int end){
        int index;
        //將 aux 做為 暫存 a 為排序後正確結果
        for(index=start; index<=end; index++){
            aux[index] = arr[index];
        }
        index=start;
        int i,j;
        for(i=start, j=mid+1; i<=mid && j<=end;){
            if(aux[i]<aux[j]){
                arr[index++] = aux[i++];
            }else{
                arr[index++] = aux[j++];
            }
        }
        while(i<=mid) arr[index++] = aux[i++];
        while(j<=end) arr[index++] = aux[j++];
    }

    private static void sort(int[] arr, int[] aux, int from, int to){
        if(from >= to) return;
        int mid = (from+to) >> 1;
        sort(arr, aux, from, mid);
        sort(arr, aux, mid+1, to);
        merge(arr, aux, from, mid, to);
    }

    public static void sort(int[] arr){
        int len = arr.length;
        int[] aux = new int[len];
        sort(arr, aux, 0, len-1);
    }
}
