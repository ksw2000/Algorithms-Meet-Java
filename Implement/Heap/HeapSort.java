package Heap;

//Sink-based Heap sort
public class HeapSort{
    static int[] arrShift;
    static int len;

    public static void sort(int[] arr){
        len = arr.length;
        arrShift = new int[len + 1];
        System.arraycopy(arr, 0, arrShift, 1, len);

        for(int i = (len >> 1); i>=1; i--){
            sink(i);
        }

        int oldlen = len;

        while(len > 1){
            swap(1, len--);
            sink(1);
        }

        System.arraycopy(arrShift, 1, arr, 0, oldlen);
    }

    private static void sink(int i){
        while(i*2 <= len){
            int j = i << 1;
            if(j+1 <=len && arrShift[j] < arrShift[j+1]){
                j++;
            }

            if(arrShift[j] > arrShift[i]){
                swap(i, j);
            }

            i = j;
        }
    }

    private static void swap(int i, int j){
        arrShift[i] = arrShift[i] ^ arrShift[j];
        arrShift[j] = arrShift[i] ^ arrShift[j];
        arrShift[i] = arrShift[i] ^ arrShift[j];
    }
}
