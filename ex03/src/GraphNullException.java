public class GraphNullException extends Exception{

    public GraphNullException(String graph, int line) {
        super("line number=" + line + ", line input=" + graph + ", Error message= null\n");
    }
}
