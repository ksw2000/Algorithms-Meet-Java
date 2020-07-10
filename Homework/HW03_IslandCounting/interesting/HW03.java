package home;

import home.hw_compiler.HW03.*;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.reflect.Constructor;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Calendar;

public class HW03 {
    static String number = "03"; // 作業編號
    static double roundNum = 1; // 要跑多少次
    static int Ans = 0;
    static int num = 0;

    static String data_path_A = "";
    static String data_path_B = "";

    static String rank_path = "home//alg2020//hw_upload//HW03_rank.txt";
    static String student_className_path = "StudentClassName_HW03.txt";

    public static void main(String[] args) throws Exception {

        int case_Ans = 0;
        Calendar rightNow = Calendar.getInstance();
        int type = rightNow.get(Calendar.DAY_OF_MONTH);
        System.out.println("date:" + rightNow.get(Calendar.DAY_OF_MONTH));

        switch (type) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                data_path_A = "home//testing_data//HW03_data//web-Google//A.txt";
                data_path_B = "home//testing_data//HW03_data//web-Google//B.txt";
                num = 5105039;
                case_Ans = 2746; // home//testing_data//HW03_data//web-Google//ans.txt
                break;
            case 7:
                data_path_A = "home//testing_data//HW03_data//roadNet-PA//A.txt";
                data_path_B = "home//testing_data//HW03_data//roadNet-PA//B.txt";
                num = 3083796;
                case_Ans = 206; // home//testing_data//HW03_data//roadNet-PA//ans.txt
                break;
            case 8:
                data_path_A = "home//testing_data//HW03_data//com-youtube.ungraph//A.txt";
                data_path_B = "home//testing_data//HW03_data//com-youtube.ungraph//B.txt";
                num = 2987624;
                case_Ans = 1; // home//testing_data//HW03_data//com-youtube.ungraph//ans.txt
            case 9:
                data_path_A = "home//testing_data//HW03_data//roadNet-CA//A.txt";
                data_path_B = "home//testing_data//HW03_data//roadNet-CA//B.txt";
                num = 5533214;
                case_Ans = 2638; // home//testing_data//HW03_data//roadNet-CA//ans.txt
                break;
            case 10:
                data_path_A = "home//testing_data//HW03_data//com-dblp.ungraph//A.txt";
                data_path_B = "home//testing_data//HW03_data//com-dblp.ungraph//B.txt";
                num = 1049866;
                case_Ans = 1; // home//testing_data//HW03_data//com-dblp.ungraph//ans.txt
                break;
            case 11:
                data_path_A = "home//testing_data//HW03_data//com-youtube.ungraph//A.txt";
                data_path_B = "home//testing_data//HW03_data//com-youtube.ungraph//B.txt";
                num = 2987624;
                case_Ans = 1; // home//testing_data//HW03_data//com-youtube.ungraph//ans.txt
                break;
            case 12:
                data_path_A = "home//testing_data//HW03_data//com-amazon.ungraph//A.txt";
                data_path_B = "home//testing_data//HW03_data//com-amazon.ungraph//B.txt";
                num = 925872;
                case_Ans = 1; // home//testing_data//HW03_data//com-amazon.ungraph//ans.txt
                break;
            case 13:
                data_path_A = "home//testing_data//HW03_data//roadNet-CA//A.txt";
                data_path_B = "home//testing_data//HW03_data//roadNet-CA//B.txt";
                num = 5533214;
                case_Ans = 2638; // home//testing_data//HW03_data//roadNet-CA//ans.txt
                break;
            default:
                data_path_A = "home//testing_data//HW03_data//roadNet-CA//A.txt";
                data_path_B = "home//testing_data//HW03_data//roadNet-CA//B.txt";
                num = 5533214;
                case_Ans = 2638; // home//testing_data//HW03_data//roadNet-CA//ans.txt
                break;
        }

        String[] case_A = new String[num];
        String[] case_B = new String[num];

        // 讀入測資檔案
        case_A = loadData(data_path_A, num);
        case_B = loadData(data_path_B, num);

        int countRank = 0;
        FileWriter fw = new FileWriter(rank_path, true);
        ArrayList<Student> list = new ArrayList<Student>();
        ArrayList<Student> errlist = new ArrayList<Student>();
        Student new1;

        HashMap<String, Student> stuMap = new HashMap<String, Student>();
        HashMap<String, Student> stuerrMap = new HashMap<String, Student>();

        String filename = "";
        FileInputStream frsl = new FileInputStream(student_className_path); // 取得學生的function name
        BufferedReader brsl = new BufferedReader(new InputStreamReader(frsl));
        while (brsl.ready()) {
            filename = brsl.readLine();
        }
        frsl.close();
        brsl.close();

        // exec HW java
        // 跑幾次
        double round = 10;
        for (int roundAll = 0; roundAll < round; roundAll++) {
            System.out.println("****" + filename);

            boolean allcase = true; // 如果答案一有錯就會false
            double alltime = 0; // 執行時間
            if (filename.equals("IslandCounting") || filename.equals("") || stuerrMap.containsKey(filename))
                continue;

            Student oneResult = null;
            // case 1
            System.out.println("case1");
            if (allcase) {
                Ans = case_Ans;
                oneResult = oneTestdata(filename, case_A, case_B);
                if (!oneResult.stats.equals("Correct"))
                    allcase = false;
                else
                    alltime += oneResult.durTime;
                System.out.println(allcase);
            }

            if (allcase) {
                if (stuMap.containsKey(filename)) {
                    Student stuTemp = stuMap.get(filename);
                    stuTemp.durTime = stuTemp.durTime + alltime;
                    stuMap.put(filename, stuTemp);
                }

                else {
                    Student thisStudent = new Student(filename, "Correct", (alltime));
                    stuMap.put(filename, thisStudent);
                }
                // list.add(oneResult);
            }

            else {
                System.out.println(oneResult.fn + " " + oneResult.stats);
                stuerrMap.put(filename, oneResult);
                // errlist.add(oneResult);
            }

        }

        for (String Key : stuMap.keySet()) {
            Student colstu = stuMap.get(Key);
            System.out.println(colstu.durTime);

            // double[] time_list = new double[10];
            // for(int x ; x < time_list.length ; x++){
            // time_list =
            // }

            list.add(new Student(colstu.fn, colstu.stats, (colstu.durTime / round)));
        }
        for (String Key : stuerrMap.keySet()) {
            errlist.add(stuerrMap.get(Key));
        }

        String writeFormat = "%s,%s,%f\n";
        String line = "";
        for (int r = 0; r < 115; r++)
            line += "-";
        for (Student poplist : list) {
            fw.write(String.format(writeFormat, poplist.fn, poplist.stats, poplist.durTime));
            fw.flush();
        }
        for (Student poplist : errlist) {
            fw.write(String.format(writeFormat, poplist.fn, poplist.stats, poplist.durTime));
            fw.flush();
        }
        // fw.write(String.format(writeFormat , ++countRank,"ArrayData4104056022_4
        // ","Error: Infinite loop",0));
        fw.flush();
        fw.close();
    }

    static Student receiveData;

    @SuppressWarnings("deprecation")
    public static Student oneTestdata(final String fn, final String[] A, final String[] B) {
        // exec HW java
        double tempTime = 0;
        boolean correctWrite = true;
        String stats = "";
        // final int[] inputA = A;
        // test n round
        correctWrite = true;
        tempTime = 0;
        int onemore = 0;
        // produce Answer
        receiveData = new Student(fn, "empty", 0);
        for (int countR = 0; countR < roundNum; countR++) {

            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    receiveData = checkAns(fn, A, B);
                    System.out.print("k");
                }
            });
            t1.start();
            // 設定time out
            try {
                for (int timer = 0; t1.isAlive() && timer < 300; timer++) {
                    Thread.sleep(1 * 1000);
                }
            } catch (InterruptedException e) {
                System.out.println("ERROR: sleep");
            }

            if (receiveData.stats.equals("empty")) {
                t1.stop();
                System.out.println("stop");
                System.out.println("timeout");
                stats = "Error: Timeout.";
                return new Student(fn, stats, 0);
            }
            System.out.print("n");

            stats = receiveData.stats;
            if (onemore == 0 && stats.contains("InvocationTargetException")) {
                System.out.println("start again");
                countR--;
                onemore++;
            } else if (!stats.equals("Correct")) {
                return new Student(fn, stats, 0);
            }
            tempTime += receiveData.durTime;
        }
        if (correctWrite)

            return new Student(fn, stats, (tempTime / roundNum));
        // list.add(new Student(fn,stats,(tempTime/roundNum)));
        return new Student(fn, "empty", 0);

    }

    public static Student checkAns(final String fn, final String[] A, final String[] B) {
        try {
            double Rtime = 0;
            String errorString = "Error: function ";
            int errorStrLen = errorString.length();
            String className = "home.hw_compiler.HW" + number + "." + fn;

            Class cName = Class.forName(className);
            System.out.print("*cName*");

            /* 使用Method這個物件去呼叫指定Class的method,method name要和呼叫class中的method name相同 */
            Method one = cName.getMethod("count", new Class[] { String[].class, String[].class });

            long start = System.nanoTime();
            // int result1 = (int)one.invoke(cName.newInstance() , A);
            int result1 = (int) one.invoke(cName.newInstance(), new Object[] { A, B });

            if (result1 != Ans) {
                errorString += "count() Wrong Answer.";
            }
            double durTime = ((System.nanoTime() - start) / 1000000000.0);
            Rtime += durTime;

            System.out.println("Student's Answer: ");
            System.out.println(result1);

            if (errorString.length() > errorStrLen) {
                return new Student("", errorString, 0);
            }
            return new Student("", "Correct", Rtime);
        } catch (ClassNotFoundException e) {
            System.out.println("compiler error");
            return new Student("", "Error: compiler error.", 0);
        } catch (NoSuchMethodException e) {
            System.out.println("Errorns: " + e);
            return new Student("", "Error: No Such Method Exception.", 0);
        } catch (UnsupportedClassVersionError e) {
            System.out.println("Errorns: " + e);
            return new Student("", "Error: The annotation contains chinese Character.", 0);
        } catch (InstantiationException e) {
            System.out.println("Errorns: " + e);
            return new Student("", "Error: Instantiation Exception.", 0);
        } catch (NullPointerException e) {
            System.out.println("Errorns: " + e);
            return new Student("", "Error: Null Pointer Exception.", 0);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            String che = e.toString();
            if (che.contains("InvocationTargetException")) {
                // Throwable t = e.getTargetException();
                // t.printStackTrace();
                e.printStackTrace();
                return new Student("", "Error: InvocationTargetException.", 0);
            }
            e.printStackTrace();
            return new Student("", "Error: other error.", 0);
        }

    }

    public static String[] loadData(String data_path, int data_size) {
        /* case1 load */
        String[] data = new String[data_size];
        int count = 0; // 測資的index
        try {
            FileInputStream frsll = new FileInputStream(data_path);
            BufferedReader brsll = new BufferedReader(new InputStreamReader(frsll));
            try {
                while (brsll.ready()) {
                    String ts = brsll.readLine();
                    data[count++] = ts;
                    if (count >= num) {
                        break;
                    }
                }
                frsll.close();
                brsll.close();
            } catch (IOException exx) {

            }
        } catch (FileNotFoundException ex) {

        }

        return (data);
    }
}
