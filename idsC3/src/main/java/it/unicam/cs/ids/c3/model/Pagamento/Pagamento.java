package it.unicam.cs.ids.c3.model.Pagamento;

import it.unicam.cs.ids.c3.model.Clienti.ClienteInterface;
import it.unicam.cs.ids.c3.model.Clienti.ListaClienti;
import it.unicam.cs.ids.c3.model.Esercente.Commerciante;
import it.unicam.cs.ids.c3.model.Esercente.ListaNegozi;

import java.util.Objects;

/**
 * Questa classe è un'implementazione di default dell'interfaccia PagamentoInterface.
 * Permette di effettuare un pagamaneto tra Cliente e commerciante.
 */
public class Pagamento implements PagamentoInterface {


    private int IDCliente;
    private int IDCommerciante;
    private float totale;

    public Pagamento(){
        //TODO da finire questa classe.
    }


    @Override
    public String pagamento(int IDCliente, int IDCommerciante, float importo) {
        ClienteInterface cliente = ListaClienti.getInstance().getCliente(IDCliente);
        Commerciante commerciante = Objects.requireNonNull(ListaNegozi.
                getInstance().
                getNegozi(IDCommerciante).
                stream().
                findFirst().orElse(null)).
                getPropietario();
        boolean esitoInvio = cliente.inviaPagamento(cliente.getCarrello());
        if(!esitoInvio) return rifiuto();
        commerciante.riceviPagamento(IDCliente,importo);
        return conferma();
    }
}
