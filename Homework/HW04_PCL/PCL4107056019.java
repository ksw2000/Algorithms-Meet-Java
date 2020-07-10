public class PCL4107056019 extends PCL{
    volatile boolean ans;
    int tNum = 32;
    int lgTNum = 5;
    Thread[] t = new Thread[tNum];

    public boolean checkPCL(int[][] array){
        ans = false;
        int len = array.length;
        int hashCap = 1 << ((int)Math.ceil(Math.log10((double)len) / 0.3010));

        if(len > tNum){
            for(int k=0; k<tNum; k++){
                final int finalK = k;
                t[k] = new Thread(new Runnable(){
                    public void run(){
                        final int start = (len * finalK)     >> lgTNum;
                        final int end   = (len * (finalK+1)) >> lgTNum;
                        HashMap m = new HashMap(hashCap);

                        for(int i=start; i<end && !ans; i++){
                            for(int j=i+1; j<len && !ans; j++){
                                if(m.containOrPut((double)(array[i][1] - array[j][1]) / (double)(array[i][0] - array[j][0]))){
                                    ans = true;
                                }
                            }
                            m.reset();
                        }
                    }
                });
                t[k].start();
            }

            try{
                for(Thread v : t) v.join();
            }catch(InterruptedException e){}

            return ans;
        }else{
            int i,j;
            HashMap m = new HashMap(hashCap);
            for(i=0; i<len; i++){
                for(j=i+1; j<len; j++){
                    if(m.containOrPut((double)(array[i][1] - array[j][1]) / (double)(array[i][0] - array[j][0]))){
                        return true;
                    }
                }
                m.reset();
            }

            return false;
        }

    }

    private class HashMap{
        class Entry{
            public double key;
            public Entry next;
        }

        private int cap;
        private Entry[] list;

        HashMap(int size){
            this.cap = size;
            Entry[] newEntry = new Entry[this.cap];
            this.list = newEntry;
        }

        final public void reset(){
            Entry[] newEntry = new Entry[this.cap];
            this.list = newEntry;
        }

        final public boolean containOrPut(double key){
            // & (this.cap - 1) is equivalent to % this.cap when base of this.cap is 2
            int index = (Double.valueOf(key).hashCode() & 0x7fffffff) & (this.cap-1);

            for(Entry current = list[index]; current!=null; current=current.next){
                if(current.key == key){
                    return true;
                }
            }

            Entry newEntry = new Entry();
            newEntry.key  = key;
            newEntry.next = list[index];
            list[index] = newEntry;
            return false;
        }
    }
}
