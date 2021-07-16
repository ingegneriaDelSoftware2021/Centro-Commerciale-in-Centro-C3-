package it.unicam.cs.ids.c3.model;

public class Ordine {

    private final int IDCliente;

    private StatoOrdine statoOrdine;

    private final String destinazione;

    private final int codiceRitiro;


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
        this.codiceRitiro = codiceRitiro;
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
    public int getCodiceRitiro() {
        return codiceRitiro;
    }


    /**
     *
     * @param statoOrdine
     */
    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }
}
