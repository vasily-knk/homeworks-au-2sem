package ru.spbau.kononenko.drunkgame.drunks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.spbau.kononenko.drunkgame.common.actors.MovingDeadActorException;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.FieldOccupiedException;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;
import ru.spbau.kononenko.drunkgame.rect_field.RectField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class DrunkTest {
    private Field field;
    private Drunk drunk;

    final Coord startCoord = new Coord(5, 5);
    final Coord adjCoord = new Coord(5, 6);

    @Before
    public void setUp() throws Exception {
        field = spy(new RectField(10, 10));
        
        List<Coord> mockAdj = new ArrayList<Coord>();
        mockAdj.add(adjCoord);
        
        when(field.getAdjacent(startCoord)).thenReturn(mockAdj);
                
        drunk = new Drunk(field, startCoord);

        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt(anyInt())).thenReturn(0);
        drunk.setRandom(mockRandom);
    }
    
    @Test
    public void bottleDrop() {
        Coord oldCoord = drunk.getCoord();
        drunk.update();
        FieldObject bottle = field.getObject(oldCoord);
        assertNotNull(bottle);
    }

    @Test
    public void bottlePickup() {
        field.setObject(adjCoord, new Bottle());
        assertFalse(drunk.getProperty(Drunk.sleepingDrunkProperty));
        drunk.update();
        assertTrue(drunk.getProperty(Drunk.sleepingDrunkProperty));
        assertEquals(drunk.getCoord(), adjCoord);
    }

    @Test
    public void pillar() {
        field.setObject(adjCoord, new Pillar());
        assertFalse(drunk.getProperty(Freeze.freezeProperty));
        drunk.update();
        assertTrue(drunk.getProperty(Freeze.freezeProperty));
        assertEquals(drunk.getCoord(), startCoord);
    }
    

    @Test
    public void singleStep() {
        drunk.update();
        assertEquals(drunk.getCoord(), adjCoord);
        assertEquals(field.getObject(adjCoord), drunk);
        Mockito.verify(field).moveObject(startCoord, adjCoord);
    }
    
    @Test
    public void anotherDrunk() {
        new Drunk(field, adjCoord);
        drunk.update();
        assertEquals(drunk.getCoord(), startCoord);
    }

    @Test (expected = MovingDeadActorException.class)
    public void walkingDead() {
        drunk.kill();
        drunk.moveTo(adjCoord);
    }

}
