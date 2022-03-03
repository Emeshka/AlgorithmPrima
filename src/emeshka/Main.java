package emeshka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static java.lang.System.out;
/**
 * Created by Alexandra on 06.11.2018.
 */
public class Main {
    public static ArrayList<Vertex> list(Vertex... els) {
        return new ArrayList<>(Arrays.asList(els));
    }

    public static ArrayList<Integer> list(Integer... els) {
        return new ArrayList<>(Arrays.asList(els));
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Vertex v1 = new Vertex("V1", null, null);
        Vertex v2 = new Vertex("V2", list(v1), list(7));
        Vertex v7 = new Vertex("V7", list(v2), list(9));
        Vertex v4 = new Vertex("V4", list(v1, v7), list(8, 5));
        v4.loop(3);
        Vertex v3 = new Vertex("V3", list(v2, v4), list(9, 7));
        Vertex v5 = new Vertex("V5", list(v1), list(6));
        Vertex v6 = new Vertex("V6", list(v2, v2), list(2, 4));
        Vertex v9 = new Vertex("V9", list(v5, v7), list(1, 4));
        v1.connectTo(v9, 4);
        Vertex v12 = new Vertex("V12", list(v5, v9), list(2, 5));
        v12.loop(7);
        Vertex v11 = new Vertex("V11", list(v5, v5, v3, v6), list(3, 8, 1, 2));
        Vertex v8 = new Vertex("V8", list(v3), list(4));
        v2.connectTo(v8, 2);
        Vertex v10 = new Vertex("V10", list(v8, v6, v11), list(3, 6, 1));
        v8.connectTo(v10, 5);
        Graph G = new Graph();
        G.makeByVerteces(list(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12));

        out.println("This program will apply Warshall-Floyd's algorithm to a graph, that's inherent in the program.");
        out.println("\tInitial graph G:\n\n---\n");
        G.print();
        out.println("\n\tData:\n");
        G.data();
        out.println("\nRunning algorithm...\n");

        Int[][] d = G.shortestDistanceMatrix();
        out.println("\nShortest distances matrix:\n");

        for (Vertex w : G.V) out.print("\t"+w);
        int i = 0;
        for (Vertex v : G.V) {
            out.print("\n"+v);
            for (int j = 0; j<d[i].length; j++) out.print("\t"+d[i][j]);
            i++;
        }
    }
}
