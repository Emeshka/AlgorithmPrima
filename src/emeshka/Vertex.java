package emeshka;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import static java.lang.System.out;
import static emeshka.Main.tryParse;

/**
 * Created by Alexandra on 06.11.2018.
 */
public class Vertex {
    String id = "";
    ArrayList<Rib> incidentRibs = null;//массив положительно инцидентных дуг

    @Override public String toString() {
        return this.id;
    }

    public TreeSet<Rib> getConnectionsTo(Vertex end) {
        TreeSet<Rib> conns = new TreeSet<>(Rib.getComparator());
        for (Rib r : incidentRibs) {
            if (r.v2 == end) conns.add(r);//сравнение идентичности указателей
        }
        return conns;
    }

    public static Comparator<Vertex> getComparator() {
        return (v1, v2) -> {
            Integer i1 = tryParse(v1.id.substring(1, v1.id.length()));
            Integer i2 = tryParse(v2.id.substring(1, v2.id.length()));
            //упорядочить по нумерации, если имя в формате Vn, иначе - в лексикографическом порядке
            return (i1 == null || i2 == null) ? v1.id.compareTo(v2.id) : (i1-i2);
        };
    }

    public void connectTo(Vertex v, int weight) {
        if (v == null) return;
        Rib r = new Rib(this, v, weight);
        this.incidentRibs.add(r);
    }

    public void loop(int weight) {
        connectTo(this, weight);
    }

    public void print() {
        out.println("Vertex id: '"+id+"'");
        if (incidentRibs.size() > 0) {
            String ribsList = "";
            for (Rib r : incidentRibs) {
                ribsList += r.id + ", ";
            }
            out.println("incidentRibs: {" + ribsList.substring(0, ribsList.length() - 2) + "}");
        } else out.println("no incidentRibs");
        out.println();
    }

    public Vertex(String name, ArrayList<Vertex> connectTo, ArrayList<Integer> weights) {
        id = name;
        incidentRibs = new ArrayList<>();
        if (connectTo != null && weights != null) {
            for (int i = 0; i < connectTo.size(); i++) {
                connectTo(connectTo.get(i), weights.get(i));
            }
        }
    }
}
