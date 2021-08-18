package it.unicam.cs.ids.c3.model.Lockers;


import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;

import java.util.Set;

/**
 * Questa classe &egrave; un'implementazione di default di un locker.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Locker implements LockerInterface{

    private Set<Armadietto> armadietti;

    private final int IDLocker;

    private String indirizzo;

    /**
     * Questo &egrave; un costruttore di default.
     * @param armadietti insieme di armadietti che ha questo locker.
     * @param idLocker id del locker.
     * @param indirizzo indirizzo del locker.
     */
    public Locker(Set<Armadietto> armadietti, int idLocker, String indirizzo) {
        this.armadietti = armadietti;
        this.IDLocker = idLocker;
        this.indirizzo = indirizzo;
    }




    @Override
    public Set<Armadietto> visualizzaArmadietti() {
        return armadietti;
    }

    @Override
    public int cercaOrdine(int IDOrdine) {
        for(Armadietto a : armadietti){
            if(a.getOrdine().getIDOrdine()==IDOrdine) return a.getOrdine().getIDOrdine();
        }
        return -1;
    }

    @Override
    public void aggiornaStatoOrdine(int IDOrdine) {
        for(Armadietto a : armadietti){
            if(a.getOrdine().getIDOrdine()==IDOrdine) {
                a.getOrdine().setStatoOrdine(StatoOrdine.DARITIRARE);
            }
        }
    }

    @Override
    public void aggiungiOrdine(Ordine ordine) {
        if(ordine==null) return;
        for(Armadietto a : this.armadietti){
            if(a.getOrdine()==null) a.aggiungiOrdine(ordine);
        }
    }

    /**
     * Questo metodo restituisce l'id del locker.
     * @return id del locker.
     */
    public int getID(){
        return IDLocker;
    }
}
