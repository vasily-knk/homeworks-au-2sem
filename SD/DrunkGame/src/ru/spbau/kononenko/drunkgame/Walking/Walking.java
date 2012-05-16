package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.Dynamic.DynamicObject;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.SelfAwareImpl;

public abstract class Walking extends SelfAwareImpl implements DynamicObject {

    public Walking(Field field, Coord coord) {
        super (field, coord);
    }

    @Override
    public boolean isDead() {
        return false;
    }
	
}
