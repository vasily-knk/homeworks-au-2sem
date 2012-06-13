package ru.spbau.kononenko.drunkgame.common.algorithms;

import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class BFS {
    public static class Record {
        public final Coord coord;
        public final Coord parent;
        public final int depth;

        Record (Coord coord, Coord parent, int depth) {
            this.coord = coord;
            this.parent = parent;
            this.depth = depth;
        }
    }

    public enum CheckState {
        CONTINUE, CUT, STOP
    }
    
    private Field field;
    private HashSet<Coord> visited = new HashSet<Coord>();
    private Queue<Record> queue = new LinkedList<Record>();

    public BFS(Field field, Coord start) {
        this.field = field;
        queue.add(new Record(start, null, 0));
    }

    public void run() {

        while (!queue.isEmpty()) {
            Record r = queue.remove();
            while (visited.contains(r.coord)) {
                if (queue.isEmpty())
                    break;
                r = queue.remove();
            }

            
            CheckState state = check(r);

            if (state == CheckState.STOP)
                return;

            
            if (state == CheckState.CONTINUE) {
                List<Coord> adjs = field.getAdjacent(r.coord);
                for (Coord adj : adjs) {
                    if (!visited.contains(adj)) {
                        queue.add(new Record(adj, r.coord, r.depth + 1));
                    }
                }
            }
            visited.add(r.coord);
        }
    }
    
    public abstract CheckState check(Record r);
}
