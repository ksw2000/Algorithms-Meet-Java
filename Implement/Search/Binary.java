package Search;
import java.util.*;
public class Binary{
    public static int search(int[] arr, int val){
        int start = 0;
        int end = arr.length-1;
        int mid;
        for(mid = (start+end) >>1;; mid = (start+end)>>1){
            if(arr[mid] > val){
                end = mid-1;
            }else if(arr[mid] < val){
                start = mid+1;
            }else{
                break;
            }
        }
        return mid;
    }
}
