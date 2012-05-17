package ru.spbau.kononenko.drunkgame.Static;

import ru.spbau.kononenko.drunkgame.Arrestable;
import ru.spbau.kononenko.drunkgame.BFS;
import ru.spbau.kononenko.drunkgame.ArrestableReporter;
import ru.spbau.kononenko.drunkgame.Field.*;
import ru.spbau.kononenko.drunkgame.Walking.Drunk;

public class Streetlight extends SelfAwareImpl implements ArrestableReporter {
    private final int radius;

    public Streetlight(Field field, Coord coord, int radius) {
        super (field, coord);
        this.radius = radius;
    }
    
    public Arrestable search() {
        final Arrestable[] res = new Arrestable[1];
        res[0] = null;

        BFS bfs = new BFS(field, coord) {
            @Override
            public CheckState check(Record r) {
                FieldObject object = field.getObject(r.coord);
                if (object != null && object.getProperty(Drunk.sleepingDrunkProperty) && (object instanceof Arrestable))
                {
                    res[0] = (Arrestable)object;
                    return CheckState.STOP;
                }
                    
                if (r.depth == radius)
                    return CheckState.CUT;
                return CheckState.CONTINUE;
            }
        };
        bfs.run();
        return res[0];
    }
    
    @Override
    public boolean getProperty(Property property) {
        return false;
    }

    @Override
    public char getChar() {
        return 'Ф';
    }
}
