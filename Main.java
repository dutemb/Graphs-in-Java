import java.util.*;
public class Main {

    // Run "java -ea Main" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)
    
    public static void test1() {
	Graph g = new ListGraph();
	boolean as = g.addNode("b");
	boolean b = g.addNode("b");
	//System.out.println(as);
	//System.out.println(b);
	assert g.addNode("a");
	assert g.hasNode("a");
    }
	public static void testNodes(){
		Graph g = new ListGraph();
		g.addNode("b");
	    g.addNode("a");
		//printGraph(g);
		//printGraph(b);
		Graph b = new ListGraph();
		for(String key : g.nodes()){
			System.out.println(key);
		}
	}
	public static void testAddEdge(){
		Graph g = new ListGraph();
		g.addNode("b");
	    g.addNode("a");
		g.addNode("d");
		assert g.addEdge("a","b");
		assert !g.addEdge("a","b"); //should be false because edge already added
		assert g.hasEdge("a","b");
		assert !g.hasEdge("b","a");
		assert !g.hasEdge("a","d");
		//assert !g.addEdge("a","c"); raises No Such Element
	}

	public static void testRemoveEdge(){
		Graph g = new ListGraph();
		g.addNode("b");
	    g.addNode("a");
		g.addNode("d");
		assert g.addEdge("a","b");
		assert g.addEdge("a","d"); 
		assert g.removeEdge("a","b");
		assert !g.hasEdge("a","b");
		//assert g.removeEdge("a","h"); raises Exception
	}
	

	public static void testSucc(){
	   	Graph g = new ListGraph();
		g.addNode("b");
	    g.addNode("a");
		g.addNode("d");
		g.addNode("h");
		assert g.addEdge("a","b");
		assert g.addEdge("a","d");
        for(int i=0;i<g.succ("a").size();i++){
			System.out.println(g.succ("a").get(i));
		}
	}

	public static void union_test() {
		Graph h = new ListGraph();
		h.addNode("a");
		h.addNode("d");
		assert(h.addEdge("a","d"));
		h.addEdge("d", "a");
		h.addNode("e");

		Graph g = new ListGraph();
		assert (g.addNode("a")); 
		assert (g.addNode("b")); 
		assert (g.addNode("c"));
		assert (g.addEdge("a","b"));
		assert (g.addEdge("c","b"));
		Graph returned = g.union(h);
		assert(returned.hasNode("a"));
		assert(returned.hasNode("b"));
		assert(returned.hasNode("c"));
		assert(returned.hasNode("d"));
		assert(returned.hasNode("e"));
		assert(returned.hasEdge("a", "d")); //NOT WORKING
		assert(returned.hasEdge("d", "a"));
		assert(returned.hasEdge("a", "b"));
		assert(returned.hasEdge("c", "b"));

	}

	public static void testSub(){
		Graph g = new ListGraph();
		assert (g.addNode("a")); 
		assert (g.addNode("b")); 
		assert (g.addNode("c"));
		assert (g.addNode("d"));
		assert (g.addEdge("a","b"));
		assert (g.addEdge("d","b"));
		
		Set<String> hash_Set = new HashSet<String>();
		hash_Set.add("Geeks");
		hash_Set.add("a");
		hash_Set.add("b");

		Graph n = g.subGraph(hash_Set);
		for(String key : n.nodes()){
			System.out.println(key);
		}
		assert (n.hasEdge("a","b"));
        assert (!n.hasEdge("d","b"));
		assert (!n.hasNode("Geeks"));
	}

		public static void testPred(){
		Graph g = new ListGraph();
		g.addNode("b");
	    g.addNode("a");
		g.addNode("d");
		g.addNode("h");
		assert g.addEdge("a","b");
		assert g.addEdge("d","b");
        for(int i=0;i<g.pred("b").size();i++){
			System.out.println(g.pred("b").get(i));
		}
	}

   public static void testBfs(){
	   Graph g = new ListGraph();
		assert (g.addNode("a")); 
		assert (g.addNode("b")); 
		assert (g.addNode("c"));
		assert (g.addNode("d"));
		assert (g.addNode("k"));
		assert (g.addNode("l"));
		assert (g.addEdge("a","b"));
		assert (g.addEdge("a","l"));
		assert (g.addEdge("b","d"));
		assert (g.addEdge("d","k"));
		boolean b = g.connected("a","a");
		System.out.println(b);
		assert !(g.connected("b","l"));
   }

    public static void test2() {
	Graph g = new ListGraph();
	EdgeGraph eg = new EdgeGraphAdapter(g);
	Edge e = new Edge("a", "b");
	Edge n = new Edge("a", "m");
	Edge k = new Edge("a", "j");
	Edge r = new Edge("l", "j");
	assert eg.addEdge(e);
	assert eg.addEdge(n);
	assert eg.addEdge(k);
	assert eg.addEdge(r);
	Graph gr = new ListGraph();
    EdgeGraph em = new EdgeGraphAdapter(gr);
	assert eg.hasEdge(e);
	List<Edge> ng = eg.edges();
	List<Edge> kg = em.outEdges("a");
	List<Edge> og = eg.inEdges("j");
    }

	public static void testUnionTwo() {
	Graph g = new ListGraph();
	EdgeGraph eg = new EdgeGraphAdapter(g);
	Edge e = new Edge("a", "b");
	Edge n = new Edge("a", "m");
	Edge k = new Edge("a", "j");
	Edge r = new Edge("l", "j");
	assert eg.addEdge(e);
	assert eg.addEdge(n);
	assert eg.addEdge(k);
	assert eg.addEdge(r);
    
	Graph nk = new ListGraph();
	Graph vk = new ListGraph();
	EdgeGraph rip = new EdgeGraphAdapter(nk);
	EdgeGraph bp = new EdgeGraphAdapter(vk);
	EdgeGraph ok = new EdgeGraphAdapter(nk);
	Edge lo = new Edge("a", "b");
	Edge clo = new Edge("k", "v");
	Edge pr = new Edge("lof", "top");
	Edge ro = new Edge("crop", "prob");
	assert ok.addEdge(lo);
	assert ok.addEdge(clo);
	assert ok.addEdge(pr);
	assert ok.addEdge(ro);
	EdgeGraph pup = ok.union(eg);


	assert pup.hasEdge(pr);
	assert pup.hasEdge(r);
    }
	
    public static void testPath(){
    Graph g = new ListGraph();
	EdgeGraph eg = new EdgeGraphAdapter(g);
	Edge e = new Edge("a", "b");
	Edge n = new Edge("a", "m");
	Edge k = new Edge("b", "j");
	Edge r = new Edge("j", "k");
	Edge rp = new Edge("op", "pro");
	assert eg.addEdge(e);
	assert eg.addEdge(n);
	assert eg.addEdge(k);
	assert eg.addEdge(r);
	List<Edge> gr = new ArrayList<Edge>();
	gr.add(e);
	gr.add(k);
	gr.add(r);
	boolean lop = eg.hasPath(gr);
	System.out.println(lop);
	}


    public static void main(String[] args) {
	test1();
	testNodes();
	testAddEdge();
	testRemoveEdge();
	testSucc();
	testPred();
	union_test();
	testSub();
    test2();
	testBfs();
	testUnionTwo();
	testPath();

    }
	

}