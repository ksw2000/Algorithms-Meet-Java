import Search.*;
public class SearchDemo{
    public static void main(String[] args){
        int[] a = {1,4,5,7,9,10,11,20,45,89};
        System.out.println("Binary Search:");
        System.out.printf("find(10): a[%d] = 10\n", Binary.search(a, 10));
        System.out.printf("find(89): a[%d] = 89\n", Binary.search(a, 89));
        System.out.printf("find(45): a[%d] = 45\n", Binary.search(a, 45));
        System.out.println();

        int[] b = {1,6,3,12,9,3,2,7,8,53,12,74};
        System.out.println("Quick search:");
        System.out.printf("第  5 大的數: %d\n", Quick.search(b,  5));
        System.out.printf("第 11 大的數: %d\n", Quick.search(b, 11));
        System.out.println();
    }
}
