package it.unicam.cs.ids.c3.model;

import java.util.List;

public class Corriere implements CorriereInterface{

    private boolean disponibilita;

    private List<Ordine> ordini;

    public boolean isDisponibilita() {
        return disponibilita;
    }

    public int getIDCorriere() {
        return IDCorriere;
    }

    private int IDCorriere;

    public Corriere(int IDCorriere){
        this.IDCorriere = IDCorriere;
        disponibilita = true;
    }

    @Override
    public void aggiornaStatoOrdine(int IDOrdine) {
        for (Ordine o: this.ordini) {
            if(o.getIDOrdine()==IDOrdine){
                o.setStatoOrdine(StatoOrdine.INTRANSITO);
            }
        }
    }

    @Override
    public void cambiaDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    @Override
    public String visualizzaDestinazione() {
        //TODO implementare
        return null;
    }

    @Override
    public List<Ordine> visualizzaConsegne() {
        //TODO implementare
        return null;
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void addOrdine(Ordine ordine){
        if(ordine==null) return;
        this.ordini.add(ordine);
    }
}
