// smart detect upper bound
// use long int to simulate boolean
// optimization

public class MPI4107056019 extends MPI{
    public int min(int[] array){
        int len = array.length;

        long[] A = new long[(len >> 6) + 2];
        A[0] = 1L;

        for(int i : array){
            if(i<0 || i>len) continue;
            A[i >> 6] |= (1L << (i & 63));
        }

        int index = -1;
        while(A[++index] == -1L);

        int ans = index << 6;

        long num = A[index];

        if((num & 0xffffffff) == 0xffffffff){
            ans += 32;
            num >>>= 32;
        }

        if((num & 0xffff) == 0xffff){
            ans += 16;
            num >>>= 16;
        }

        if((num & 0xff) == 0xff){
            ans += 8;
            num >>>= 8;
        }

        while((num & 0x1) == 0x1){
            ans += 1;
            num >>>= 1;
        }

        return ans;
    }
}
