package ru.spbau.kononenko.drunkgame.common.actors;

import ru.spbau.kononenko.drunkgame.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.game.DynamicControl;

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

    public void spawn (Actor object) {
        dynamicControl.add(object);
    }

    public boolean isDead() {
        return false;
    }
}
