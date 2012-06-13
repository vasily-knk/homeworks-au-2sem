package ru.spbau.kononenko.drunkgame.police;

import ru.spbau.kononenko.drunkgame.common.actors.ReturnReportInterface;
import ru.spbau.kononenko.drunkgame.game.DynamicControl;
import ru.spbau.kononenko.drunkgame.common.actors.Portal;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.police.arrestable.Arrestable;
import ru.spbau.kononenko.drunkgame.police.policeman.Policeman;
import ru.spbau.kononenko.drunkgame.police.reporters.ArrestableReporter;

import java.util.LinkedList;
import java.util.List;

public class PoliceDept extends Portal {
    List<ArrestableReporter> reporters = new LinkedList<ArrestableReporter>();
    boolean policemanIsOut = false;

    public PoliceDept(Field field, Coord coord, DynamicControl dynamicControl) {
        super(field, coord, dynamicControl);
    }

    public void addSearcher(ArrestableReporter reporter) {
        reporters.add(reporter);
    }

    @Override
    public void update() {
        for (ArrestableReporter reporter : reporters) {
            Arrestable res = reporter.search();
            if (res != null) {
                tryToSendPoliceman(res);
                break;
            }
        }
    }

    private void tryToSendPoliceman(Arrestable target) {
        policemanIsOut = true;
        if (canSpawn()) {
            ReturnReportInterface onReturn = new ReturnReportInterface() {
                @Override
                public void report() {
                    policemanIsOut = false;
                }
            };
            spawn(new Policeman(field, coord, onReturn, target));
        }
    }

    @Override
    public boolean isActive() {
        return !policemanIsOut;
    }

}
