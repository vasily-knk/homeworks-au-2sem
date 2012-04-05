package ru.spbau.kononenko.drunkgame;

public class Streetlight implements FieldObject {
    Streetlight(Field field, Coord coord) {
        field.setObject(coord, this);
    }

    @Override
    public boolean getProperty(Property property) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public char getChar() {
        return 'Ф';
    }
}
