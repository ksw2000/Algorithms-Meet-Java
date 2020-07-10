package Sorting;
public class ThreeWayQuick{
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void sort(int[] arr, int from, int to){
        if(from >= to) return;
        int pivot = arr[from];
        int lt = from;   //less than
        int gt = to;     //great than
        int i;
        for(i=from+1; i<=gt;){
            if(arr[i] < pivot){
                //如果比pivot小交換並且+1，因為靠左的一定都比pivot小所以要+1
                swap(arr, i++, lt++);
            }else if(arr[i] > pivot){
                //如果比pivot大交換但不+1，因為靠右的不確定比pivot大還小
                swap(arr, i, gt--);
            }else{
                i++;
            }
        }
        sort(arr, from, lt-1);
        sort(arr, gt+1, to);
    }

    public static void sort(int[] arr){
        sort(arr, 0, arr.length-1);
    }
}
