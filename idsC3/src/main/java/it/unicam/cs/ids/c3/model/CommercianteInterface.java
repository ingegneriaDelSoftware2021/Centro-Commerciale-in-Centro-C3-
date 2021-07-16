package it.unicam.cs.ids.c3.model;

public interface CommercianteInterface {


    /**
     *
     * @param negozio
     * @param p
     */
    void addPromozioni(Negozio negozio, Promozioni p);


    /**
     *
     * @param IDNegozio
     * @param promozioni
     */
    void eliminaPromozione(int IDNegozio,Promozioni promozioni);

    /**
     *
     * @param IDNegozio
     * @param promozione
     * @param nuovoStato
     */
    void modificaStatoPromozione(int IDNegozio, Promozioni promozione, boolean nuovoStato);

    /**
     *
     * @param IDNegozio
     * @param promozioni
     * @param nuovoSconto
     */
    void modificaScontoPromozione(int IDNegozio, Promozioni promozioni, double nuovoSconto);


    /**
     *
     */
    void creaOrdine();


    /**
     *
     * @param listaCorrieri
     * @return
     */
    Corriere selezionaCorriere(ListaCorrieri listaCorrieri);

    /**
     *
     * @param cliente
     * @return
     */
    String selezionaDestinazione(Cliente cliente);


    void aggiornaStatoOrdine(Ordine ordine);







}
