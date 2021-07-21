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

    public int getID(){
        return IDLocker;
    }
}
