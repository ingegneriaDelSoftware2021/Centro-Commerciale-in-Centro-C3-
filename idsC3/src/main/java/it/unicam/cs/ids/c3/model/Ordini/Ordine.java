package it.unicam.cs.ids.c3.model.Ordini;

import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Corriere.Corriere;

public class Ordine implements OrdineInterface{

    private final int IDCliente;

    private StatoOrdine statoOrdine;

    private String destinazione;

    private  int IDOrdine;

    private Corriere corriere;

    private final Carrello carrello;


    /**
     * destinazione casa cliente
     * @param idCliente
     * @param statoOrdine
     * @param destinazione
     */
    public Ordine(int idCliente, StatoOrdine statoOrdine, String destinazione) {
        IDCliente = idCliente;
        this.statoOrdine = statoOrdine;
        this.destinazione = destinazione;
        statoOrdine = StatoOrdine.ORDINECREATO;
        //TODO aggiustare questo costruttore
        this.carrello = null;
    }

    /**
     * destinazione ancora da scoprire.
     * @param idCliente
     * @param statoOrdine
     */
    public Ordine(int idCliente, StatoOrdine statoOrdine){
        this.IDCliente = idCliente;
        this.statoOrdine = statoOrdine;
        this.carrello = null;
        this.destinazione = "";
        //TODO completare questo costruttore
    }

    /**
     * destinazione locker
     * @param idCliente
     * @param statoOrdine
     * @param IDLocker
     */
    public Ordine(int idCliente, StatoOrdine statoOrdine, int IDLocker){
        this.IDCliente = idCliente;
        this.statoOrdine = statoOrdine;
        this.carrello = null;
        //TODO cercare sulla lista di locker l'id e recuperare l'indirizzo.
    }

    public Ordine(Carrello carrello){
        this.IDCliente = carrello.getIDCarrello();
        this.statoOrdine = StatoOrdine.ORDINECREATO;
        this.carrello = carrello;
    }

    /**
     *
     * @return
     */
    public int getIDCliente() {
        return IDCliente;
    }

    /**
     *
     * @return
     */
    public StatoOrdine getStatoOrdine() {
        return statoOrdine;
    }

    /**
     *
     * @return
     */
    public String getDestinazione() {
        return destinazione;
    }

    /**
     *
     * @return
     */
    public int getIDOrdine() {
        return IDOrdine;
    }


    /**
     *
     * @param statoOrdine
     */
    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void setCorriere(Corriere corriere) {
        this.corriere = corriere;
    }

    public void setDestinazione(String destinazione){
        this.destinazione = destinazione;
    }
}
