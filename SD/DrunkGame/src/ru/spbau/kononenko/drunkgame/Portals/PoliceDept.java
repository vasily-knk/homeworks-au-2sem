package ru.spbau.kononenko.drunkgame.Portals;

import ru.spbau.kononenko.drunkgame.BFS;
import ru.spbau.kononenko.drunkgame.Dynamic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Static.Streetlight;

import java.util.LinkedList;
import java.util.List;

public class PoliceDept extends Portal {
    List<Streetlight> streetlights = new LinkedList<Streetlight>();
    boolean policemanIsOut = false;

    public PoliceDept(Field field, Coord coord, DynamicControl dynamicControl) {
        super(field, coord, dynamicControl);
    }

    public void addStreetlight(Streetlight streetlight) {
        streetlights.add(streetlight);
    }

    @Override
    public void update() {
        for (Streetlight streetlight : streetlights) {
            Coord res = streetlight.searchForDrunk();
            if (res != null) {
                break;
            }
        }
    }


    @Override
    public boolean isActive() {
        return !policemanIsOut;
    }
}
