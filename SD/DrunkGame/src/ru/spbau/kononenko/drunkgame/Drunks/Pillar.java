package ru.spbau.kononenko.drunkgame.drunks;

import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObjectProperty;
import ru.spbau.kononenko.drunkgame.common.field.objects.StaticFieldObject;

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
