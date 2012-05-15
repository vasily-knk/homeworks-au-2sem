package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.Property;

public class Pillar implements FieldObject {
    public final static Property freezeProperty = Property.createProperty();

	@Override
	public boolean getProperty(Property property) {
		if (property == freezeProperty)
			return true;
		
		return false;
	}
	
	@Override
	public char getChar() {
		return '#';
	}

}
