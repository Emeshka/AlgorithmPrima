package emeshka;

/**
 * Created by Alexandra on 05.12.2018.
 */
public class Int {
    private int value = 0;//private to make immutable

    public boolean isInfinity() {
        return value == Integer.MAX_VALUE;
    }

    @Override public String toString() {
        return isInfinity() ? "∞" : ""+value;
    }

    public Int plus(Int b) {
        if (this.isInfinity() || b.isInfinity()) return infinity();
        long sum = this.value + b.value;
        if (sum > Integer.MAX_VALUE) return infinity();//во избежание переполнения инта
        else return new Int(this.value + b.value);
    }

    public int getValue() {
        return value;
    }

    public static Int min(Int a, Int b) {
        if (a.value < b.value) return a;
        else return b;
    }

    public static Int infinity() {
        return new Int(Integer.MAX_VALUE);
    }

    public Int(int v) {
        value = v;
    }
}
