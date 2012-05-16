package ru.spbau.kononenko.drunkgame.Portals;

import ru.spbau.kononenko.drunkgame.Dynamic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Dynamic.DynamicObject;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Walking.Walking;

public abstract class Portal implements DynamicObject {
    protected final Field field;
    protected final Coord coord;
    protected final DynamicControl dynamicControl;

    public Portal(Field field, Coord coord, DynamicControl dynamicControl) {
        this.field = field;
        this.coord = coord;      
        this.dynamicControl = dynamicControl; 
    }

    public boolean canSpawn() {
        return field.getObject(coord) == null;
    }

    public void spawn (Walking object) {
        field.setObject(coord, object);
        dynamicControl.add(object);
    }
}
