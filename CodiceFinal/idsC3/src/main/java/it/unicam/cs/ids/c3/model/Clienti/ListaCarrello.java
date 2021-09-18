package it.unicam.cs.ids.c3.model.Clienti;

import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import it.unicam.cs.ids.c3.model.Esercente.Promozioni;
import java.util.List;

/**
 * Questa classe si occupa di gestire tutti i carrelli presenti nel sistema C3.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaCarrello {

    private DBLocale db;
    private List<Carrello> listaCarrello;
    private static ListaCarrello instance;



    private ListaCarrello(){
        List<Carrello> l = null;
        try {
            db = DBLocale.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.listaCarrello = l;
    }

    public  static ListaCarrello getInstance(){
        return new ListaCarrello();
    }


    /**
     * Questo metodo mostra il carrello tramite l'id
     * @param idcarrello id del carrello da visualizzare
     * @return  carrello
     */
    public Carrello getCarrello(int idcarrello){
        this.listaCarrello = db.getAllCarrello();
        return this.listaCarrello.stream().filter(x->x.getIDCarrello()==idcarrello).findFirst().orElse(null);
    }

    /**
     * Questo metodo aggiunge un prodotto al carrello
     * @param idcarrello id del carrello
     * @param prodotto prodotto da aggiungere
     * @param quantita la quantita del prodotto che si vuole aggiungere.
     */
    public void addToCarrello(int idcarrello, Prodotto prodotto, int quantita){
        System.out.println(idcarrello + "== id del carrello");
        if(getCarrello(idcarrello) == null)System.out.println(" il carrrello e' null");
        this.db.addProdottoToCarrello(idcarrello,prodotto, quantita);
    }

    /**
     * Questo metodo rimuove un prodotto dal carrello
     * @param idcarrello id del carrello
     * @param prodotto id del prodotto da togliere
     * @param quantita la quantita da togliere. Se &egrave; 0 allora vuol dire che il prodotto
     *                 va tolto del tutto dal carrello.
     */
    public void removeFromCarrello(int idcarrello, int prodotto, int quantita){
        this.db.removeProdottoToCarrello(idcarrello,prodotto, quantita);
    }

    /**
     * Questo metodo mostra la lista di prodotti nel arrello
     * @param idcarrello id del carrello
     * @return lista di prodotti
     */
    public List<Prodotto> ListaProdotto(int idcarrello){
        return db.getProdottiFromCarrello(idcarrello);
    }

    /**
     * Questo metodo prende l'id del commerciante dal carrello
     * @param idneg id del negozio da cui prendere il commerciante
     * @return id del commerciante
     */
    public int getIDCommFromCarrID(int idneg) {
        return db.getIDCommFromNegozio(idneg);
    }

    public float isSaled(Prodotto prodotto) {
        int idNeg = prodotto.getIDNegozio();
        Promozioni p = this.db.getAllPromozioni(idNeg).stream().filter(x->x.getIDpromozione()== prodotto.getPromozione()).findFirst().orElse(null);
        if(prodotto.getPromozione()!=0){
            float scontoInSoldi = (prodotto.getPrezzo()/100)*(p.getSconto());
            prodotto.setPrezzo(prodotto.getPrezzo()-scontoInSoldi);
        }
        return prodotto.getPrezzo();
    }
}

