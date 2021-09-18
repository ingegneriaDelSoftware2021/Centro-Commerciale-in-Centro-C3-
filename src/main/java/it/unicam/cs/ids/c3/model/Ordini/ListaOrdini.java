package it.unicam.cs.ids.c3.model.Ordini;
import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Esercente.ListaNegozi;
import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Questa classe si occupa di gestire tutti gli ordini presenti nel database.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaOrdini {
    private List<OrdineInterface> ordini;
    private static ListaOrdini instance;
    private DBLocale db;


    /**
     * Questo &egrave; un costruttore di default.
     */
    private ListaOrdini() {
        List<OrdineInterface> o = null;
        try {
            db = DBLocale.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ordini = o;
    }

    public ListaOrdini(List<OrdineInterface> o) {
        ordini = new ArrayList<>(o);
    }


    /**
     * Questo metodo permette di aggiungere un nuovo ordine nel database.
     * @param ordine ordine da aggiungere.
     */
    public void addOrdine(Ordine ordine) {
        if (ordine == null) return;
        this.ordini = db.getAllOrdiniFromNegozio(ordine.getIDNegozio());
        if(db!=null){
            Negozio n = ListaNegozi.getInstance().getNegozio(ordine.getIDNegozio());
            int app;
            for(Prodotto p : ordine.getListaProdotti()){
                app = n.getProdottiVenduti().get(p);
                db.updateCounterProdotto(p.getIDprodotto(),app);
            }
            updateQuantitaDisponibile(ordine);
            db.addOrdine(ordine);
            ordini.add(ordine);
        }
    }


    /**
     * Questo metodo restituisce l'insieme degli ordini presenti nel database, sia da consegnare che
     * gia' consegnati.
     * @return un insieme di ordini.
     */
    public List<OrdineInterface> getOrdini() {
        this.ordini = db.getAllOrdini(0);
        return this.ordini;
    }

    /**
     * Questo metodo permette di restituire un ordine ben specifico, se presente nel database.
     * @param IDOrdine id dell'ordine da restituire.
     * @return l'ordine che corrisponde all'id passato.
     * Restituisce null se non &egrave; presente nel database.
     */
    public OrdineInterface getOrdine(int IDOrdine) {
        if(this.ordini==null) this.ordini = getOrdini();
        for (OrdineInterface o : this.ordini) {
            if (o.getIDOrdine() == IDOrdine) {
                return o;
            }
        }
        return null;
    }

    /**
     * ritorna l'istanza della classe
     * @return istanza della classe
     */
    public static ListaOrdini getInstance(){
        instance = new ListaOrdini();
        return instance;
    }




    /**
     * Questo metodo permette di impostare l'indirizzo di destinazione dell'ordine
     * @param IDOrdine id dell'ordine a cui aggiungere l'indirizzo
     * @param indirizzo indirizzo da aggiungere all'ordine
     */
    public void setIndirizzoToOrdine(int IDOrdine, String indirizzo){
        if(IDOrdine<=0 || indirizzo.equals("")) throw new IllegalArgumentException("l'id dell'ordine o l'indirizzo " +
                "passati non sono corretti");
        db.setIndirizzoToOrdine(indirizzo,IDOrdine);
    }

    /**
     * Questo metodo permette di impostare l'indirizzo di destinazione dell'ordine
     * @param idOrdine id dell'ordine a cui aggiungere l'indirizzo
     * @param idCorriere id del corriere che spedisce l'ordine
     */
    public void setCorriereToOrdine(int idCorriere, int idOrdine) {
        if(idCorriere<=0 || idOrdine<=0) return;
        db.setCorriereToOrdine(idCorriere,idOrdine);

    }

    /**
     * Questo metodo restituisce  l'id del commerciante dall'ordine
     * @param idOrdine id dell'ordine
     * @return id del commerciante
     */
    public int getIDCommercianteFromIDOrdine(int idOrdine) {
        return db.getIDCommercianteFromIDOrdine(idOrdine);
    }
    /**
     * Questo metodo restituisce l'id del corriere dall'ordine
     * @param idOrdine id dell'ordine
     * @return id del corriere
     */
    public int getIDCorriereFromIDOrdine(int idOrdine) {
        return db.getIDCorriereFromIDOrdine(idOrdine);
    }

    /**
     * Questo metodo restituisce l'id del commerciante dal negozio
     * @param idNegozio id del negozio
     * @return id del commerciante
     */
    public int getIDCommFromNegozio(int idNegozio) {
        return db.getIDCommFromNegozio(idNegozio);
    }
    /**
     * Questo metodo permette di impostare l'id del locker di destinazione dell'ordine
     * @param idLocker id del locker di destinazione da aggiungere nell'ordine
     * @param idOrdine id dell'ordine al quale aggiungere l'id del locker di destinazione
     */
    public void setIDLockerToOrdine(int idLocker, int idOrdine) {
        db.setIDLockerToOrdine(idLocker,idOrdine);
    }

    /**
     * Questo metodo aggiorna il contatore delle vendite di un determinato prodotto
     * @param iDprodotto id del prodotto di cui aggiornare il contatore vendite
     * @param counterVendita contatore vendita del prodotto
     */
    public void updateCounterProdotto(int iDprodotto, int counterVendita) {
        db.updateCounterProdotto(iDprodotto, counterVendita);
    }



    public List<OrdineInterface> getOrdiniFromCliente(int iDcliente) {
        if(this.ordini==null) this.ordini = db.getAllOrdini(0);
        return this.ordini.stream().filter(x->x.getIDCliente()==iDcliente).collect(Collectors.toList());
    }

    public List<Prodotto> getProdottiFromOrdine(int IDOrdine) {
        return db.getProdottoFromIDOrdine(IDOrdine);
    }


    public void updateStatoOrdine(int idOrdine,StatoOrdine nuovoStato){
        if(nuovoStato.equals(StatoOrdine.DARITIRARE)){
            db.aggiornaStatoOrdine(idOrdine, nuovoStato);
        }else db.aggiornaStatoOrdine(idOrdine,nuovoStato);

    }


    private void updateQuantitaDisponibile(Ordine ordine){
        Prodotto oldProdotto = new Prodotto(0,"",0,"",0,"",0,0,0);
        for(Prodotto p : ordine.getListaProdotti()){
            if(p.getIDprodotto()!=oldProdotto.getIDprodotto()){
                int quantita = (int) ordine.getListaProdotti().stream().filter(x->x.getIDprodotto()==p.getIDprodotto()).count();
                db.updateQuantitaDisponibile(p.getIDprodotto(),quantita);
                oldProdotto = p;
            }
        }
    }
}




