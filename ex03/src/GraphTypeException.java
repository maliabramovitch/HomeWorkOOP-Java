public class GraphTypeException  extends Exception{

    public GraphTypeException(String graph, int line) {
        super("line number=" + line + ", line input=" + graph + ", Error message= The graph type is not valid\n");
    }
}
