import java.util.*;

public class ListGraph implements Graph {
    private HashMap<String, LinkedList<String>> nodes = new HashMap<>();
    //private LinkedList<String> allNodes = new LinkedList<String>;

    public boolean addNode(String n) {
	   //  throw new UnsupportedOperationException();
        if(nodes.containsKey(n)) return false;
        nodes.put(n, new LinkedList<String>());
        return true;
    }

    public boolean addEdge(String n1, String n2) {
        if(!hasNode(n1) || !hasNode(n2))
            throw new NoSuchElementException();
        if(!hasEdge(n1,n2)){
            nodes.get(n1).add(n2);
            return true;
        }
        return false;
    }

    public boolean hasNode(String n) {
	    if(nodes.containsKey(n)) return true;
        return false;
    }

    public boolean hasEdge(String n1, String n2) {
        if(!hasNode(n1) || !hasNode(n2)) return false;
	    if(nodes.get(n1).contains(n2)) return true;
        return false;
    }

    public boolean removeNode(String n) {
        if(hasNode(n)){
          nodes.remove(n);
          return true;
        }
         return false;
    }

    public boolean removeEdge(String n1, String n2) {
	     if(!hasNode(n1) || !hasNode(n2))
            throw new NoSuchElementException();
         if(hasEdge(n1,n2)){
            nodes.get(n1).remove(n2);
            return true;
         }
         return false;
    }

    public List<String> nodes() {
         LinkedList<String> returned = new LinkedList<String>();
         for (String n : nodes.keySet()) { 
                 returned.add(n); 
         }
         return returned;
         
    }

    public List<String> succ(String n) {
	    if(!hasNode(n)){
           throw new NoSuchElementException();
        }
        return nodes.get(n);

    }

    public List<String> pred(String n) {
	    if(!hasNode(n)){
           throw new NoSuchElementException();
        }
        List<String> returned = new LinkedList<String>();
        for (String k : nodes.keySet()) { 
                if(hasEdge(k,n))
                  returned.add(k);
         }
         return returned;
    }

    public Graph union(Graph g) {
	    Graph returned = new ListGraph();
        for (String s : nodes.keySet()) { 
              returned.addNode(s);
         }
        List<String> listG = g.nodes();
        for (int l=0; l<listG.size();l++) { 
            returned.addNode(listG.get(l));
        }
        for (int j=0; j< listG.size();j++) { 
              for(int m=0; m<g.succ(listG.get(j)).size();m++){
                  returned.addEdge(listG.get(j),g.succ(listG.get(j)).get(m));
              }
         }
        for (String s : nodes.keySet()) { 
              for(int f=0; f<nodes.get(s).size();f++){
                  returned.addEdge(s,nodes.get(s).get(f));
              }
         }
       
         return returned;

    }


    public Graph subGraph(Set<String> nodes) {
        //add nodes
         Graph returned = new ListGraph();
           for(String m : nodes){
             for(String k : nodes()){
                  if(k == m){
                      returned.addNode(k);
                  }
             }
           }
         
        //add edges
         for (String d : returned.nodes()) { 
            for(int i = 0; i<succ(d).size();i++){
                if(returned.hasNode(succ(d).get(i)) && hasEdge(d,succ(d).get(i)) ){
                    returned.addEdge(d,succ(d).get(i));
                }
            }
         }
         return returned;
    }

    public boolean connected(String n1, String n2) {
        if(!hasNode(n1) || !hasNode(n2))
            throw new NoSuchElementException();
         if(n1.equals(n2)){
             return true;
         }
	     return bfs(n1,n2);
    }
    private boolean bfs(String n1, String n2){
       Set<String> visited= new HashSet<String>();
       LinkedList<String> queue = new LinkedList<String>();
       LinkedList<String> path = new LinkedList<String>();
       queue.addLast(n1);

       while(!queue.isEmpty()){
          String top = queue.peek();
          queue.removeFirst();
          visited.add(n1);
          path.addLast(top);
          for(String k : succ(top)){
              if(!visited.contains(k)){
                 path.addLast(k);
                 visited.add(k);
                 queue.addLast(k);
                 if(k == n2){
                   return true;
                 }
              }
          }

       }
       return false;
    }

    
}
