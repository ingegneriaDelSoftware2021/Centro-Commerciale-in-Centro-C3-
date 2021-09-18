package it.unicam.cs.ids.c3.model.Corriere;

import it.unicam.cs.ids.c3.model.database.DBLocale;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe serve per gestire i corrieri presenti nel database.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaCorrieri {

    private static ListaCorrieri instance;
    private List<CorriereInterface> corrieri;

    /**
     * Questo &egrave; un costruttore di default.
     */
    public ListaCorrieri(){
        try {
            corrieri = DBLocale.getInstance().getAllCorrieri();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo restituisce la lista dei corrieri presenti nel database.
     * @return lista dei corrieri presenti nel database.
     */
    public List<CorriereInterface> getCorrieri(){
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
    public List<CorriereInterface> getCorrieriDisponibili(){
        return this.corrieri.parallelStream().filter(CorriereInterface::getDisponibilita).collect(Collectors.toList());
    }


    /**
     * restituisce l'istanza della classe
     * @return
     */
    public static ListaCorrieri getInstance() {
        if(instance==null) instance = new ListaCorrieri();
        return instance;
    }

    public void cambiaDisponibilita(int idCorriere, boolean nuovaDisponibilita) {
        try {
            DBLocale.getInstance().updateDisponibilitaCorriere(idCorriere, nuovaDisponibilita);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
