package it.unicam.cs.ids.c3.model;

public class Armadietto {
    private Ordine ordine;
    private int IDArmadietto;

    public Armadietto(){
        //TODO implementare
    }

    public Ordine getOrdine(){
        return ordine;
    }

    public void removeOrdine(){
        ordine = null;
    }

    public void aggiungiOrdine(Ordine ordine){
        if(ordine==null) return;
        this.ordine = ordine;
    }

}
