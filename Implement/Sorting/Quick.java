package Sorting;
public class Quick{
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int partition(int[] arr, int start, int end){
        int pivot = arr[start];
        int i,j;
        //from left to right
        for(i=start+1, j=end;;){
            for(; i<=j && arr[i]<=pivot; i++);
            for(; j>=i && arr[j]>=pivot; j--);
            if(i>j) break;
            swap(arr, i, j);
        }
        //arr[j] is less than arr[start]
        swap(arr, start, j);
        return j;
    }

    private static void sort(int[] arr, int from, int to){
        if(from >= to)return;
        int mid = partition(arr, from, to);
        sort(arr, from, mid-1);
        sort(arr, mid+1, to);
    }

    public static void sort(int[] arr){
        sort(arr, 0, arr.length-1);
    }
}
