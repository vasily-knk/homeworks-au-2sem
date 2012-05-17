package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.*;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.Property;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Policeman extends Actor {
    private enum State {
        CHASING, RETURNING
    }
    
    private final Coord home;
    private PoliceReportInterface reportInterface;
    //private PathFinder pathFinder;
    private Coord target;
    private State state;
    Queue<Coord> path;

    public Policeman(Field field, Coord coord, Coord target, PoliceReportInterface reportInterface) {
        super(field, coord);
        home = coord;
        this.target = target;
        this.reportInterface = reportInterface;
        //this.pathFinder = new BFSPathFinder();
        startChase();
    }

    @Override
    public void update() {
        if (path == null) {
            updatePath();
            return;
        }

        if (state == State.RETURNING && path.isEmpty()) {
            reportInterface.report();
            kill();
            return;
        }

        Coord next = path.remove();

        if (state == State.CHASING && next.equals(target))
        {
            ((Arrestable) field.getObject(target)).arrest(this);
            startReturn();
            return;
        }

        if (field.getObject(next) != null)
            updatePath();
        else
            moveTo(next);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean getProperty(Property property) {
        return false;
    }

    @Override
    public char getChar() {
        return '!';
    }
    
    private void startChase() {
        state = State.CHASING;
        updatePath();
    }
    private void startReturn() {
        state = State.RETURNING;
        target = home;
        updatePath();
    }
    private void updatePath() {
        OneOfFilter<FieldObject> ignore = new OneOfFilter<FieldObject>();
        ignore.add(null);
        ignore.add(this);
        if (state == State.CHASING)
            ignore.add(field.getObject(target));

        BFSPathFinder pathFinder = new BFSPathFinder(field, ignore);
        List<Coord> list = pathFinder.getPath(coord, target);
        path = (list == null) ? null : new LinkedList<Coord>(list);
        if (path != null)
            path.remove();
    }
}
