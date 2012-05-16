package ru.spbau.kononenko.drunkgame.Field;

import ru.spbau.kononenko.drunkgame.SelfAwareFieldObject;

public abstract class SelfAwareImpl implements SelfAwareFieldObject {
    protected final Field field;
    protected Coord coord;
    
    public SelfAwareImpl(Field field, Coord coord) {
        this.field = field;
        this.coord = coord;
        field.setObject(coord, this);
    }

    public Coord getCoord() {
        return coord;
    }

    public Field getField() {
        return field;
    }

    protected void moveTo(Coord c) {
        field.moveObject(coord, c);
        coord = c;
    }
}
