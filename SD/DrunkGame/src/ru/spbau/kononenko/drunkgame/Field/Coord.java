package ru.spbau.kononenko.drunkgame.Field;

public class Coord implements Comparable<Coord> {
    public final int x, y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord plus (Coord other) {
        return new Coord(x + other.x, y + other.y);
    }

    public Coord minus (Coord other) {
        return new Coord(x - other.x, y - other.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Coord o) {
        if (x == o.x)
            return Integer.compare(y, o.y);
        return Integer.compare(x, o.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return (x == coord.x && y == coord.y);
    }
    
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
