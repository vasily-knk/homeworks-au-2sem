package ru.spbau.kononenko.drunkgame.Drunks;

import ru.spbau.kononenko.drunkgame.Logic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Logic.Portal;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;

public class Bar extends Portal {
    private final int spawnFrequency;
    private int timeElapsed = 0;
    
    public Bar(Field field, Coord coord, DynamicControl dynamicControl, int spawnFrequency) {
        super(field, coord, dynamicControl);
        this.spawnFrequency = spawnFrequency;
    }

    @Override
    public void update() {
        if (timeElapsed == spawnFrequency) {
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
