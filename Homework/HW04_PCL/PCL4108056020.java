public class PCL4108056020 extends PCL{
	class Slot{ // class for saving slope of the pair as linked list
		final double SLOPE; final Slot NEXT;
		public Slot(final double slope, final Slot next)
		{ this.SLOPE = slope; this.NEXT = next; }
	}
	final byte CORE = 5; // Binary logarithm of launch thread number
	final Slot[][] HashTable = new Slot[1<<CORE][]; // Initialize 2^5=32 hash table
	final Thread[] T = new Thread[HashTable.length-1]; // Initialize 32-1=31 threads, one for main thread
	volatile boolean result; // the result value. modifier volatile let us not to care synchronous problem

	public boolean checkPCL(int[][] A){
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
						final double SLOPE = (A[i][1]-A[j][1])/(double)(A[i][0]-A[j--][0]);

						// hash for ONLY power-of-2-number: same as (hashcode&0x7fff_ffff)%BUCKETNUM
						// for keep the hash index in [0,BUCKETMUN-1]
						bucket = Double.hashCode(SLOPE)&B;

						// view through slots of the bucket, hash collisions occur if Solt have NEXT.
						for(Slot pivot = HashTable[t][bucket]; pivot != null; pivot = pivot.NEXT){
							// find three points in the same line
							if(pivot.SLOPE == SLOPE) result = true;

							// if other thread(s) or this thread found, end up the thread.
							// the same instruction below is for the same reason.
							if(result) return;
						}

						// put slope into the foremost slot of the bucket
						HashTable[t][bucket] = new Slot(SLOPE, HashTable[t][bucket]);

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
				final double SLOPE = (A[i][1]-A[j][1])/(double)(A[i][0]-A[j--][0]);
				bucket = Double.hashCode(SLOPE)&B;
				for(Slot pivot = HashTable[TLEN][bucket]; pivot != null; pivot = pivot.NEXT)
					if(pivot.SLOPE == SLOPE || result)
						return true;
				HashTable[TLEN][bucket] = new Slot(SLOPE, HashTable[TLEN][bucket]);
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
