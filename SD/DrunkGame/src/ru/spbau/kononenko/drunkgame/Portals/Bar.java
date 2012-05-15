package ru.spbau.kononenko.drunkgame.Portals;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Walking.Drunk;
import ru.spbau.kononenko.drunkgame.DynamicControl;
import ru.spbau.kononenko.drunkgame.Field.Field;

public class Bar extends Portal {
    private static final int SPAWN_EACH = 20;
    private int timeElapsed = 0;
    
    public Bar(Field field, Coord coord, DynamicControl dynamicControl) {
        super(field, coord, dynamicControl);
    }

    @Override
    public void update() {
        if (timeElapsed == SPAWN_EACH) {
            if (canSpawn())
                spawn(new Drunk(field, coord));
            timeElapsed = 0;
        }
        ++timeElapsed;
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
