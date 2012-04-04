package ru.spbau.kononenko.drunkgame;

final class Coord2d {
    public final int x, y;

    public Coord2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord2d plus (Coord2d other) {
        return new Coord2d(x + other.x, y + other.y);
    }

    public Coord2d minus (Coord2d other) {
        return new Coord2d(x - other.x, y - other.y);
    }

    @Override
    public String toString() {
        return "" + x + ", " + y;
    }
}
