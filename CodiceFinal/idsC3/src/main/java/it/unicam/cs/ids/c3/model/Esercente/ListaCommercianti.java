package it.unicam.cs.ids.c3.model.Esercente;

import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Ordini.StatoOrdine;

import java.util.List;



public class ListaCommercianti {

    private DBLocale db;
    private List<CommercianteInterface> listaCommercianti;


    public static ListaCommercianti getInstance(){
        return new ListaCommercianti();
    }

    private ListaCommercianti(){
        try {
            db = DBLocale.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.listaCommercianti = null;

    }



    public List<CommercianteInterface> getCommercianti(){
        return this.listaCommercianti = db.getAllCommercianti();
    }

    public CommercianteInterface getCommerciante(int id){
        return getCommercianti().stream().filter(x->x.getIDCommerciante()==id).findFirst().orElse(null);
    }

    /**
     * Questo metodo restituisce il prodotto a cui è associata una promozione
     * @param id id della promozione
     * @return il prodotto
     */
    public Prodotto getProdottoFromPromozione(int id) {
        return db.getProdottoFromPromozione(id);
    }

    /**
     * Questo metodo restituisce tutte le promozioni del commerciante del negozio passato
     * @return lista di promozioni del negozio passato.
     */
    public List<Promozioni> getAllPromozioniFromNegozio(int idNegozio) {
        return ListaNegozi.getInstance().getAllPromozioni(idNegozio);
    }

    /**
     * Questo metodo imposta l'id del locker nell'ordine
     * @param idLocker id del locker da aggiungere
     * @param idOrdine id dell'ordine a cui aggiungere l'id locker
     */
    public void setIDLockerToOrdine(int idLocker, int idOrdine) {
        db.setIDLockerToOrdine(idLocker, idOrdine);
    }

    /**
     * Questo metodo imposta lo stato dell'ordine come ("in preparazione")
     * @param idOrdine id dell'ordine a cui aggiornare lo stato
     */
    public void aggiornaStatoOrdine(int idOrdine) {
        db.aggiornaStatoOrdine(idOrdine, StatoOrdine.INPREPARAZIONE);
    }

    /**
     * Questo metodo aggiunge denaro al portafoglio del commerciante
     * @param idcomm id del commerciante
     * @param totale totale pagato da aggiungere al portafoglio del commerciante
     */
    public void aggiungiDenaro(int idcomm, float totale){
        db.aggiungiDenaro(idcomm, totale);
    }

    /**
     * Questo metodo mostra il portafoglio del commerciante
     * @param idCommerciante id del commerciante
     * @return portafoglio del commerciante
     */
    public float getPortafoglioFromID(int idCommerciante) {
        return db.getPortaglioFromIDComm(idCommerciante);
    }

    /**
     * Questo metodo ritorna il commerciante gato all'user
     * @param user
     * @return commerciante
     */
    public CommercianteInterface getCommercianteFromUser(String user) {
        this.listaCommercianti = db.getAllCommercianti();
        return this.listaCommercianti.stream().filter(x->x.getUser().equals(user)).findFirst().orElse(null);
    }

    /**
     * Questo metodo ritorna tutte le promozioni del negozio
     * @return lista di promozioni
     */
    public List<Promozioni> getAllPromozioniFromNegozio() {
        return db.getAllPromozioni();
    }
}
