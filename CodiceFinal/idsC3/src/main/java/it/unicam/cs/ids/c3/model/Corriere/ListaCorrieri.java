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

    /**
     * Questo metodo cambia lo stato di disponibilita' del corriere
     * @param idCorriere id del corriere a cui cambiare lo stato
     * @param nuovaDisponibilita disponibilita'
     */
    public void cambiaDisponibilita(int idCorriere, boolean nuovaDisponibilita) {
        try {
            DBLocale.getInstance().updateDisponibilitaCorriere(idCorriere, nuovaDisponibilita);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
