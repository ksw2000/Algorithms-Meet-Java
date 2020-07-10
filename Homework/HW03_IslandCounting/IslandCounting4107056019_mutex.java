public class IslandCounting4107056019_mutex extends IslandCounting{
	final int num_thread = 16;
	final int num_thread_log = 4;
	public int count(String[] A, String[] B){
		int len = A.length;
		int index = 0;
		int indexA, indexB;
		MyHashMap m = new MyHashMap(len << 1);
		MyHashMap.Entry hmeA, hmeB;
		UnionFind uf = new UnionFind(len);
		int[][] union = new int[2][len];
		Mutex mutex = new Mutex();

		Thread[] t = new Thread[num_thread];

		if(len > num_thread){
			for(int j=0; j<num_thread; j++){
				final int start = ((len * j) >> num_thread_log);
				final int end = ((len * (j+1)) >> num_thread_log);
				for(int i=start; i<end; i++){
					hmeA = m.get(A[i]);
					if(hmeA == null){
						m.put(A[i], index);
						union[0][i] = index++;
					}else{
						union[0][i] = hmeA.val;
					}

					hmeB = m.get(B[i]);
					if(hmeB == null){
						m.put(B[i], index);
						union[1][i] = index++;
					}else{
						union[1][i] = hmeB.val;
					}
				}

				t[j] = new Thread(new Runnable(){
					public void run(){
						mutex.lock();
						for(int k=start; k<end; k++){
							uf.union(union[0][k], union[1][k]);
						}
						mutex.unlock();
					}
				});
				t[j].start();
			}

			try{
				t[num_thread-1].join();
			}catch(InterruptedException e){}
		}else{
			for(int i=0; i<len; i++){
				hmeA = m.get(A[i]);
				if(hmeA == null){
					indexA = index++;
					m.put(A[i], indexA);
				}else{
					indexA = hmeA.val;
				}

				hmeB = m.get(B[i]);
				if(hmeB == null){
					indexB = index++;
					m.put(B[i], indexB);
				}else{
					indexB = hmeB.val;
				}
				uf.union(indexA, indexB);
			}
		}

		return uf.howManyHead(m.len);
	}

	private class UnionFind{
		private int[] unionFindArray;
		private int[] unionFindArrayWeight;
		private int cap;
		private int count;

		UnionFind(int size){
			this.cap = size;
			unionFindArray = new int[cap];
			unionFindArrayWeight = new int[cap];

			for(int i=0; i < cap; i++)
				unionFindArray[i] = i;
		}

		final public int find(int i){
			while(unionFindArray[i] != i){
				unionFindArray[i] = unionFindArray[unionFindArray[i]];
				i = unionFindArray[i];
			}
			return i;
		}

		final public void union(int a, int b){
			if(a>=cap || b>=cap){
				resize(cap << 1);
			}
			int aroot = find(a);
			int broot = find(b);
			if(aroot == broot) return;
			++count;
			if(unionFindArrayWeight[aroot] > unionFindArrayWeight[broot]){
				unionFindArray[broot] = aroot;
			}else{
				unionFindArray[aroot] = broot;
				if(unionFindArrayWeight[aroot] == unionFindArrayWeight[broot]){
					++unionFindArrayWeight[broot];
				}
			}
		}

		final public int howManyHead(int end){
			return end-count;
		}

		final private void resize(int size){
			int[] newUnionFindArray = new int[size];
			int[] newUnionFindArrayWeight = new int[size];
			System.arraycopy(unionFindArray, 0, newUnionFindArray, 0, cap);
			System.arraycopy(unionFindArrayWeight, 0, newUnionFindArrayWeight, 0, cap);
			for(int i=cap; i<size; i++){
				newUnionFindArray[i] = i;
			}
			this.unionFindArray = newUnionFindArray;
			this.unionFindArrayWeight = newUnionFindArrayWeight;
			this.cap = size;
		}
	}

	private class MyHashMap{
		class Entry{
			public String key;
			public int val;
			public Entry next;
		}

		int len,cap;
		Entry[] list;

		MyHashMap(int size){
			len = 0;
			cap = size;

			Entry[] newEntry = new Entry[this.cap];
			this.list = newEntry;
		}

		final private int hashCode(String key){
			return (key.hashCode() & 0x7fffffff) % this.cap;
		}

		final public void put(String key, int val){
			if(this.len > this.cap){
				int oldcap = this.cap;
				Entry[] temp = this.list;

				this.cap <<= 1;

				Entry[] newEntry = new Entry[this.cap];
				this.list = newEntry;

				Entry current, next;
				int index;
				for(int i=0; i<oldcap; i++){
					for(current = temp[i]; current!=null; current=next){
						next = current.next;

						index = hashCode(current.key);
						current.next = newEntry[index];
						newEntry[index] = current;
					}
				}
			}
			this.len++;

			int index = hashCode(key);
			Entry newEntry = new Entry();
			newEntry.key = key;
			newEntry.val = val;
			newEntry.next = list[index];

			list[index] = newEntry;
		}

		final public Entry get(String key){
			int index = hashCode(key);
			for(Entry current = list[index]; current!=null; current=current.next){
				if(current.key.equals(key)){
					return current;
				}
			}
			return null;
		}
	}

	public final class Mutex {
	    private long locks = 0;
	    private Thread owner = null;
	    public synchronized void lock() {
	        Thread me = Thread.currentThread();
	        while (locks > 0 && owner != me) {
	            try {
	                wait();
	            } catch (InterruptedException e) {
	            }
	        }
	        // locks == 0 || owner == me
	        owner = me;
	        locks++;
	    }
	    public synchronized void unlock() {
	        Thread me = Thread.currentThread();
	        if (locks == 0 || owner != me) {
	            return;
	        }
	        // locks > 0 && owner == me
	        locks--;
	        if (locks == 0) {
	            owner = null;
	            notifyAll();
	        }
	    }
	}
}
