import java.util.*;

public class DirectedGraph<V extends Comparable<V>> implements IGraph<V> {


    private SortedMap<V, SortedSet<V>> vertices;


    public DirectedGraph() {
        vertices = new TreeMap<V, SortedSet<V>>();
    }


    /**
     * Add a new vertex if none exists.
     */
    @Override
    public void addVertex(V v) {
        if (!vertices.containsKey(v)) {
            vertices.put(v, new TreeSet<V>());
        }
    }


    /**
     * Add a new edge if none exists between the two vertices. If the vertices u or
     * v do not exist, add them to the graph.
     */
    public void addEdge(V u, V v) {
        if (!vertices.containsKey(v))
            addVertex(v);
        if (!vertices.containsKey(u)) ;
        addVertex(u);
        vertices.get(u).add(v);

    }


    /**
     * @return If the graph contains the vertex v.
     */
    @Override
    public boolean containsVertex(V v) {
        return vertices.containsKey(v);
    }


    /**
     * @param u, v
     * @return If the the edge (u,v) exists.
     */
    @Override
    public boolean containsEdge(V u, V v) {
        if (vertices.get(u) == null)
            return false;
        if (vertices.get(u).contains(v))
            return true;
        return false;
    }


    /**
     * Remove the vertex and its edges from the graph, and return its incidents. If
     * the vertex dosn't exit return null.
     */
    @Override
    public Set<V> removeVertex(V v) {
        for (V key : vertices.keySet())
            if (vertices.get(key).contains(v))
                vertices.get(key).remove(v);
        return vertices.remove(v);
    }


    /**
     * @return Remove the edge (u,v) if it exists, and return True. If this edge
     * does not exist return False.
     */
    @Override
    public boolean removeEdge(V u, V v) {
        if (vertices.get(u) == null)
            return false;
        if (vertices.get(u).contains(v)) {
            vertices.get(u).remove(v);
            return true;
        }
        return false;
    }


    /**
     * @return the type of the graph "Directed" or "Undirected".
     */
    @Override
    public String getGraphType() {
        return "DirectedGraph";
    }


    /**
     * @return the number of vertices
     */
    @Override
    public int numOfVertices() {
        return vertices.size();
    }


    /**
     * @return the number of edges
     */
    @Override
    public int numOfEdges() {
        int count = 0;
        for (V key : vertices.keySet()) {
            count += vertices.get(key).size();
        }
        return count;
    }

    /**
     * toString helper function.
     *
     * @return String.
     */
    public String toStringHelper() {
        int i = 0;
        String str = new String();
        for (V key : vertices.keySet()) {
            str = str.concat(key.toString());
            str = str.concat(":");
            str = str.concat("{");
            i = vertices.get(key).size();
            for (V value : vertices.get(key)) {
                i--;
                str = str.concat(value.toString());
                if (i > 0)
                    str = str.concat(",");

            }
            str = str.concat("}");
            str = str.concat(" ");
        }
        str = str.trim();
        return str;
    }


    @Override
    public String toString() {

        return getGraphType() + ":\t" + toStringHelper();
    }


    public boolean equals(IGraph<V> other) {
        if (this != other)
            return false;
        return true;
    }


    public int compareTo(IGraph<V> other) {
        int sizeThis = numOfVertices();
        int sizeOther = other.numOfVertices();
        if (this.equals(other))
            return 0;
        if (sizeThis < sizeOther)
            return -1;
        return 1;
    }

}
