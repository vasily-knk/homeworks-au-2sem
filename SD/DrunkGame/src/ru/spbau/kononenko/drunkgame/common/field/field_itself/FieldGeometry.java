package ru.spbau.kononenko.drunkgame.common.field.field_itself;

import java.util.List;

public interface FieldGeometry {
    public List<Coord> getAdjacent(Coord coord);
    public boolean isInside(Coord coord);
}
