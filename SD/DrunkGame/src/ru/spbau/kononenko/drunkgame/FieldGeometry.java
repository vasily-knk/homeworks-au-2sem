package ru.spbau.kononenko.drunkgame;

import java.util.List;

public interface FieldGeometry/*<Coord>*/ {
    public List<Coord> getAdjacent(Coord coord);
    public int getDist(Coord coord1, Coord coord2);
    public boolean isInside(Coord coord);
}
