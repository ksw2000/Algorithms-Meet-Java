package Sorting;
public class Selection{
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    private static int getMinIndex(int[] arr, int from, int to){
        int min = arr[from];
        for(int i=from; i<=to; i++){
            if(min > arr[i]){
                from = i;
            }
        }
        return from;
    }

    public static void sort(int[] arr){
        int len = arr.length;
        //從沒排好的右側找最小的來交換
        for(int i=0; i<len; i++){
            // 講義是從 i+1 開始抓，講義最小值預設 arr[i]
            // 但我預設最小值是 arr[i+1] 所以在 getMinIndex() 會 OutOfBound
            swap(arr, i, getMinIndex(arr, i, len-1));
        }
    }
}
