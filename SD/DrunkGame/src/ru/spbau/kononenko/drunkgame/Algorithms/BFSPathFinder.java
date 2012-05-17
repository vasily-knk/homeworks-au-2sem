package ru.spbau.kononenko.drunkgame.Algorithms;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BFSPathFinder implements PathFinder {
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
        
        LinkedList<Coord> res = new LinkedList<Coord>();
        Coord c = dst;
        while (parents.get(c) != null) {
            res.addFirst(c);
            c = parents.get(c);
        }

        return res;
    }
}
