package ru.spbau.kononenko.drunkgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Drunk extends Walking implements DynamicObject {
    private final List<Coord> streetlightLocations;
    private final DrunkReportInterface reportInterface;
    private final FieldGeometry geometry;
    private final Random random;
    
    Drunk(Field field, FieldGeometry geometry, Coord coord, List<Coord> streetlightLocations, DrunkReportInterface reportInterface) {
        super(field, coord);
        this.geometry = geometry;
        this.streetlightLocations = streetlightLocations;
        this.reportInterface = reportInterface;
        this.random = new Random();
    }

    @Override
    public boolean getProperty(Property property) {
        return false;
    }

    @Override
    public char getChar() {
        return '&';
    }

    @Override
    public void update() {
        List<Coord> directions = geometry.getAdjacent(getCoord());
        int index = random.nextInt(directions.size());
        Coord dir = directions.get(index);
        if(getField().getObject(dir) == null)
            moveTo(dir);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
