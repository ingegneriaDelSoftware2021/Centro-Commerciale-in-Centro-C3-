package it.unicam.cs.ids.c3.model.Lockers;

import it.unicam.cs.ids.c3.model.DBLocale;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe si occupa di gestire la lista di tutti i locker presenti del database.
 * @author Davide Menghini, Francesco Allevi.
 */
public  class ListaLockers {
    private List<Locker> lockers;
    private DBLocale db;



    /**
     * costruttore di default
     */
    public ListaLockers(){
        try {
            db = DBLocale.getInstance();
            lockers = db.getAllLockers();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }





    /**
     * Questo metodo aggiunge un locker nel database.
     * @param locker il locker da aggiungere.
     */
    public void addLocker(Locker locker){
        if(locker == null) return;
        lockers.add(locker);
    }

    /**
     * Questo metodo permette di rimuovere il locker dal database.
     * @param IDLocker id del locker da rimuovere.
     */
    public void removeLocker(int IDLocker){
        this.lockers.removeIf(l -> l.getID() == IDLocker);
    }

    /**
     * Questo metodo restituisce la lista di tutti i locker presenti nel database.
     * @return una lista di tutti i locker.
     */
    public List<Locker> getLockers(){
        return lockers;
    }

}
