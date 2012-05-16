package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.Arrestable;
import ru.spbau.kononenko.drunkgame.Static.Bottle;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.Property;
import ru.spbau.kononenko.drunkgame.Static.Pillar;

import java.util.List;
import java.util.Random;

public class Drunk extends Walking implements Arrestable {
    private static final int BOTTLE_DROP_PROBABILITY = 30;
    
    private Random random = new Random();
    private boolean isFrozen = false;
    private boolean isSleeping = false;
    private boolean hasBottle = true;
    private boolean isArrested = false;

    public static Property sleepingDrunkProperty = Property.createProperty();

    public Drunk(Field field, Coord coord) {
        super(field, coord);
    }

    @Override
    public boolean getProperty(Property property) {
        if (property == Pillar.freezeProperty)
            return isFrozen;
        else if (property == sleepingDrunkProperty)
            return isSleeping;

        return false;
    }

    @Override
    public char getChar() {
        if (isFrozen)
            return '1';
        if (isSleeping)
            return '&';
        return '@';
    }

    @Override
    public void update() {
        List<Coord> directions = field.getAdjacent(getCoord());
        int index = random.nextInt(directions.size());
        Coord dir = directions.get(index);

        FieldObject other = field.getObject(dir);
        if(other == null)
            moveTo(dir);
        else if (other.getProperty(Pillar.freezeProperty))
            freeze();
        else if (other.getProperty(Bottle.bottleProperty))
            attemptBottlePickup(dir);
    }



    @Override
    public boolean isActive() {
        return (!isFrozen && !isSleeping);
    }

    private void freeze() {
        isFrozen = true;
    }

    @Override
    protected void moveTo(Coord c) {
        Coord oldCoord = getCoord();
        super.moveTo(c);
        if (hasBottle)
            attemptBottleDrop(oldCoord);
    }

    private void attemptBottleDrop(Coord oldCoord) {
        if (random.nextInt(BOTTLE_DROP_PROBABILITY) == 0) {
            field.setObject(oldCoord, new Bottle());
            hasBottle = false;
        }
    }
    private void attemptBottlePickup(Coord dir) {
        field.removeObject(dir);
        super.moveTo(dir);
        isSleeping = true;
    }

    @Override
    public void arrest(Policeman policeman) {
        isArrested = true;
        field.removeObject(coord);
    }

    @Override
    public boolean isDead() {
        return isArrested;
    }
}
