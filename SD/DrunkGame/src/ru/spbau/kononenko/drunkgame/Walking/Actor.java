package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.Dynamic.DynamicObject;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.SelfAwareImpl;

public abstract class Actor extends SelfAwareImpl implements DynamicObject {
    private boolean dead = false;

    public Actor(Field field, Coord coord) {
        super (field, coord);
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    public void kill() {
        field.removeObject(coord);
        dead = true;
    }
	
}
