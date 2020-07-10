public class slash4107056019 extends slash{
    public int slash_min(int[] A){
        int start = 0;
        int end   = A.length - 1;
        if(A[start] < A[end]) return -1;
        for(int mid = (start+end) >> 1; mid!=start; mid = (start+end) >> 1){
            if(A[mid] > A[start]){
                start = mid;
            }else{
                end = mid;
            }
        }
        return A.length - end - 1;
    }
}
