package it.unicam.cs.ids.c3.model.Lockers;

import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;

import java.util.List;

/**
 * Questa classe si occupa di gestire la lista di tutti i locker presenti del database.
 * @author Davide Menghini, Francesco Allevi.
 */
public  class ListaLockers {
    private List<LockerInterface> lockers;
    private DBLocale db;



    /**
     * costruttore di default
     */
    private ListaLockers(){
        try {
            db = DBLocale.getInstance();
            lockers = db.getAllLockers();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Questo metodo restituisce l'istanza della lista di lockers.
     * @return istanza di ListaLockers.
     */
    public static ListaLockers getInstance() {
        return new ListaLockers();
    }


    /**
     * Questo metodo restituisce la lista di tutti i locker presenti nel database.
     * @return una lista di tutti i locker.
     */
    public List<LockerInterface> getLockers(){
        return this.lockers;
    }

    /**
     * questo metodo restituisce tutti gli armadietti del locker
     * @param idLocker id del locker
     * @return lista di armadietti
     */
    public List<Armadietto> getArmadietti(int idLocker) {
        try {
            db = DBLocale.getInstance();
            return db.getArmadiettiFromLocker(idLocker);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Questo metodo aggiunge un ordine in un armadietto
     * @param idlocker id del locker dove verra' aggiunto l'ordine
     * @param ordine ordine da aggiungere all'armadietto
     */
    public void addOrdine(int idArmadietto ,int idlocker, OrdineInterface ordine) {
        Armadietto a = getArmadietti(idlocker).stream().filter(x->x.getIDArmadietto()==idArmadietto).findAny().orElse(null);
        if(a!=null){
            a.aggiungiOrdine(ordine);
            db.addOrdineToArmadietto(a.getIDArmadietto(), ordine.getIDOrdine());
        }
    }

    /**
     * Questo metodo permette di restituire l'istanza del locker partendo dall'username.
     * @param user username usato per accedere alla piattaforma.
     * @return istanza del locker che ha come username la stringa passata.
     */
    public LockerInterface getLockerFromUser(String user) {
        return db.getAllLockers().stream().filter(x-> x.getUser().equals(user)).findFirst().orElse(null);
    }

    /**
     * Questo metodo serve per rimuovere un'ordine dall'armadietto.
     * @param idOrdine id dell'ordine da ritirare.
     */
    public void rimuoviOrdineFromArmadietto(int idOrdine) {
        db.deleteOrdineFromArmadietto(idOrdine);
    }
}
