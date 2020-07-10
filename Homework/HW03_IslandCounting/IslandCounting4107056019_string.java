public class IslandCounting4107056019_string extends IslandCounting{
	public int count(String[] A, String[] B){
		int len = A.length;
		int index = 0;
		int indexA, indexB;
		MyHashMap m = new MyHashMap(len<<1);
		UnionFind uf = new UnionFind(len<<1);

		for(int i=0; i<len; i++){
			indexA = m.get(A[i]);
			if(indexA == -1){
				indexA = index++;
				m.put(A[i], indexA);
			}

			indexB = m.get(B[i]);
			if(indexB == -1){
				indexB = index++;
				m.put(B[i], indexB);
			}

			uf.union(indexA, indexB);
		}

		return uf.howManyIsland(index);
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
			a = find(a);
			b = find(b);

			if(a == b) return;
			count++;
			if(unionFindArrayWeight[a] > unionFindArrayWeight[b]){
				unionFindArray[b] = a;
				unionFindArrayWeight[a] += 1 + unionFindArrayWeight[b];
			}else{
				unionFindArray[a] = b;
				unionFindArrayWeight[b] += 1 + unionFindArrayWeight[a];
			}
		}

		final public int howManyIsland(int end){
			return end-count;
		}
	}

	private class MyHashMap{
	    class Entry{
	        public String key;
	        public int val;
	        public Entry next;
	    }

	    private int cap;
	    private Entry[] list;

	    MyHashMap(int size){
	        this.cap = size;
	        Entry[] newEntry = new Entry[this.cap];
	        this.list = newEntry;
	    }

		final public void put(String key, int val){
	        int index = (key.hashCode() & 0x7fffffff) % this.cap;
	        Entry newEntry = new Entry();
	        newEntry.key = key;
	        newEntry.val = val;
	        newEntry.next = list[index];
	        list[index] = newEntry;
	    }

	    final public int get(String key){
	        for(Entry current = list[(key.hashCode() & 0x7fffffff) % this.cap]; current!=null; current=current.next){
	            if(current.key.equals(key)){
	                return current.val;
	            }
	        }
	        return -1;
	    }
	}
}
