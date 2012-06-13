package ru.spbau.kononenko.drunkgame.common.algorithms.pathfinders;

import ru.spbau.kononenko.drunkgame.common.algorithms.BFS;
import ru.spbau.kononenko.drunkgame.common.algorithms.filters.FilterInterface;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;

import java.util.*;

public class BFSOldPathFinder implements OldPathFinder {
    private HashMap<Coord, Coord> parents = new HashMap<Coord, Coord>();


    @Override
    public List<Coord> getPath(final Field field, final Coord src, final Coord dst, final FilterInterface<FieldObject> ignore) {
        if (!ignore.accept(field.getObject(dst)))
            return null;
        
        parents.clear();
        
        BFS bfs = new BFS(field, src) {
            @Override
            public CheckState check(Record r) {
                if (!ignore.accept(field.getObject(r.coord)))
                    return CheckState.CUT;
                
                parents.put(r.coord, r.parent);
                if (r.coord.equals(dst))
                    return CheckState.STOP;
                
                return CheckState.CONTINUE;
            }
        };

        bfs.run();
        
        if (!parents.containsKey(dst))
            return null;
        
        ArrayList<Coord> res = new ArrayList<Coord>();
        Coord c = dst;
        while (parents.get(c) != null) {
            res.add(c);
            c = parents.get(c);
        }
        Collections.reverse(res);

        return res;
    }
}
