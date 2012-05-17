package ru.spbau.kononenko.drunkgame.Field;

import ru.spbau.kononenko.drunkgame.Logic.SelfAware;

public abstract class SelfAwareFieldObject implements SelfAware, FieldObject {
    private final Field field;
    private Coord coord;
    
    public SelfAwareFieldObject(Field field, Coord coord) {
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
