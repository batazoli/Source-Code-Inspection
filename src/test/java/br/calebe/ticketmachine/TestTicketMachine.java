package br.calebe.ticketmachine;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.*;

import br.calebe.ticketmachine.core.TicketMachine;

public class TestTicketMachine {
    @Test
    public void TestInserir() throws Throwable{
        HashMap<Integer, Integer> saldo = new HashMap<Integer, Integer>(){{
            put(2, 4);
            put(5, 2);
            put(10, 1);
        }};

        TicketMachine underTest = new TicketMachine(3, saldo);

        underTest.inserir(5);
        int result = underTest.getSaldo();

        assertEquals(5, result);
    }
}
