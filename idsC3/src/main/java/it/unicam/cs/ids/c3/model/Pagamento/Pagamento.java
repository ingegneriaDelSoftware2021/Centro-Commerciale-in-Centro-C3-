package it.unicam.cs.ids.c3.model.Pagamento;

/**
 * Questa classe è un'implementazione di default dell'interfaccia PagamentoInterface.
 * Permette di effettuare un pagamaneto tra Cliente e commerciante.
 */
public class Pagamento implements PagamentoInterface {


    private int IDCliente;
    private int IDCommerciante;
    private float totale;

    public Pagamento(){
        IDCliente = -1;
        IDCommerciante = -1;
        totale = -1;
    }


    @Override
    public String pagamento(int IDCliente, int IDCommerciante, float importo) {
        return null;
    }
}
