package it.unicam.cs.ids.c3.model.Ordini;

import it.unicam.cs.ids.c3.model.Corriere.Corriere;

public interface OrdineInterface {

    /**
     *
     * @return
     */
    public int getIDCliente();

    /**
     *
     * @return
     */
    public StatoOrdine getStatoOrdine();

    /**
     *
     * @return
     */
    public String getDestinazione();

    /**
     *
     * @return
     */
    int getIDOrdine();


    /**
     *
     * @param statoOrdine
     */
    public void setStatoOrdine(StatoOrdine statoOrdine);

    public Corriere getCorriere();

    public void setCorriere(Corriere corriere);

    public void setDestinazione(String destinazione);
}
