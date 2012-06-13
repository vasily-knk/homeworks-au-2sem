package ru.spbau.kononenko.drunkgame.common.field.objects;

import ru.spbau.kononenko.drunkgame.common.actors.SelfAware;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;

public abstract class SelfAwareFieldObject implements SelfAware, FieldObject {
    private final Field field;
    private Coord coord;
    boolean allowMovement = false;
    Coord newCoord;
    
    public SelfAwareFieldObject(Field field, Coord coord) {
        this.field = field;
        this.coord = coord;
        allowMovement = true;
        field.setObject(coord, this);
        allowMovement = false;
    }

    public Coord getCoord() {
        return coord;
    }

    public Field getField() {
        return field;
    }

    protected void moveTo(Coord c) {
        allowMovement = true;
        try {
            field.moveObject(coord, c);
        } finally {
            allowMovement = false;
        }
        coord = c;
    }

    protected void remove() {
        allowMovement = true;
        field.removeObject(coord);
        coord = null;
        allowMovement = false;
    }

    @Override
    public void onRemove(Coord coord) {
        if (!allowMovement)
            throw new MovingNotAllowedException("Remove");
    }

    @Override
    public void onPlace(Coord coord) {
        if (!allowMovement)
            throw new MovingNotAllowedException("Place");
    }
}
