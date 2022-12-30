public class GraphFormatException  extends Exception{

    public GraphFormatException(String graph, int line) {
        super("line number=" + line + ", line input=" + graph + ", Error message= Exception in scanning the graph string\n");
    }
}
