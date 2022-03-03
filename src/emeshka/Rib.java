package emeshka;

import java.util.Comparator;

import static java.lang.System.out;
import static emeshka.Main.tryParse;
/**
 * Created by Alexandra on 06.11.2018.
 */
public class Rib {
    //private static int lastId = 0;

    String id = "";
    Vertex v1 = null;//start
    Vertex v2 = null;//end
    int weight = 0;

    @Override public String toString() {
        return this.id;
    }

    public static Comparator<Rib> getComparator() {
        return (r1, r2)-> {
            int dw = r1.weight - r2.weight;
            Integer i11 = tryParse(r1.v1.id.substring(1, r1.v1.id.length()));
            Integer i12 = tryParse(r1.v2.id.substring(1, r1.v2.id.length()));
            Integer i21 = tryParse(r2.v1.id.substring(1, r2.v1.id.length()));
            Integer i22 = tryParse(r2.v2.id.substring(1, r2.v2.id.length()));
            //упорядочить по весу; затем по нумерации, если имя в формате en; иначе - в лексикографическом порядке
            //int res = 0;
            if (dw != 0) return dw;
            if (i11 == null || i12 == null || i21 == null || i22 == null) return r1.id.compareTo(r2.id);
            if (i11.intValue() != i21.intValue()) return i11-i21;
            return i12 - i22;
        };
    }

    public void print() {
        out.println("Rib id:\t'"+id+"'");
        out.println("start:\t"+v1.id);
        out.println("end:\t"+v2.id);
        out.println("weight:\t"+weight);
        out.println();
    }

    public Rib(Vertex v1, Vertex v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        id = "(" + this.v1.id + ", " + this.v2.id + ")";
        weight = w;
    }
}
