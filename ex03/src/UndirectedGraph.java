import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class UndirectedGraph <V extends Comparable<V>> extends DirectedGraph<V> {
	
	
	public UndirectedGraph() {
		super();
	}
	
	
	/**
	 * Add a new edge if none exists between the two vertices. If the vertices u or
	 * v do not exist, add them to the graph.
	 */
	@Override
	public void addEdge(V u, V v) {
		super.addEdge(u, v);
		super.addEdge(v, u);		
		}	
	
	
	/**
	 * @return the type of the graph "Directed" or "Undirected".
	 */
	@Override
	public String getGraphType() {
		return "UndirectedGraph";
	}
	
	
	/**
	 * @return Remove the edge (u,v) if it exists, and return True. If this edge
	 *         does not exist return False.
	 */
	 @Override 
	 public boolean removeEdge(V u, V v) {  
		 return super.removeEdge(v, u) && super.removeEdge(u, v);
	 }
	 

	@Override
	public String toString() {
		return getGraphType() + ":\t" + super.toStringHelper();
	}
	

}	
	
	
	
	
	

	
