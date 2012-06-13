package ru.spbau.kononenko.drunkgame.common.algorithms.pathfinders;

import ru.spbau.kononenko.drunkgame.common.algorithms.BFS;
import ru.spbau.kononenko.drunkgame.common.algorithms.filters.FilterInterface;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;

import java.util.HashMap;

public class BFSPathFinder implements PathFinder {
    private HashMap<Coord, Coord> parents = new HashMap<Coord, Coord>();
    private Coord dst;

    @Override
    public Coord getNext(final Field field, final Coord src, final FilterInterface<Coord> dstFilter, 
                         final FilterInterface<FieldObject> ignore) {
        
        parents.clear();
        dst = null;

        BFS bfs = new BFS(field, src) {
            @Override
            public CheckState check(Record r) {
                if (!ignore.accept(field.getObject(r.coord)))
                    return CheckState.CUT;

                parents.put(r.coord, r.parent);

                if (dstFilter.accept(r.coord)) {
                    dst = r.coord;
                    return CheckState.STOP;
                }

                return CheckState.CONTINUE;
            }
        };

        bfs.run();

        if (dst == null)
            return null;
        
        Coord c = dst;
        while (parents.get(c) != src) {
            c = parents.get(c);
        }
        
        return c;
    }
}
