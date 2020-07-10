# Graph

## Graph API

```java
public class Graph{
    Graph(int V)                 // Create an empty graph with V vertices
    Graph(In in)                 // Create a graph from input stream
    void addEdge(int v, int w)   // Add an edge v to w
    Iterable<Integer> adj(int v) // Vertices addjacent to v
    int V()                      // Number of vertices
    int E()                      // Number of edges
}
```

## Graph Representation

> 1. List of Edge
> 
> 2. Adjacency - Matrix
> 
> 3. Adjacency - List

| representation   | space | add edge | edge between v and w? | iterate over vertices adjacent to v |
|:----------------:|:-----:|:--------:|:---------------------:|:-----------------------------------:|
| adjacency matrix | $V^2$ | 1        | 1                     | V                                   |
| adjacency lists  | E + V | 1        | degree(v)             | degree(v)                           |

### Adjacency-list graph representation (Directed graph)

**implement**

```java
public class Graph{
    private final V;
    private Bag<Integer>[] adj;
    public Graph(int v){
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v=0; v<V; v++){
            adj[v] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w){
        //無向圖
        adj[v].add(w);
        adj[w].add(v);
    }
}
```

## Depth-first Search

> **Goal.** Systematically traverse a graph.
> 
> **Idea.** Mimic maze exploration

**implement**

```java
public class DepthFirstPaths{
    private boolean[] marked;
    private int[] edgeTo;        //previous vertex on path from s to v
    private int s;
    public DepthFirstPaths(Graph G, int s){ // s is the start vertex
        marked = new boolean[G.V()]; //V() is the amount of vertex
        edgeTo = new int[G.V()];     //用來記錄走訪的順序(之前資結是直接印出)
        dfs(G, s);
    }

    private DepthFirstPaths(Graph G, int v){
        marked[v] = true;
        //Find the edge which is adjacency to 
        for(int w: G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
                edgeTo[w] = v;
                /* w 接到 v (雖然我們是藉由v找到w) */
            }
        }
    }
}
```

## Breadth-first Search

```java
public class BreadthFirstPaths{
    private boolean[] marked;
    private int[] edgeTo;    //edgeTo[x] = y 表示 y 走向 x
    private int[] distTo;    //distTo[x] 表示走訪到的 x 是第幾層的

    private void bfs(Graph C, int s){
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        marked[s] = true;
        distTo[s] = 0;

        while(!q.empty()){
            int v = q.dequeue();
            for(int w : G.adj(v)){
                if(!marked[w]){
                    q.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }        
    }

    public boolean hasPathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
    }

    public Iterable<Integer> PathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x=v; x!=s; x=edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
```

## Connectivity queries

> **Def.** Vertices v and w are connected if there is a path between them.
> 
> **Goal.** Preprocess graph to answer queries of the form *is v connected to w?* in constant time.

```java
public class CC{
    CC(Graph G)                     //Find connected components in G
    boolean connected(int v, int w) //Are v and w connected?
    int count()                     //Number of connected components
    int id(int v)                   //Compotent identifier for v
                                    //between 0 and count()-1
}
```

> 以 DFS 進行實作
> 
> 以 Union find 實作較慢，但以 Union Find 實作有一個好處，Union Find 是動態規劃的，所以如果算到一半臨時有點要加入，會非常容易接著運算，這一點是 DFS 做不到的

```java
public class CC{
    private boolean[] marked;
    private int[] id;
    private int count;    // 計算有幾個 connected components

    public CC(Graph){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int v=0; v<G.V(); v++){
            if(!marked[v]){
                dfs(G, V);
                count++;    //只有在一次 dfs 走不完的情況下才將 count+1
            }
        }
    }

    public int count(){
        return count;
    }

    public int id(int v){
        return id[v];
    }

    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;    //把這點的 id 加入 count 裡面
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
    }
}
```

### Union Find vs. DFS

| Method              | Preprocessing time | query time | space |
|:-------------------:|:------------------:|:----------:|:-----:|
| weighted union-find | $Elog^*V$          | $log^*V$   | $V$   |
| DFS                 | $E$                | $1$        | $E+V$ |

> 在預處理方面
> 
> Union Find 訪問每個邊並利用 find() 的方法把點與其母點相連
> 
> find 一次大約小於 logV ，而因為每個邊到要訪問所以是 ElogV
> 
> DFS 只要把每個 Edge 都走過就知道誰相通相不相通
> 
> 在 query 方面 (查尋兩點是否 connected 時)
> 
> union find 要做 find 的動作所以會大致小於 logV
> 
> DFS 因為在預處理時就把相通的點規類到同一個 id 裡面了，所以只要一個 == 就能比對
> 
> 至於 space 方面
> 
> union-find 僅需一個長度為頂點數 V 的陣列
> 
> 而 DFS (以 adjacency list 實作) 需要 E+V 長度的陣列

## Cycle Detection

**Directed Cycle Detection Algorithm**

```java
public class DirectedCycle{
    private boolean[] marked;        //記錄哪個點有走過 (避免重覆 dfs)
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] OnStack;       //記錄我走訪時的路徑 (避免特殊情況)

    // 在無向圖中要偵測 cycle 只要偵測走訪到的點是不是已經走訪過就行了
     // 但在有向圖中卻不行這樣實作，考慮下列情況

    // 比如我已經拜訪過 1234 但 4 不在我中途經過的路徑上所以不是 cycle
    // 1 → 4
    // ↓   ↑
    // 2 → 3

    // 1, 4       marked[1,0,0,1]
    // 重置 onStack(程式會自動重置)
    // 1, 2       marked[1,1,0,1]  onStack[1,1,0,0]
    // 1, 2, 3    marked[1,1,1,1]  onStack[1,1,1,0]
    // 1, 2, 4, 4 mraked[1,1,1,1]  onStack[1,1,1,0]
        public DirectedCycle(Graph G){
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for(int v=0; v<G.V(); V++){
            if(!marked[v]){
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v){
        onStack[k] = true;
        marked[k]  = true;
        for(int : G.adj(v)){
            if(this.hasCycle()){
                return;
            }else if(!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }else if(onStack[w]){
                cycle = new Stack<Integer>();
                for(int x = v; x!=w; x=edgeTo[x]){
                    cycle.push(x);
                }        
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle!=null;
    }

    public Iterable<Integer> Cycle(){
        return cycle;
    }
}
```

## Topological Sort

### Depth-first search orders

**Observation.** DFS visits each vertex exactly once. The order in which it does so can be important.

**Orderings.**

+ Preorder: order in which dfs() called

+ Postorder: order in which dfs() return

+ Reverse Postorder: reverse order in which dfs() returns

```java
private void dfs(Graph g, int v){
    marked[v] = true;
    preorder.enqueue(v);        //原先dfs順序
    for(int w : g.adj(v)){
        if(!marked[w]){
            dfs(g, w);
        }
    }
    postorder.enqueue(v);        //反向的順序寫入
    reversPostorder.push(v);    //反向的順序寫入 (但輸出時會再反向回來)
}
```

> 解釋：
> 
> 假如正解是 a→b→c→d→e→f
> 
> 我們從 c 開始做走訪 會先走訪到 c→d→e→f (dfs 答案順序)，而後走訪到 a→b
> 
> 我們要怎麼拼湊這兩個走訪過程使其最後印出正確答案呢？
> 
> 首先我們將兩者都先倒轉變成 f→e→d→c 及 b→a
> 
> 將這兩個合併 f→e→d→c→b→a 再倒轉回來就是原先的正解了

**implementation**

```java
public class DepthFirstOrder{
    private boolean[] marked;
    private Stack<Integer> reversePostorder;

    public DepthFirstOrder(Digraph G){
        reversePostorder = new Stack<Integer>();
        marked = new boolean(G.V());
        for(int v=0; v<G.V(); v++){
            if(!marked[v]){
                dfs(G,v);
            }
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
        reversePostorder.push(v);
    }
}
```

### Topological sort in a DAG: correctness proof

**Proposition.** Reverse DFS postorder of DAG is a topological order.

**Pf.** Consider any edge v → w. <u>When dfs(v) is called</u>:

+ Case 1: dfs(w) has already been called and returned. Thus, <u>w was done before v</u>.

+ Case 2: dfs(w) has not yet been called. 
  
  dfs(w) will get called directly or indirectly by dfs(v) will finish before dfs(v). Thus, <u>w will be done before v</u>.

+ Case 3: dfs(w) has already been called, but has not yet returned. <u>Can't happen</u> in a DAG: function call stack contains path from w to v. so v→w would complete a cycle.

拓撲排序

+ <u>排序不是唯一</u>

+ <u>起始點並不影響你的結果</u>
