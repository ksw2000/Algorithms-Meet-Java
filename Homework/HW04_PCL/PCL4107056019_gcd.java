// Simplify by gcd
public class PCL4107056019_gcd extends PCL{
    public int gcd(int a, int b){
        return b==0 ? a : gcd(b, a%b);
    }

    public boolean checkPCL(int[][] array){
        int i,j;
        int len = array.length;
        int lnLen = (int)Math.ceil(Math.log10((double)len) / 0.3010);
        HashMap m = new HashMap(1 << lnLen);

        int deltaX, deltaY,gcd;
        for(i=0; i<len; i++){
            for(j=i+1; j<len; j++){
                deltaX = array[i][0] - array[j][0];
                deltaY = array[i][1] - array[j][1];

                //If there is duplicate test data return true
                //if(deltaX == 0 && deltaY == 0) return true;

                gcd = gcd(deltaY, deltaX);

                //If Both deltaX and deltaY are positive or negative
                //Than deltaX/gcd and deltaY are positive

                //If one of deltaX and deltaY is positive and the other is negative
                //Than one of deltaX/gcd and deltaY/gcd is positive and the other is negative
                //However, we don't know which one is positive or which one is negative
                //Thus, we will keep deltaX is positive and deltaY is negative
                //By changing their signed.

                deltaX = (deltaX / gcd);
                deltaY = (deltaY / gcd);
                if(deltaX < 0){
                    deltaX = ~deltaX + 1;
                    deltaY = ~deltaY + 1;
                }

                if(m.containOrPut(deltaX, deltaY)){
                    return true;
                }
            }
            m.reset();
        }

        return false;
    }

    private class HashMap{
        class Entry{
            public int key1;
            public int key2;
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

        final public boolean containOrPut(int key1, int key2){
            // & (this.cap - 1) is equivalent to % this.cap when base of this.cap is 2
            int index = ((key1 + key2) & 0x7fffffff) & (this.cap -1);
            for(Entry current = list[index]; current!=null; current=current.next){
                if(current.key1 == key1 && current.key2 == key2){
                    return true;
                }
            }
            Entry newEntry = new Entry();
            newEntry.key1  = key1;
            newEntry.key2  = key2;
            newEntry.next = list[index];
            list[index] = newEntry;
            return false;
        }
    }
}
