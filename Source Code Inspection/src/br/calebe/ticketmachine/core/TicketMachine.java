package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class TicketMachine {

    protected int valor;
    protected int saldo;
    protected int[] papelMoeda = {2, 5, 10, 20, 50, 100};
    protected Map<PapelMoeda> saldoMaquina;

    public TicketMachine(int valor, HashMap saldoMaquina) {
        this.valor = valor;
        this.saldo = 0;
        this.saldoMaquina = saldoMaquina;
    }

    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean achou = false;
        for (int i = 0; i < papelMoeda.length && !achou; i++) {
            if (papelMoeda[i] == quantia) {
                saldoMaquina.replace(papelMoeda[i], saldoMaquina.get(papelMoeda[i]).quantidade + 1);
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

    public Iterator<PapelMoeda> getTroco() {
        Iterator trocoIterator = new Troco(saldo).getIterator();
        PapelMoeda troco = null;

        if (trocoIterator.hasNext()) {
            PapelMoeda retorno = trocoIterator.next();

            if(saldoMaquina.get(retorno.valor).quantidade < retorno.quantidade){
                throw new TrocoInsuficienteException();
            }

            saldoMaquina.replace(retorno.valor, saldoMaquina.get(retorno.valor).quantidade - retorno.quantidade);
            
            troco = retorno;
        }

        if(troco != this.saldo)
            throw new TrocoInsuficienteException();

        if(troco != null)
            this.saldo -= troco.quantidade * troco.valor;

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
