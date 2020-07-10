package Sorting;
public class Shell{
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void h_sort(int[] arr, int h){
        int len = arr.length;
        //從第(h)個往左掃(每次減h) 做 Insertion search
        for(int i=h; i<len; i++){
            for(int j=i; j>=h; j-=h){
                // 若靠左的比較大，就往左swap直到右邊比左邊大
                // 因為 假設你要排序 1, 4, 7
                // 這時 1, 4 在先前就排好了(若先前是 4,1 則遍歷到 1 時就會排成 1,4)
                // 所以 只是檢查 7 有沒有比 4 小而已有的話就往前插
                // 沒的話就 break 往下檢查 (2, 5, 8)
                if(arr[j] < arr[j-h]){
                    swap(arr, j, j-h);
                }else{
                    break;
                }
            }
        }
    }

    public static void sort(int[] arr){
        int len = arr.length;
        //製作 h (3n+1)
        int h=1;
        while(h<len){
            h = h*3+1;
        }

        while(h>=1){
            h_sort(arr, h);
            h /= 3;
        }
    }
}
