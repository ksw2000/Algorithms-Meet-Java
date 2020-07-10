package Shuffle;
public class Knuth{
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] shuffle(int[] arr){
        int len = arr.length;
        for(int i=0; i<len; i++){
            swap(arr, i, (int)(Math.floor(Math.random()*(i+1))));
        }
        return arr;
    }

    public static void printArrayLn(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.printf("%2d ",arr[i]);
        }
        System.out.println();
    }
}
