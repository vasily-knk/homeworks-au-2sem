package ru.spbau.kononenko.drunkgame.Police;

import ru.spbau.kononenko.drunkgame.Drunks.Drunk;
import ru.spbau.kononenko.drunkgame.Algorithms.BFS;
import ru.spbau.kononenko.drunkgame.Field.*;

public class Streetlight extends SelfAwareFieldObject implements ArrestableReporter {
    private final int radius;

    public Streetlight(Field field, Coord coord, int radius) {
        super (field, coord);
        this.radius = radius;
    }
    
    public Arrestable search() {
        final Arrestable[] res = new Arrestable[1];
        res[0] = null;

        BFS bfs = new BFS(getField(), getCoord()) {
            @Override
            public CheckState check(Record r) {
                FieldObject object = getField().getObject(r.coord);
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
    public boolean getProperty(FieldObjectProperty property) {
        return false;
    }

    @Override
    public char getChar() {
        return 'Ф';
    }
}
