package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.Arrestable;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.Property;
import ru.spbau.kononenko.drunkgame.PoliceReportInterface;

public class Policeman extends Walking {
    private final Coord target;
    private final Coord home;
    private PoliceReportInterface reportInterface;
    private boolean missionAccomplished = false;

    public Policeman(Field field, Coord coord, Coord target, PoliceReportInterface reportInterface) {
        super(field, coord);
        home = coord;
        this.target = target;
        this.reportInterface = reportInterface;
    }

    @Override
    public void update() {
        Arrestable arrestable = (Arrestable)field.getObject(target);
        arrestable.arrest(this);
        missionAccomplished = true;
        reportInterface.report();
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean getProperty(Property property) {
        return false;
    }

    @Override
    public char getChar() {
        return '!';
    }

    @Override
    public boolean isDead() {
        return missionAccomplished;
    }
}
