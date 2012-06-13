package ru.spbau.kononenko.drunkgame.game;

import ru.spbau.kononenko.drunkgame.beggar.BottlesRC;
import ru.spbau.kononenko.drunkgame.common.actors.DynamicObject;
import ru.spbau.kononenko.drunkgame.drunks.Bar;
import ru.spbau.kononenko.drunkgame.drunks.Pillar;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.hex_field.HexField;
import ru.spbau.kononenko.drunkgame.police.PoliceDept;
import ru.spbau.kononenko.drunkgame.police.reporters.Streetlight;

import java.util.LinkedList;
import java.util.List;

public class Game implements DynamicControl {
    private final List<DynamicObject> objectsList = new LinkedList<DynamicObject>();
    private final List<DynamicObject> objectsToAdd = new LinkedList<DynamicObject>();

    private final Field field;
    private int turn = 0;

    public Game () {
        field = new HexField(15, 15);
        objectsList.add(new Bar(field, new Coord(9, 0), this, 20));
        field.setObject(new Coord(7, 7), new Pillar());
        
        PoliceDept dept = new PoliceDept(field, new Coord(14, 3), this);
        objectsList.add(dept);

        Streetlight streetlight = new Streetlight(field, new Coord(10, 3), 3);
        dept.addSearcher(streetlight);

        BottlesRC rc = new BottlesRC(field, new Coord(4, 14), this, 40);
        objectsList.add(rc);
    }

    @Override
    public void add(DynamicObject object) {
        objectsToAdd.add(object);
    }

    public void update() {
        for (DynamicObject object : objectsList) {
            if (!object.isDead() && object.isActive())
                object.update();
        }

        objectsList.addAll(objectsToAdd);
        objectsToAdd.clear();

        ++turn;
    }
    
    public void output() {
        System.out.println("Turn " + turn);
        field.draw();
    }

}
