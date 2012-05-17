package ru.spbau.kononenko.drunkgame.Drunks;

import ru.spbau.kononenko.drunkgame.Logic.Actor;
import ru.spbau.kononenko.drunkgame.Police.Arrestable;
import ru.spbau.kononenko.drunkgame.Police.Policeman;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.FieldObjectProperty;

import java.util.List;
import java.util.Random;

public class Drunk extends Actor implements Arrestable {
    private static final int BOTTLE_DROP_PROBABILITY = 30;
    
    private final Random random = new Random();
    private boolean isFrozen = false;
    private boolean isSleeping = false;
    private boolean hasBottle = true;

    public static FieldObjectProperty sleepingDrunkProperty = FieldObjectProperty.createProperty();

    public Drunk(Field field, Coord coord) {
        super(field, coord);
    }

    @Override
    public boolean getProperty(FieldObjectProperty property) {
        if (property == Pillar.freezeProperty)
            return isFrozen;
        else if (property == sleepingDrunkProperty)
            return isSleeping;

        return false;
    }

    @Override
    public char getChar() {
        if (isSleeping)
            return '&';
        if (isFrozen)
            return '1';
        return '@';
    }

    @Override
    public void update() {
        List<Coord> directions = getField().getAdjacent(getCoord());
        int index = random.nextInt(directions.size());
        Coord dir = directions.get(index);

        FieldObject other = getField().getObject(dir);
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
            getField().setObject(oldCoord, new Bottle());
            hasBottle = false;
        }
    }
    private void attemptBottlePickup(Coord dir) {
        getField().removeObject(dir);
        super.moveTo(dir);
        fallAsleep();
    }

    @Override
    public void arrest(Policeman policeman) {
        kill();
    }

    public void fallAsleep() {
        isSleeping = true;
    }
}
