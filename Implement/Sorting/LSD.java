package Sorting;
public class LSD{
    public static void sort(String[] arr, int w){
        int len = arr.length;
        int r   = 256;
        for(int i=w-1; i>=0; i--){
            int[] count = new int[r+1];
            String[] aux = new String[len];

            //Count
            for(int j=0; j<len; j++){
                count[arr[j].charAt(i)+1]++;
            }

            //Accumulate
            for(int j=1; j<r+1; j++){
                count[j] += count[j-1];
            }

            //Rearrange
            for(int j=0; j<len; j++){
                aux[count[arr[j].charAt(i)]++] = arr[j];
            }

            //Overwrite
            for(int j=0; j<len; j++){
                arr[j] = aux[j];
            }
        }
    }
}
