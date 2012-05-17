package ru.spbau.kononenko.drunkgame.Police;

import ru.spbau.kononenko.drunkgame.Logic.Actor;
import ru.spbau.kononenko.drunkgame.Algorithms.BFSPathFinder;
import ru.spbau.kononenko.drunkgame.Algorithms.OneOfFilter;
import ru.spbau.kononenko.drunkgame.Algorithms.PathFinder;
import ru.spbau.kononenko.drunkgame.Logic.ReportInterface;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.FieldObjectProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Policeman extends Actor {
    private enum State {
        CHASING, RETURNING
    }

    private final PathFinder pathFinder = new BFSPathFinder();
    private final Arrestable victim;
    private final ReportInterface reportInterface;
    private final Coord home;

    private Coord target;
    private State state;
    Queue<Coord> path;

    public Policeman(Field field, Coord coord, Arrestable victim, ReportInterface reportInterface) {
        super(field, coord);

        this.victim = victim;
        this.reportInterface = reportInterface;

        home = coord;
        startChase();
    }

    @Override
    public void update() {
        // No path. Search again.
        if (path == null) {
            updatePath();
            return;
        }
        
        if (path.isEmpty()) {
            if (state == State.RETURNING) {
                report();
            } else {
                // Came to the location but no one is found. Search again.
                updatePath();
            }
            return;
        }

        Coord next = path.remove();

        if (state == State.CHASING && next.equals(victim.getCoord()))
        {
            checkVictimConsistency();
            victim.arrest(this);
            startReturn();
            return;
        }

        if (getField().getObject(next) != null)
        {
            // Path blocked, invalidate it
            path = null;
            return;
        }

        moveTo(next);
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
    
    private void report() {
        reportInterface.report();
        kill();
        
    }
    private void startChase() {
        state = State.CHASING;
        target = victim.getCoord();
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
        if (state == State.CHASING) {
            ignore.add(getField().getObject(target));
            
            checkVictimConsistency();
        }

        List<Coord> list = pathFinder.getPath(getField(), getCoord(), target, ignore);
        path = (list == null) ? null : new LinkedList<Coord>(list);
    }
    
    private void checkVictimConsistency() {
        if (getField().getObject(victim.getCoord()) != victim)
            throw new ArrestableNotFoundException("Arrestable not found on reported coord: " + victim.getCoord());
    }
}
