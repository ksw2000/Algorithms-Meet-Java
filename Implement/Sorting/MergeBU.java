package Sorting;
public class MergeBU{
    private static void merge(int[] arr,int[] aux, int start, int mid, int end){
        int index;
        //將 aux 做為暫存, 而 arr 才為排序後正確結果
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

    private static int min(int a, int b){
        return (a>b)? b : a;
    }

    public static void sort(int[] arr){
        int len = arr.length;
        int[] aux = new int[len];

        for(int size=1; size<len; size+=size){
            for(int i=0; i+size < len; i+=size+size){
                //start = i
                //mid   = i+size-1
                //end   = i+size+size-1
                //其中 mid "i+size-1" 這項記得要減 1 不然會錯
                //因為 mid = (star + end)/2 = (i + (i+size+size-1)) / 2
                //所以 mid = i + size - (1/2) 所以直接 -1 不然會出錯
                merge(arr, aux, i, i+size-1, min(i+size+size-1, len-1));
            }
        }
    }
}
