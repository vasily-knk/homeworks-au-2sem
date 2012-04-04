package ru.spbau.kononenko.drunkgame;

import java.util.List;

public interface FieldGeometry<T> {
    public List<T> getAdjacent(T coord);
    public int getDist(T coord1, T coord2);
    public boolean isInside(T coord);
}
