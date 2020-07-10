/************************************************************
 使用說明：

 int[][] readTestData(int num)
    給定 num 後 readTestData() 會抓取 test_data_{num}.txt
    並將讀取到的結果回傳成一個二維陣列


 void run(String version, LSPath lsp)
    這是一個簡單的時間計算函式
    參數 lsp 放進要改的物件比如一般來說是 new LSPath4107056019()
    但有時候有其他版本比如 new LSPath4107056019_2()

    參數 version 不影響函數執行，只是會在函式印出答案及秒數前印出
    version 的值，方便 Debug


請注意：該檔案應與被批改之檔案共同編譯在同一個地址中
且測資檔案也在同一個編譯的位置中，比如：

./ ─┬ test.java
    ├ test.class
    ├ LSPath.java
    ├ LSPath.class
    ├ LSPath4107056019.java
    ├ LSPath4107056019.class
    └ test_data_1.txt

或

./ ─┬ out/ ┬ test.class
    │      ├ LSPath.class
    │      ├ LSPath4107056019.class
    │      └ test_data_1.txt
    │
    ├ test.java
    ├ LSPath.java
    └ LSPath4107056019.java

************************************************************/
import java.io.*;
import java.util.*;

public class test{
    static int[][] readTestData(int num){
        ArrayList<int[]> a = new ArrayList<int[]>();
        try{
            File f = new File("test_data_" + String.valueOf(num) + ".txt");
            Scanner input = new Scanner(f);
            int i = 0;
            while(input.hasNext()){
                a.add(new int[]{(int)Integer.parseInt(input.next()), (int)Integer.parseInt(input.next())});
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        int[][] return_array = new int[a.size()][2];

        int i=0;
        for(Object v : a.toArray()){
            return_array[i][0] = ((int[])v)[0];
            return_array[i][1] = ((int[])v)[1];
            i++;
        }

        return return_array;
    }

    static void run(String version, LSPath lsp){
        int[][] data0 = readTestData(0);
        /*
        int[][] data1 = readTestData(1);
        int[][] data2 = readTestData(2);
        int[][] data3 = readTestData(3);
        */
        System.out.println(version);

        long time = System.currentTimeMillis();

        System.out.println(lsp.Ans(data0)+" ");
        /*
        System.out.println(lsp.Ans(data1)+" ");
        System.out.println(lsp.Ans(data2)+" ");
        System.out.println(lsp.Ans(data3)+" ");
        */
        time = System.currentTimeMillis() - time;

        System.out.println("Take: " + time + "ms");
        System.out.println();
    }

    public static void main(String[] args){
        run("測試 1：", new LSPath4107056019());
        //run("測試 版本2：", new LSPath4107056019_2());
    }
}
