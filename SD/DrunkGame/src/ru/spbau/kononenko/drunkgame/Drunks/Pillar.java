package ru.spbau.kononenko.drunkgame.Drunks;

import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.FieldObjectProperty;
import ru.spbau.kononenko.drunkgame.Field.StaticFieldObject;
import ru.spbau.kononenko.drunkgame.Freeze;

public class Pillar extends StaticFieldObject {

	@Override
	public boolean getProperty(FieldObjectProperty property) {
		if (property == Freeze.freezeProperty)
			return true;
		
		return false;
	}
	
	@Override
	public char getChar() {
		return '#';
	}

}
