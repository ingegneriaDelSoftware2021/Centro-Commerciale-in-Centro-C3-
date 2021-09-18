package it.unicam.cs.ids.c3.model.Lockers;

import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;

/**
 *
 * @author Davide Menghini, Francesco Allevi.
 */
public class Armadietto {
    private OrdineInterface ordine;
    private final int IDArmadietto;

    /**
     * Questo &egrave; un costruttore di default.
     * @param IDArmadietto id dell'armadietto.
     */
    public Armadietto(int IDArmadietto, OrdineInterface ordine){
        this.ordine = ordine;
        this.IDArmadietto=IDArmadietto;
    }




    /**
     * Questo metodo restituisce l'ordine nell'armadietto, se presente.
     * @return l'ordine nell'armadietto o null se non &egrave; presente alcun ordine.
     */
    public OrdineInterface getOrdine(){
        return ordine;
    }



    /**
     * Questo metodo permette di aggiugnere l'ordine dall'armadietto.
     * @param ordine id dell'ordine da aggiungere.
     */
    public void aggiungiOrdine(OrdineInterface ordine){
        if(ordine==null) return;
        this.ordine = ordine;
    }

    /**
     * getter dell'id dell'armadietto
     * @return restituisce l'id dell'attuale armadietto.
     */
    public int getIDArmadietto() {
        return IDArmadietto;
    }
}
