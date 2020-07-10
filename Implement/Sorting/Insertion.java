package Sorting;
public class Insertion{
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //類似在玩撲克牌，固定左側，往下抽一張，看要插哪
    public static void sort(int[] arr){
        int len = arr.length;
        for(int i=1; i<len; i++){
            for(int j=i-1; j>=0; j--){
                // 如果 [j] 比 [i] 大就交換，直到 [j] 比 [i] 小，break;
                if(arr[j] > arr[j+1]){
                    swap(arr, j+1, j);  //以 j+1 表示 i
                }else{
                    break;
                }
            }
        }
    }
}
