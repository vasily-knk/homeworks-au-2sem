package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.Property;

public class Streetlight implements FieldObject {
    @Override
    public boolean getProperty(Property property) {
        return false;
    }

    @Override
    public char getChar() {
        return 'Ф';
    }
}
