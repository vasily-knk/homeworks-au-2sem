package ru.spbau.kononenko.drunkgame.common.actors;

import ru.spbau.kononenko.drunkgame.common.algorithms.filters.FilterInterface;
import ru.spbau.kononenko.drunkgame.common.algorithms.filters.OneOfFilter;
import ru.spbau.kononenko.drunkgame.common.algorithms.pathfinders.BFSPathFinder;
import ru.spbau.kononenko.drunkgame.common.algorithms.pathfinders.PathFinder;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;

public abstract class ThereAndBackAgain extends Actor {
    protected enum State {
        CHASING, RETURNING
    }

    private Coord home;
    private ReturnReportInterface onReturn;
    private State state;
    private PathFinder pathFinder;
    private FilterInterface<Coord> isHomeFilter;

    private FilterInterface<FieldObject> ignoreFilter = new OneOfFilter<FieldObject>(null, this);
    
    public ThereAndBackAgain(Field field, Coord coord, ReturnReportInterface onReturn) {
        super(field, coord);
        this.home = coord;
        this.onReturn = onReturn;
        this.pathFinder = new BFSPathFinder();
        isHomeFilter = new OneOfFilter<Coord>(home);
    }

    public ThereAndBackAgain(Field field, Coord coord, ReturnReportInterface onReturn, PathFinder pathFinder) {
        super(field, coord);
        this.home = coord;
        this.onReturn = onReturn;
        this.pathFinder = pathFinder;
        isHomeFilter = new OneOfFilter<Coord>(home);
    }
    
    @Override
    public void update() {
        Coord nextCoord;
        if (state == State.RETURNING) {
            if (getCoord().equals(home)) {
                reportReturn();
                return;
            }
            nextCoord = pathFinder.getNext(getField(), getCoord(), isHomeFilter, ignoreFilter);
        } else {
            nextCoord = chase();
        }

        if (nextCoord != null)
            moveTo(nextCoord);
    }

    private void reportReturn() {
        onReturn.report();
        kill();
    }

    public State getState() {
        return state;
    }

    protected void changeState(State state) {
        this.state = state;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    abstract protected Coord chase();
}
