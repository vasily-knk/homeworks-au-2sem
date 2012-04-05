package ru.spbau.kononenko.drunkgame;

public class Policeman extends Walking {
    Policeman(Field field, Coord coord) {
        super(field, coord);
    }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isActive() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getProperty(Property property) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public char getChar() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
