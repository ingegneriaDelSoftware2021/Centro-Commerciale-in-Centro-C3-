package it.unicam.cs.ids.c3.model;

import java.util.Set;

public interface ClienteInterface {
    /**
     *
     * @return
     */
    Set<Negozio> visualizzaNegozi(ListaNegozi listaNegozi);

    /**
     *
     * @return
     */
    Set<Prodotto> visualizzaOrdini();

    /**
     *
     * @return
     */
    Set<Promozioni> visualizzaPromozioni();


    /**
     *
     * @return
     */
    ListaLockers visualizzaMagazzini();

    /**
     *
     * @return
     */
    Carrello visualizzaCarrello();

    /**
     *
     * @param carrello
     * @return
     */
    String checkOutCarrello(Carrello carrello);

    /**
     *
     * @param nomeProdotto
     * @return
     */
    Prodotto cercaProdotto(String nomeProdotto);



}
