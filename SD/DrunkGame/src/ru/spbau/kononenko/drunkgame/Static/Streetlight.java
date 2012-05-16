package ru.spbau.kononenko.drunkgame.Static;

import ru.spbau.kononenko.drunkgame.BFS;
import ru.spbau.kononenko.drunkgame.Field.*;
import ru.spbau.kononenko.drunkgame.Walking.Drunk;

public class Streetlight extends SelfAwareFieldObject {
    private final int radius;

    public Streetlight(Field field, Coord coord, int radius) {
        super (field, coord);
        this.radius = radius;
    }
    
    public Coord searchForDrunk() {
        BFS bfs = new BFS(field, coord) {
            @Override
            public boolean check(Coord c) {
                return true;
            }
        };
        return null;
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
