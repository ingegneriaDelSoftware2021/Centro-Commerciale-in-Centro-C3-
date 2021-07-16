package it.unicam.cs.ids.c3.model;

import java.util.Set;

public class Locker implements LockerInterface{

    private Set<Armadietto> armadietti;

    private final int IDLocker;

    private String indirizzo;

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
    public Armadietto cercaOrdine(int IDOrdine) {
        return null;
    }

    @Override
    public void aggiornaStatoOrdine(int IDOrdine) {

    }

    @Override
    public void aggiungiOrdine(int IDOrdine) {

    }
}
