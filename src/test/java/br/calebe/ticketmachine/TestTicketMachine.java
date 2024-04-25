package br.calebe.ticketmachine;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.*;

import br.calebe.ticketmachine.core.PapelMoeda;
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

    @Test
    public void TestImprimir() throws Throwable{
        HashMap<Integer, Integer> saldo = new HashMap<Integer, Integer>(){{
            put(2, 4);
            put(5, 2);
            put(10, 1);
        }};

        TicketMachine underTest = new TicketMachine(3, saldo);

        underTest.inserir(5);

        String result = underTest.imprimir();
        String esperado = "*****************\n";
        esperado += "*** R$ " + 2 + ",00 ****\n";
        esperado += "*****************\n";

        assertEquals( esperado, result);
    }

    @Test(timeout = 5000)
    public void TesGetTroco() throws Throwable{
        HashMap<Integer, Integer> saldo = new HashMap<Integer, Integer>(){{
            put(2, 4);
            put(5, 2);
            put(10, 1);
        }};

        TicketMachine underTest = new TicketMachine(3, saldo);

        underTest.inserir(5);

        List<PapelMoeda> result = underTest.getTroco();
        List<PapelMoeda> esperado = new  ArrayList<PapelMoeda>();
        esperado.add(new PapelMoeda( 5, 1));
   
        assertEquals( esperado, result);
    }
}
