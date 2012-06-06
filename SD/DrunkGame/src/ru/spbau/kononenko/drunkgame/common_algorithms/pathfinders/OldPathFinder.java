package ru.spbau.kononenko.drunkgame.common_algorithms.pathfinders;

import ru.spbau.kononenko.drunkgame.common_algorithms.filters.FilterInterface;
import ru.spbau.kononenko.drunkgame.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.field.objects.FieldObject;

import java.util.List;

public interface OldPathFinder {
    List<Coord> getPath(final Field field, final Coord src, final Coord dst, final FilterInterface<FieldObject> ignore);
}
