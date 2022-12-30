import java.io.File;
import java.util.Scanner;

public class GraphUtils {
    private static double PRECISION = 1.0e-2;

    /**
     * @param d : double
     * @return String represents d with 2 places after the decimal point.
     */
    public static String formatDouble(double d) {
        String res = String.format("%.2f", d);
        if (res.equals("-0.00"))
            res = "0.00";
        return res;
    }

    public static boolean areEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < PRECISION;
    }

    /**
     * creates an IGraph from a String.
     * @param s
     * @return
     */
    public static IGraph<String> readGraphString(String s) {
        IGraph<String> graph;
        Scanner scan = new Scanner(s);

        scan.useDelimiter("[\\t:{,}]");
        String test = scan.next();


        if (test.equals("DirectedGraph")) {
            graph = new DirectedGraph<String>();
            scan.next();
        }
        else {
            graph = new UndirectedGraph<String>();
            scan.next();
        }

        //ADDING THE FIRST VERTEX
        String nextVertex = scan.next();

        nextVertex = nextVertex.replace(" ", "");
        if (nextVertex.length() != 0) {
            graph.addVertex(nextVertex);
        }
        //scan.next();
        while (scan.hasNext()) {
            String point = scan.next();
            if (point.contains(" ")) {
                nextVertex = point.replace(" ", "");
                if (nextVertex.length() != 0) {
                    graph.addVertex(nextVertex);
                }
                scan.next();
            }
            else {

                if (point.length() != 0 && nextVertex.length() != 0) {
                    graph.addEdge(nextVertex, point);
                }

            }

        }
        scan.close();
        return graph;
    }
}
