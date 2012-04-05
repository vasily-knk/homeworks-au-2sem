package ru.spbau.kononenko.drunkgame;

class Pillar implements FieldObject {
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
