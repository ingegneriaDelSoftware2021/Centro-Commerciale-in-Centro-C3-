package it.unicam.cs.ids.c3.model;

import java.util.List;

public interface CommercianteInterface {


    /**
     *
     * @param IDNegozio
     * @param promozione
     */
    void addPromozioni(int IDNegozio, Promozioni promozione);


    /**
     *
     * @param IDNegozio
     * @param IDPromozione
     */
    void eliminaPromozione(int IDNegozio,int IDPromozione);

    /**
     *
     * @param IDNegozio
     * @param IDPromozione
     * @param nuovoStato
     */
    void modificaStatoPromozione(int IDNegozio, int  IDPromozione, boolean nuovoStato);

    /**
     *
     * @param IDNegozio
     * @param IDPromozioni
     * @param nuovoSconto
     */
    void modificaScontoPromozione(int IDNegozio, int IDPromozioni, double nuovoSconto);


    /**
     *
     * @param cliente
     */
    void creaOrdine(Cliente cliente);


    /**
     *
     * @param listaCorrieri
     * @return
     */
    List<Corriere> getCorrieriDisponibili(ListaCorrieri listaCorrieri);

    /**
     *
     * @param indirizzo
     * @param IDOrdine
     * @return
     */
    void selezionaDestinazioneCasa(String indirizzo, int IDOrdine);


    /**
     *
     * @param indirizzoLocker
     * @param IDOrdine
     */
    void selezionaDestinazioneLocker(String indirizzoLocker, int IDOrdine);


    /**
     *
     * @return
     */
    ListaLockers getListaLocker();

    /**
     *
     * @param ordine
     */
    void aggiornaStatoOrdine(Ordine ordine);

    /**
     *
     * @param IDCorriere
     * @param IDOrdine
     * @return
     */
    String selezionaCorriere(int  IDCorriere, int IDOrdine);







}
