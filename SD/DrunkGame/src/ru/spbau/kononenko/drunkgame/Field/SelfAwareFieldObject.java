package ru.spbau.kononenko.drunkgame.Field;

public abstract class SelfAwareFieldObject implements FieldObject {
    protected final Field field;
    protected Coord coord;
    
    public SelfAwareFieldObject(Field field, Coord coord) {
        this.field = field;
        this.coord = coord;
    }

}
