package ru.spbau.kononenko.drunkgame.drunks;

import ru.spbau.kononenko.drunkgame.game.DynamicControl;
import ru.spbau.kononenko.drunkgame.common.actors.Portal;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;

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
