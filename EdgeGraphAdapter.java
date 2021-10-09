import java.util.*;

public class EdgeGraphAdapter implements EdgeGraph {

    private Graph g;

    EdgeGraphAdapter(Graph g) { this.g = g; }

    public boolean addEdge(Edge e) {
      if(!g.hasNode(e.getSrc())){
        g.addNode(e.getSrc());
      }
      if(!g.hasNode(e.getDst())){
        g.addNode(e.getDst());
      }
      if(hasEdge(e)) return false;

	     g.addEdge(e.getSrc(),e.getDst());
       return true;
    }

    public boolean hasNode(String n) {
	     if(g.hasNode(n)) return true;
      return false;
    }

    public boolean hasEdge(Edge e) {
	    if(g.hasEdge(e.getSrc(),e.getDst())) return true;
      return false;
    }

    public boolean removeEdge(Edge e) {
        if(!hasEdge(e)) return false;
	      return g.removeEdge(e.getSrc(), e.getDst());
    }

    public List<Edge> outEdges(String n) {
      
      List<Edge> returned = new LinkedList<Edge>();
        for(Edge i : edges() ){
          if(i.getSrc() == n){
            returned.add(i);
          }
        }
        return returned;
    }

    public List<Edge> inEdges(String n) {
       List<Edge> returned = new LinkedList<Edge>();
        for(Edge i : edges() ){
          if(i.getDst() == n){
            returned.add(i);
          }
        }
        return returned;
    }

    public List<Edge> edges() {
        List<Edge> returned = new LinkedList<Edge>();
        for(String k : g.nodes()){
          for(int n=0; n<g.succ(k).size();n++){
            Edge toAdd = new Edge(k,g.succ(k).get(n));
            returned.add(toAdd);
          }
        }
        return returned;
    }

    public EdgeGraph union(EdgeGraph g) {
      EdgeGraph returned = g;

      for(Edge k : edges()){
         if(!returned.hasEdge(k)){
           returned.addEdge(k);
         }
      }
      return returned;
      
    }

    public boolean hasPath(List<Edge> e) {
      for(int i = 0; i<e.size()-1; i++){
         if(e.get(i).getDst() != e.get(i+1).getSrc())
              throw new BadPath();       
      }
      for(int j=0; j < e.size(); j++){
         if(!hasEdge(e.get(j))) return false;
      }
  
      return true;
       
    }
    
   

}
