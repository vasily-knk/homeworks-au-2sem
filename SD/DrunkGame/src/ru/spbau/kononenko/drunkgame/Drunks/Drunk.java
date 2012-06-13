package ru.spbau.kononenko.drunkgame.drunks;

import ru.spbau.kononenko.drunkgame.common.actors.Actor;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObjectProperty;
import ru.spbau.kononenko.drunkgame.police.arrestable.Arrestable;
import ru.spbau.kononenko.drunkgame.police.policeman.Policeman;

import java.util.List;
import java.util.Random;

public class Drunk extends Actor implements Arrestable {
    private static final int BOTTLE_DROP_PROBABILITY = 30;
    
    private Random random = new Random();
    private boolean isFrozen = false;
    private boolean isSleeping = false;
    private boolean hasBottle = true;

    public static FieldObjectProperty sleepingDrunkProperty = FieldObjectProperty.createProperty();

    public Drunk(Field field, Coord coord) {
        super(field, coord);
    }

    @Override
    public boolean getProperty(FieldObjectProperty property) {
        if (property == Freeze.freezeProperty)
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
        
        if (directions.isEmpty())
            return;
        
        int index = random.nextInt(directions.size());
        Coord dir = directions.get(index);

        FieldObject other = getField().getObject(dir);
        if(other == null)
            moveTo(dir);
        else if (other.getProperty(Freeze.freezeProperty))
            freeze();
        else if (other.getProperty(Bottle.bottleProperty))
            pickupBottle(dir);
    }



    @Override
    public boolean isActive() {
        return (!isFrozen && !isSleeping);
    }

    public void freeze() {
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

    private void pickupBottle(Coord dir) {
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

    public void setRandom(Random random) {
        this.random = random;
    }
}
