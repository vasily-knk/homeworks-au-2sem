package ru.spbau.kononenko.drunkgame.common_algorithms.pathfinders;

import ru.spbau.kononenko.drunkgame.common_algorithms.filters.FilterInterface;
import ru.spbau.kononenko.drunkgame.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.field.objects.FieldObject;

public interface PathFinder {
    Coord getNext(Field field, Coord src,
                  FilterInterface<Coord> dstFilter,
                  FilterInterface<FieldObject> ignore);
}
