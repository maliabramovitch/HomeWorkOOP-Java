import java.io.*;
import java.util.*;

public class GraphsHandler {


    public static void main(String[] args) throws IOException {

        File f = new File(args[0]);
        Scanner s = new Scanner(f);
        Writer pw0 = new FileWriter(args[4]); //Error output file
        Writer pw1 = new FileWriter(args[1]); //Reversed list file
        ArrayList<IGraph<String>> aList = new ArrayList<IGraph<String>>();
        SortedSet<IGraph<String>> set = new TreeSet<IGraph<String>>();
        int lineCounter = 0;
        while (s.hasNextLine()) {
            lineCounter++;
            String str = s.nextLine();
            try {
                if (str.length() < 1) //IF LINE IS EMPTY
                    throw new GraphNullException(str, lineCounter);
                if (!str.startsWith("DirectedGraph:\t") && !str.startsWith("UndirectedGraph:\t")) { //IF GRAPH TYPE IS VALID.
                    throw new GraphTypeException(str, lineCounter);
                }
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == ':' && (str.charAt(i + 1) != '{')) { //CHECKS THE FORMAT CORRECTION.
                        if (str.charAt(i) == ':' && str.charAt(i + 1) != '\t')
                            throw new GraphFormatException(str, lineCounter);
                    }
                    if (str.charAt(i) == ' ' && (str.charAt(i - 1) != '}')) {
                        if (str.charAt(i - 1) != ':')
                            throw new GraphFormatException(str, lineCounter);
                    }
                    if (i == (str.length() - 1) && str.charAt(i) != '}') {
                        throw new GraphFormatException(str, lineCounter);
                    }
                }
                IGraph<String> ig = GraphUtils.readGraphString(str);
                aList.add(ig);
                set.add(ig);
            } catch (GraphTypeException e) {
                pw0.write(e.getMessage());
            } catch (GraphFormatException e) {
                pw0.write(e.getMessage());
            } catch (GraphNullException e) {
                pw0.write(e.getMessage());
            }

        }

        for (int i = aList.size() - 1; i >= 0; i--) {
            pw1.write((aList.get(i)).toString());
            pw1.write('\n');
        }
        Collections.sort(aList);
        Writer pw2 = new FileWriter(args[2]); //SORTED SET OUTPUT FILE
        Writer pw3 = new FileWriter(args[3]); //SORTED LIST OUTPUT FILE
        for (IGraph<String> g : set) {
            pw2.write(String.valueOf(g));
            pw2.write('\n');
        }
        for (int i = 0; i < aList.size(); i++) {
            pw3.write(String.valueOf(aList.get(i)));
            pw3.write('\n');
        }
        s.close();
        pw0.flush();
        pw1.flush();
        pw2.flush();
        pw3.flush();
        pw0.close();
        pw1.close();
        pw2.close();
        pw3.close();

    }

}