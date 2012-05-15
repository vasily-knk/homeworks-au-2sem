package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.DynamicObject;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;

public abstract class Walking implements FieldObject, DynamicObject {
    protected final Field field;
	private Coord coord;

    public Walking(Field field, Coord coord) {
        this.field = field;
        this.coord = coord;
    }
	
	protected Coord getCoord() {
        return coord;
	}
    
	protected void moveTo(Coord c) {
        if (c == coord)
            return;

        field.removeObject(coord);
        coord = c;
		field.setObject(coord, this);
	}
}
