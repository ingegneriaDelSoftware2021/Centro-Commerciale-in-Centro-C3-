package it.unicam.cs.ids.c3.model.Lockers;

import it.unicam.cs.ids.c3.model.Ordini.Ordine;

/**
 *
 * @author Davide Menghini, Francesco Allevi.
 */
public class Armadietto {
    private Ordine ordine;
    private int IDArmadietto;

    /**
     * Questo &egrave; un costruttore di default.
     * @param IDArmadietto id dell'armadietto.
     */
    public Armadietto(int IDArmadietto){
        this.ordine = null;
        this.IDArmadietto=IDArmadietto;
    }




    /**
     * Questo metodo restituisce l'ordine nell'armadietto, se presente.
     * @return l'ordine nell'armadietto o null se non &egrave; presente alcun ordine.
     */
    public Ordine getOrdine(){
        return ordine;
    }

    /**
     * Questo metodo permette di rimuovere l'ordine dall'armadietto.
     */
    public void removeOrdine(){
        ordine = null;
    }

    /**
     * Questo metodo permette di aggiugnere l'ordine dall'armadietto.
     * @param ordine id dell'ordine da aggiungere.
     */
    public void aggiungiOrdine(Ordine ordine){
        if(ordine==null) return;
        this.ordine = ordine;
    }

}
