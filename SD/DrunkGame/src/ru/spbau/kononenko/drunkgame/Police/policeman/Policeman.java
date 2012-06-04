package ru.spbau.kononenko.drunkgame.police.policeman;

import ru.spbau.kononenko.drunkgame.common_actors.Actor;
import ru.spbau.kononenko.drunkgame.common_algorithms.pathfinders.BFSPathFinder;
import ru.spbau.kononenko.drunkgame.common_algorithms.filters.OneOfFilter;
import ru.spbau.kononenko.drunkgame.common_algorithms.pathfinders.PathFinder;
import ru.spbau.kononenko.drunkgame.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.field.objects.FieldObject;
import ru.spbau.kononenko.drunkgame.field.objects.FieldObjectProperty;
import ru.spbau.kononenko.drunkgame.police.arrestable.Arrestable;
import ru.spbau.kononenko.drunkgame.police.arrestable.ArrestableNotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Policeman extends Actor {
    private enum State {
        CHASING, RETURNING
    }

    private final PathFinder pathFinder = new BFSPathFinder();
    private final Arrestable victim;
    private final PolicemanReportInterface reportInterface;
    private final Coord home;

    private Coord target;
    private State state;
    Queue<Coord> path;

    public Policeman(Field field, Coord coord, Arrestable victim, PolicemanReportInterface reportInterface) {
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
            throw new ArrestableNotFoundException("arrestable not found on reported coord: " + victim.getCoord());
    }
}
