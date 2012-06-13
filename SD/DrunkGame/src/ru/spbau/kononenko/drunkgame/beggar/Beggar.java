package ru.spbau.kononenko.drunkgame.beggar;

import ru.spbau.kononenko.drunkgame.common.actors.ReturnReportInterface;
import ru.spbau.kononenko.drunkgame.common.actors.ThereAndBackAgain;
import ru.spbau.kononenko.drunkgame.common.algorithms.filters.FilterInterface;
import ru.spbau.kononenko.drunkgame.drunks.Bottle;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObjectProperty;

public class Beggar extends ThereAndBackAgain {
    private FilterInterface<FieldObject> ignoreFilter = new FilterInterface<FieldObject>() {
        @Override
        public boolean accept(FieldObject fieldObject) {
            if (fieldObject == Beggar.this || fieldObject == null)
                return true;
            return fieldObject.getProperty(Bottle.bottleProperty);
        }
    };

    private FilterInterface<Coord> isBottleLocation = new FilterInterface<Coord>() {
        @Override
        public boolean accept(Coord coord) {
            if (getField().getObject(coord) == null)
                return false;
            return getField().getObject(coord).getProperty(Bottle.bottleProperty);
        }
    };


    public Beggar(Field field, Coord coord, ReturnReportInterface onReturn) {
        super(field, coord, onReturn);
    }

    @Override
    protected Coord chase() {
        Coord next = getPathFinder().getNext(getField(), getCoord(), isBottleLocation, ignoreFilter);
        if (next == null)
            return null;

        if (getField().getObject(next) != null && getField().getObject(next).getProperty(Bottle.bottleProperty)) {
            getField().removeObject(next);
            changeState(State.RETURNING);
        }

        return next;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean getProperty(FieldObjectProperty property) {
        return false;
    }

    @Override
    public char getChar() {
        return 'z';
    }
}
