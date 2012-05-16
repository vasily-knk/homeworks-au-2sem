package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.Dynamic.DynamicObject;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.SelfAwareFieldObject;

public abstract class Walking extends SelfAwareFieldObject implements DynamicObject {

    public Walking(Field field, Coord coord) {
        super (field, coord);
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
