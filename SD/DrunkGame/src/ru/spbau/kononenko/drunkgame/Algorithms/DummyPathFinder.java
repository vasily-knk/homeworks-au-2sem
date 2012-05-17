package ru.spbau.kononenko.drunkgame.Algorithms;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;

import java.util.List;

public class DummyPathFinder implements PathFinder {
    @Override
    public List<Coord> getPath(final Field field, final Coord src, final Coord dst, final FilterInterface<FieldObject> ignore) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
