package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.Arrestable;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.Property;
import ru.spbau.kononenko.drunkgame.PathFinder;
import ru.spbau.kononenko.drunkgame.PoliceReportInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Policeman extends Actor {
    private enum State {
        CHASING, RETURNING
    }
    
    private final Coord home;
    private PoliceReportInterface reportInterface;
    private PathFinder pathFinder;
    private Coord target;
    private State state;
    Queue<Coord> path;

    public Policeman(Field field, Coord coord, Coord target, PoliceReportInterface reportInterface) {
        super(field, coord);
        home = coord;
        this.target = target;
        this.reportInterface = reportInterface;
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

        if (state == State.CHASING && next == target)
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
        List<Coord> list = pathFinder.getPath(coord, target);
        path = (list == null) ? null : new LinkedList<Coord>(path);
        if (path != null)
            path.remove();
    }
}
