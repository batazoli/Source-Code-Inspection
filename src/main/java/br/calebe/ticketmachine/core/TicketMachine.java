package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import br.calebe.ticketmachine.exception.TrocoInsuficienteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class TicketMachine {

    protected int valor;
    protected int saldo;
    protected int[] papelMoeda = {2, 5, 10, 20, 50, 100};
    protected Map<Integer, Integer> saldoMaquina;

    public TicketMachine(int valor, HashMap<Integer, Integer> saldoMaquina) {
        this.valor = valor;
        this.saldo = 0;
        this.saldoMaquina = saldoMaquina;
    }

    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean achou = false;
        for (int i = 0; i < papelMoeda.length && !achou; i++) {
            if (papelMoeda[i] == quantia) {
                saldoMaquina.replace(papelMoeda[i], saldoMaquina.get(papelMoeda[i]) + 1);
                achou = true;
            }
        }
        if (!achou) {
            throw new PapelMoedaInvalidaException();
        }
        this.saldo += quantia;
    }

    public int getSaldo() {
        return saldo;
    }

    public List<PapelMoeda> getTroco() throws TrocoInsuficienteException {
        Iterator<PapelMoeda> trocoIterator = new Troco(saldo).getIterator();
        List<PapelMoeda> troco = new ArrayList<PapelMoeda>();
        int somaTroco = 0;

        while (trocoIterator.hasNext()) {
            PapelMoeda retorno = trocoIterator.next();

            if(saldoMaquina.get(retorno.valor) < retorno.quantidade){
                throw new TrocoInsuficienteException();
            }

            saldoMaquina.replace(retorno.valor, saldoMaquina.get(retorno.valor) - retorno.quantidade);
            
            troco.add(retorno);

            somaTroco += retorno.quantidade * retorno.valor;
        }

        if(somaTroco != this.saldo)
            throw new TrocoInsuficienteException();

        this.saldo -= somaTroco;

        return troco;
    }

    public String imprimir() throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException();
        }

        saldo -= valor;
        String result = "*****************\n";
        result += "*** R$ " + saldo + ",00 ****\n";
        result += "*****************\n";
        return result;
    }
}
