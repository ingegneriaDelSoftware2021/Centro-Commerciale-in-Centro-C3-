package it.unicam.cs.ids.c3.model;

import java.util.List;

public class Corriere implements CorriereInterface{

    private boolean disponibilita;

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
    public void aggiornaStatoOrdine(StatoOrdine statoOrdine) {
        //TODO implementare
    }

    @Override
    public void cambiaDisponibilita(boolean disponibilita) {
        //TODO implementare
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
}
