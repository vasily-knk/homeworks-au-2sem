package ru.spbau.kononenko.drunkgame.Field;

public class Coord {
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
}
