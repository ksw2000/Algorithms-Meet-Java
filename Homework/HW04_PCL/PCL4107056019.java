public class PCL4107056019 extends PCL{
	class Slot{ // class for saving slope of the pair as linked list
		final double slope; final Slot Next;
		public Slot(final double slope, final Slot Next)
		{ this.slope = slope; this.Next = Next; }
	}
	final byte CORE = 5; // Binary logarithm of launch thread number
	final Slot[][] HashTable = new Slot[1<<CORE][]; // Initialize 2^5=32 hash table
	final Thread[] T = new Thread[HashTable.length-1]; // Initialize 32-1=31 threads, one for main thread
	volatile boolean result; // the result vaiue. modifier volatile let us not to care synchronous problem

    public boolean checkPCL(int[][] array){
		result = false; // reset the value, prevent same object-method calling

		final int AEND = A.length-1, TLEN = T.length, FREQ = HashTable.length,
				// get the closest power-of-2-number bigger than A.length, BUCKETS = 2^k, k in [0,32]
				BUCKETNUM = 1<<32-Integer.numberOfLeadingZeros(AEND), B = BUCKETNUM-1;

		for(byte ti=0; ti<TLEN; ++ti){ // start up all threads
			final byte t = ti; // the variables pass to anonymous class must be final
			T[t] = new Thread(()->{ // lambda expression for Runnable interface since Java 8
				// start of each thread(algorithm same as below)
				// threading by frequency: e.g. 0,32,64... 1,33,65... 2,34,66... ......
				for(int i=AEND-t, j, bucket; i>-1; i-=FREQ){
					// (re-)set buckets for each i
					for(HashTable[t] = new Slot[BUCKETNUM], j=i-1; j>-1;){
						// calculate the slope of the line construct by point i and j
						final double slope = (A[i][1]-A[j][1])/(double)(A[i][0]-A[j--][0]);

						// modulo for ONLY power-of-2-number: same as (hashcode&0x7fff_ffff)%BUCKETNUM
						// for keep the hash index in [0,BUCKETMUN]
						bucket = Double.hashCode(slope)&B&0x7fff_ffff;

						// view through slots of the bucket, hash collisions occur if Solt have Next.
						for(Slot Pivot = HashTable[t][bucket]; Pivot != null; Pivot = Pivot.Next){
							// find three points in the same line
							if(Pivot.slope == slope) result = true;

							// if other thread(s) or this thread found, end up the thread.
							// the same instruction below is for the same reason.
							if(result) return;
						}

						// put slope into the foremost slot of the bucket
						HashTable[t][bucket] = new Slot(slope, HashTable[t][bucket]);

						if(result) return;
					}
					if(result) return;
				}
				// end of each thread
			});
			T[t].setDaemon(true); // let threads end up while main thread return ture
			T[t].start(); // launch threads
			if(result) return true;
		} // end of start up threads

		// start algorithm to main thread
		for(int i=AEND-TLEN, j, bucket; i>-1; i-=FREQ){
			for(HashTable[TLEN] = new Slot[BUCKETNUM], j=i-1; j>-1;){
				final double slope = (A[i][1]-A[j][1])/(double)(A[i][0]-A[j--][0]);
				bucket = Double.hashCode(slope)&B&0x7fff_ffff;
				for(Slot Pivot = HashTable[TLEN][bucket]; Pivot != null; Pivot = Pivot.Next)
					if(Pivot.slope == slope || result)
						return true;
				HashTable[TLEN][bucket] = new Slot(slope, HashTable[TLEN][bucket]);
			}
			if(result) return true;
		}
		// end of algorithm in main thread

		try{
			for(final Thread t : T){ // join all threads
				if(result) return true;
				t.join();
			}
		}catch(final InterruptedException e){}
		return result;
    }
}
