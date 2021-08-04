package it.unicam.cs.ids.c3.model.Corriere;

import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;
import it.unicam.cs.ids.c3.model.Utente.Utente;

import java.util.ArrayList;
import java.util.List;

public class Corriere extends Utente implements CorriereInterface {

    private boolean disponibilita;

    private List<Ordine> ordini;

    private int IDCorriere;

    public Corriere(int IDCorriere, String nome, String indirizzo){
        super();
        this.IDCorriere = IDCorriere;
        disponibilita = true;
        ordini = new ArrayList<>();
    }



    public int getIDCorriere() {
        return IDCorriere;
    }

    @Override
    public List<Ordine> getListaOrdine() {
        return null;
    }

    @Override
    public Ordine getOrdine(int IDOrdine) {
        return null;
    }

    @Override
    public void addOrdine(int IDOrdine) {

    }

    @Override
    public boolean getDisponibilita() {
        return this.disponibilita;
    }

    @Override
    public void setDisponibilita(boolean nuovaDisponibilita) {
        this.disponibilita = nuovaDisponibilita;
    }
}
