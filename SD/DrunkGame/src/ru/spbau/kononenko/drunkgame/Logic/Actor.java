package ru.spbau.kononenko.drunkgame.Logic;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.SelfAwareFieldObject;

public abstract class Actor extends SelfAwareFieldObject implements DynamicObject {
    private boolean dead = false;

    public Actor(Field field, Coord coord) {
        super (field, coord);
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    public void kill() {
        getField().removeObject(getCoord());
        dead = true;
    }
	
}
