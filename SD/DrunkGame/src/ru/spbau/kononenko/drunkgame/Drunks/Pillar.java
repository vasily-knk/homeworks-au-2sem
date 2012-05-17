package ru.spbau.kononenko.drunkgame.Drunks;

import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.FieldObjectProperty;

public class Pillar implements FieldObject {
    public final static FieldObjectProperty freezeProperty = FieldObjectProperty.createProperty();

	@Override
	public boolean getProperty(FieldObjectProperty property) {
		if (property == freezeProperty)
			return true;
		
		return false;
	}
	
	@Override
	public char getChar() {
		return '#';
	}

}
