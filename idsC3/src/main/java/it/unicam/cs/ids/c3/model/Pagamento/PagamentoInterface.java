package it.unicam.cs.ids.c3.model.Pagamento;

/**
 * Questa interfaccia si occupa di definire un pagamneto tra un Cliente e un Commerciante.
 */
public interface PagamentoInterface {

    /**
     * Questo metodo si occupa di effettuare il pagamento tra Cliente e Commerciante.
     * @param IDCliente id del cliente.
     * @param IDCommerciante id del commerciante.
     * @param importo importo.
     * @return se il pagamento è andato bene ritorna il metodo conferma, altrimenti ritorna rifiuto.
     */
    String pagamento(int IDCliente, int IDCommerciante, float importo);

    default String conferma(){
        return "pagamento accettato";
    }

    default String rifiuto(){
        return "pagamento rifiutato";
    }
}
