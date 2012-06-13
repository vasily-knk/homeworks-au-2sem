package ru.spbau.kononenko.drunkgame.police.policeman;

import ru.spbau.kononenko.drunkgame.common.actors.ReturnReportInterface;
import ru.spbau.kononenko.drunkgame.common.actors.ThereAndBackAgain;
import ru.spbau.kononenko.drunkgame.common.algorithms.filters.FilterInterface;
import ru.spbau.kononenko.drunkgame.common.algorithms.filters.OneOfFilter;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObjectProperty;
import ru.spbau.kononenko.drunkgame.police.arrestable.Arrestable;

public class Policeman extends ThereAndBackAgain {
    private Arrestable victim;
    private FilterInterface<Coord> isVictimLocation = new FilterInterface<Coord>() {
        @Override
        public boolean accept(Coord coord) {
            return getField().getObject(coord) == victim;
        }
    };
    private FilterInterface<FieldObject> ignoreFilter;

    public Policeman(Field field, Coord coord, ReturnReportInterface onReturn, Arrestable victim) {
        super(field, coord, onReturn);
        this.victim = victim;
        this.ignoreFilter = new OneOfFilter<FieldObject>(this, null, victim);
    }

    @Override
    protected Coord chase() {
        Coord next = getPathFinder().getNext(getField(), getCoord(), isVictimLocation, ignoreFilter);
        if (next == null)
            return null;
        
        if (next.equals(victim.getCoord())) {
            victim.arrest(this);
            changeState(State.RETURNING);
            return null;
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
        return '!';
    }
}
