public class LSPath4107056019 extends LSPath{
    public int Ans(int[][] array){
        Graph g = new Graph(array.length + 1);

        for(int[] v: array){
            g.append(v[0], v[1]);
        }

        return g.findMaxDepth()-2;
    }

    class Set{
        private int _cap;   // _cap = cap - 1
        private Element[] list;

        Set(int cap){
            this._cap = (1 << (int)(Math.log(cap) / Math.log(2) + 1)) - 1;
            this.list = new Element[this._cap + 1];
        }

        class Element{
            int key;
            int val;
            Element next;
        }

        private int hashcode(int n){
            return n & _cap;
        }

        void put(int k, int v){
            Element newE = new Element();
            newE.key = k;
            newE.val = v;
            newE.next = list[hashcode(k)];
            list[hashcode(k)] = newE;
        }

        int get(int n){
            for(Element c = list[hashcode(n)]; c!=null; c=c.next){
                if(c.key == n) return c.val;
            }
            return 0;
        }

        boolean contain(int n){
            for(Element c = list[hashcode(n)]; c!=null; c=c.next){
                if(c.key == n) return true;
            }
            return false;
        }
    }

    class HashAdjList{
        private int _cap;   // _cap = cap - 1
        private int maxDegree;
        public  int maxDegreeNode;
        Element[] list;

        HashAdjList(int cap){
            this._cap = (1 << (int)(Math.log(cap) / Math.log(2) + 1)) - 1;
            this.list = new Element[this._cap + 1];
            this.maxDegree = -1;
            this.maxDegreeNode = -1;
        }

        class Element{
            int key;
            int degree;
            ArrayListLite val; // sub node
            Element next;
        }

        private final int hashcode(int n){
            return n & _cap;
        }

        final void put(int n, int subnode){
            for(Element c = list[hashcode(n)]; c!=null; c=c.next){
                if(c.key == n){
                    if(++c.degree > maxDegree){
                        maxDegree = c.degree;
                        maxDegreeNode = n;
                    }
                    c.val.add(subnode);
                    return;
                }
            }

            Element newE = new Element();
            newE.key = n;
            newE.val = new ArrayListLite();
            newE.val.add(subnode);
            newE.next = list[hashcode(n)];
            list[hashcode(n)] = newE;
        }

        final ArrayListLite get(int n){
            for(Element c = list[hashcode(n)]; c!=null; c=c.next){
                if(c.key == n) return c.val;
            }
            return null;
        }
    }

    class Graph{
        private HashAdjList adjList;
        private FiniteQueue queue;
        private int maxDepth;
        private int maxDepthNode;
        private int len;

        Graph(int len){
            this.len = len;
            this.maxDepth = 0;
            adjList = new HashAdjList(len << 1);
            queue = new FiniteQueue(len);
        }

        final void append(int a, int b){
            adjList.put(a, b);
            adjList.put(b, a);
        }

        final int findMaxDepth(){
            //Find the most depth node from "center node", which has the most degree
            BFSmark(adjList.maxDegreeNode);
            //Find the most depth
            BFSmark(this.maxDepthNode);
            return this.maxDepth;
        }

        final void BFSmark(int a){
            Set depth = new Set(this.len);

            depth.put(a, 1);
            //FiniteQueue has been initialized when Graph() was constructed
            queue.enqueue(a);

            while(!queue.empty()){
                a = queue.dequeue();
                int self_depth = depth.get(a) + 1;
                if(self_depth > maxDepth){
                    maxDepth = self_depth;
                    maxDepthNode = a;
                }

                ArrayListLite arr = adjList.get(a);
                for(arr.read(); arr.hasNext();){
                    int n = arr.next();
                    if(!depth.contain(n)){
                        depth.put(n, self_depth);
                        queue.enqueue(n);
                    }
                }
            }
        }
    }

    //insecure
    class FiniteQueue{
        private int _cap;
        private int head;
        private int tail;
        private int[] list;

        FiniteQueue(int cap){
            this._cap = (1 << (int)(Math.log(cap) / Math.log(2) + 1)) - 1;
            this.list = new int[_cap + 1];
            head = 0; //dequeue
            tail = 0; //enqueue
        }

        public final void enqueue(int n){
            list[tail] = n;
            tail = (tail + 1) & _cap;
        }

        public final int dequeue(){
            int ret = list[head];
            head = (head + 1) & _cap;
            return ret;
        }

        public final boolean empty(){
            return head == tail;
        }
    }

    public class ArrayListLite{
        private int cap;
        private int len;
        private int[] elem;
        private int pointer;

        ArrayListLite(){
            this.cap = 16;
            this.len = -1;
            this.elem = new int[cap];
        }

        public final void add(int n){
            if(++this.len != this.cap){
                this.elem[this.len] = n;
            }else{
                this.cap <<= 1;
                int[] newElem = new int[this.cap];
                for(int i=0; i<len; i++){
                    newElem[i] = elem[i];
                }
                newElem[this.len] = n;
                this.elem = newElem;
            }
        }

        public final void read(){
            this.pointer = 0;
        }

        public final boolean hasNext(){
            return pointer <= len;
        }

        public final int next(){
            return elem[pointer++];
        }
    }
}
