package it.unicam.cs.ids.c3.model;

public class Ordine {

    private final int IDCliente;

    private StatoOrdine statoOrdine;

    private String destinazione;

    private final int IDOrdine;

    private Corriere corriere;



    /**
     *
     * @param idCliente
     * @param statoOrdine
     * @param destinazione
     * @param codiceRitiro
     */
    public Ordine(int idCliente, StatoOrdine statoOrdine, String destinazione, int codiceRitiro) {
        IDCliente = idCliente;
        this.statoOrdine = statoOrdine;
        this.destinazione = destinazione;
        this.IDOrdine = codiceRitiro;
        statoOrdine = StatoOrdine.INPREPARAZIONE;
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
