package it.unicam.cs.ids.c3.model.Corriere;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Questa classe serve per gestire i corrieri presenti nel database.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaCorrieri {

    private List<Corriere> corrieri;

    /**
     * Questo &egrave; un costruttore di default.
     */
    public ListaCorrieri(){
        corrieri = new ArrayList<>();
    }

    /**
     * Questo metodo restituisce la lista dei corrieri presenti nel database.
     * @return lista dei corrieri presenti nel database.
     */
    public List<Corriere> getCorrieri(){
        return this.corrieri;
    }

    /**
     * Questo metodo serve per aggiungere un nuovo corriere al database.
     * @param corriere il corriere da aggiungere
     */
    public void addCorriere(Corriere corriere){
        if(corriere==null) return;
        this.corrieri.add(corriere);
    }

    /**
     * Questo metodo serve per rimuovere il corriere dal database.
     * @param IDCorriere id del corriere da rimuovere.
     */
    public void removeCorriere(int IDCorriere){
        if(IDCorriere<0) return;
        this.corrieri.removeIf(x -> x.getIDCorriere()==IDCorriere);
    }

    /**
     * Questo metodo restituisce la lista di tutti i corrieri che in questo momento sono disponibili per effettuare una
     * consegna.
     * @return
     */
    public List<Corriere> getCorrieriDisponibili(){
        return this.corrieri.parallelStream().filter(Corriere::getDisponibilita).collect(Collectors.toList());
    }
}
