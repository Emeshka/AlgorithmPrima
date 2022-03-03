package emeshka;

import java.util.*;

import static java.lang.System.out;

/**
 * Created by Alexandra on 06.11.2018.
 */
public class Graph {
    //Класс TreeSet:
    //...The elements are _ordered_ using their natural ordering,
    // or by a Comparator provided at set creation time, depending on which constructor is used.
    // TreeSet implements Set;
    // Интерфейс Set:
    // A collection that contains _no_duplicate_ elements.
    Set<Vertex> V = null;
    Set<Rib> E = null;
    int weight = 0;

    private void updateWeight() {
        for (Rib r : E) {
            weight += r.weight;
        }
    }

    public void print() {
        String verteces = "";
        for (Vertex v : V) {
            verteces += v.id+", ";
        }
        out.println("Graph: V={"+verteces.substring(0, verteces.length()-2)+"}");

        String ribs = "";
        for (Rib r : E) {
            ribs += r.id+", ";
        }
        out.println("E={"+ribs.substring(0, ribs.length()-2)+"}");
        out.println("weight: " + weight);
    }

    //вывод этой функции можно вставить в использованный мной онлайн-сервис и получить нарисованный граф
    public void data() {
        String verteces = "";
        for (Vertex v : V) {
            verteces += v.id+"\n";
        }
        String ribs = "";
        for (Rib r : E) {
            ribs += r.v1+" "+r.v2+" "+r.weight+"\n";
        }
        out.println(verteces + ribs);
    }

    public Int[][] shortestDistanceMatrix() {
        int dim = V.size();
        Int[][] d = new Int[dim][dim];
        int i = 0, j = 0;

        out.println("Initial matrix D(0):");
        for (Vertex v : V) {
            out.println();
            for (Vertex w : V) {
                TreeSet<Rib> conns = v.getConnectionsTo(w);
                //out.println("-- "+v+", "+w);
                if (conns.isEmpty()) {
                    d[i][j] = Int.infinity();
                } else {
                    d[i][j] = new Int(conns.first().weight);
                }
                out.print("\t"+d[i][j]);
                j++;
            }
            i++;
            j=0;
        }

        out.println("\n\nMatrix D("+dim+") will be the answer.");
        for (int n = 0; n<dim; n++) {
            out.println("\nMatrix D("+(n+1)+"):");
            for (i=0; i<dim; i++) {
                out.println();
                for (j=0; j<dim; j++) {
                    if (i==n || j==n) {
                        out.print("\t"+d[i][j]);
                        continue;
                    }
                    d[i][j] = Int.min(d[i][j], d[i][n].plus(d[n][j]));
                    out.print("\t"+d[i][j]);
                }
            }
            out.println();
        }
        return d;
    }

    public void makeByVerteces(Collection<Vertex> verteces) {
        V = new TreeSet<>(Vertex.getComparator());
        V.addAll(verteces);
        E = new TreeSet<>(Rib.getComparator());

        for (Vertex v : V) {
            E.addAll(v.incidentRibs);
        }
        updateWeight();
    }

    public Graph(){};
}
