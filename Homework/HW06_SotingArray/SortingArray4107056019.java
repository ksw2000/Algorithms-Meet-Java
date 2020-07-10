//Accelerate by insertion sort and replace <= to !=
public class SortingArray4107056019 extends SortingArray{
    static int[] arr;
    static int[] aux;
    public int[] sorting(int[] A){
        this.arr = A;
        this.aux = new int[A.length];
        sort(0, A.length);
        return A;
    }

    private static void merge(int start, int mid, int end){
        if(arr[mid-1] <= arr[mid]) return;
        System.arraycopy(arr, start, aux, start, end-start);
        int i = start;
        int j = mid;
        while(i!=mid && j!=end) arr[start++] = (aux[i] < aux[j])? aux[i++] : aux[j++];
        while(i!=mid)           arr[start++] = aux[i++];
        while(j!=end)           arr[start++] = aux[j++];
    }

    private static void sort(int from, int to){
        if(from + 9 > to){
            Insertion(from, to);
            return;
        }
        int mid = (from+to) >> 1;
        sort(from, mid);
        sort(mid, to);
        merge(from, mid, to);
    }

    private static void Insertion(int from, int to){
        for(int i=from+1; i<to; i++){
            for(int j=i-1; j>=from && (arr[j] > arr[j+1]); j--){
                arr[j+1] = arr[j+1] ^ arr[j];
                arr[j]   = arr[j+1] ^ arr[j];
                arr[j+1] = arr[j+1] ^ arr[j];
            }
        }
    }
}
