package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;

import java.util.HashSet;
import java.util.Queue;

public abstract class BFS {
    private Field field;
    private HashSet visited = new HashSet();
    private Queue<Coord> queue;

    public BFS (Field field, Coord start) {
        this.field = field;
        queue.add(start);
    }

    public void run() {
        while (!queue.isEmpty()) {
            Coord c = queue.remove();
            if (check(c))
                return;

            for (Coord adj : field.getAdjacent(c)) {
                if (!visited.contains(adj))
                    queue.add(adj);
            }
            visited.add(c);
        }
    }
    
    public abstract boolean check(Coord c);
}
