package ru.spbau.kononenko.drunkgame.police.reporters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObjectProperty;
import ru.spbau.kononenko.drunkgame.drunks.Drunk;
import ru.spbau.kononenko.drunkgame.rect_field.RectField;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class StreetlightTest {
    final Field field = new RectField(10, 10);
    final Coord streetlightCoord = new Coord(5, 5);
    final int streetlightRadius = 3;
    Streetlight streetlight = new Streetlight(field, streetlightCoord, streetlightRadius);

    @Before
    public void setUp() throws Exception {

    }
    
    @Test
    public void noOneFound() {
        assertNull(streetlight.search());
    }

    @Test
    public void someoneFound() {
        Drunk drunk = new Drunk(field, new Coord(5, 6));
        drunk.fallAsleep();
        assertEquals(streetlight.search(), drunk);
    }
    
}
