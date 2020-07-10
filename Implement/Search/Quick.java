package Search;
public class Quick{
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int partition(int[] arr, int from, int to){
        int pivot = arr[from];
        int i, j;
        for(i = from+1, j=to;;){
            for(;i<=j && arr[i]<=pivot; i++);
            for(;i<=j && arr[j]>=pivot; j--);
            if(i>j) break;
            swap(arr, i, j);
        }
        swap(arr, from, j);
        return j;
    }

    public static int search(int[] arr, int n){
        int start = 0;
        int end   = arr.length-1;
        int mid;
        while(start < end){
            mid = partition(arr, start, end);
            if(mid < n){
                start = mid+1;
            }else if(mid > n){
                end = mid-1;
            }else{
                return arr[n];
            }
        }
        return arr[n];
    }
}
