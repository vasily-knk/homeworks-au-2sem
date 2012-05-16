package ru.spbau.kononenko.drunkgame.Static;

import ru.spbau.kononenko.drunkgame.BFS;
import ru.spbau.kononenko.drunkgame.DrunkSearcher;
import ru.spbau.kononenko.drunkgame.Field.*;
import ru.spbau.kononenko.drunkgame.Walking.Drunk;

public class Streetlight extends SelfAwareFieldObject implements DrunkSearcher {
    private final int radius;

    public Streetlight(Field field, Coord coord, int radius) {
        super (field, coord);
        this.radius = radius;
    }
    
    public Coord searchForDrunk() {
        final Coord[] res = new Coord[1];
        res[0] = null;

        BFS bfs = new BFS(field, coord) {
            @Override
            public CheckState check(Record r) {
                FieldObject object = field.getObject(r.coord);
                if (object != null && object.getProperty(Drunk.sleepingDrunkProperty))
                {
                    res[0] = r.coord;
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
