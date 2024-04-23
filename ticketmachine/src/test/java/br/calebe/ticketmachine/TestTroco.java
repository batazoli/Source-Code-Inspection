package br.calebe.ticketmachine;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import java.util.Iterator;
import br.calebe.ticketmachine.core.*;

public class TestTroco {
    @Test
    public void TestHasNext() throws Throwable{
        Iterator<PapelMoeda> underTest = new Troco(55).getIterator();

        boolean result = underTest.hasNext();

        assertEquals(true, result);
    }
}
