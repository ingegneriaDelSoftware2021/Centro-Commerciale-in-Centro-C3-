package it.unicam.cs.ids.c3.model.Esercente;

import it.unicam.cs.ids.c3.model.database.DBLocale;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe serve per gestire la lista di negozi presenti nel database.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaNegozi {

    private List<Negozio> negozi;
    private DBLocale db;

    /**
     * Questo &egrave; un costruttore di default.
     */
    private ListaNegozi(){
        List<Negozio> o = null;
        try {
            db = DBLocale.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.negozi= o;
    }


    public static ListaNegozi getInstance(){
        return new ListaNegozi();
    }


    /**
     * Questo metodo restituisce la lista di tutti i negozi presenti nel database.
     * @return la lista dei negozi.
     */
    public List<Negozio> getNegozi(){
        return db.getAllNegozi();
    }

    /**
     * Questo metodo serve per avere la lista dei negozi di un commerciante.
     * @param IDCommerciante id del commerciante.
     * @return una lista di negozi dello stesso commerciante.
     */
    public List<Negozio> getNegozi(int IDCommerciante){
        return db.getAllNegozi().parallelStream()
                .filter(x -> x.getPropietario()==IDCommerciante)
                .collect(Collectors.toList());
    }



    /**
     * Questo metodo restituisce un negozio di un commerciante.
     * @param IDNegozio id del negozio da restituire.
     * @return il negozio cercato, oppure null se non esiste.
     */
    public Negozio getNegozio(int IDNegozio){
        this.negozi = db.getAllNegozi();
        return this.negozi.stream().filter(x->x.getIDNegozio()==IDNegozio).findFirst().orElse(null);

    }


    /**
     * Questo metodo mostra tutti i prodotti di un negozio
     * @param idNegozio id del negozio da esaminare
     * @return restituisce una lista di prodotti
     */
    public List<Prodotto> cercaProdottoFromNegozio(int idNegozio) {
        return db.getAllProdottiFromNegozio(idNegozio);
    }

    /**
     * Questo metodo ricerca dei prodotti in un negozio.La ricerca viene affettuata attraverso il nome dei prodotti.
     * Se il nome del prodotto contiene una sottostringa che corrisponde alla stringa s allora verr&agrave; restituito
     * nell lista
     * @param idNegozio id del negozio
     * @param s la stringa da cercare.
     * @return una lista contenente i prodotti cercati.
     */
    public List<Prodotto> cercaProdottoFromNegozio(int idNegozio, String s) {
        this.negozi = db.getAllNegozi();
        if(s==null || s.equals("")){
            return db.getAllProdottiFromNegozio(idNegozio);
        }
        return this.negozi.stream().filter(x->x.getIDNegozio()==idNegozio).
                map(Negozio::getListaProdotti).
                flatMap(Collection::stream).
                filter(x->x.getNome().contains(s)).collect(Collectors.toList());
    }

    /**
     * Questo metodo mostra tutti gli ordini del negozio
     * @param idNegozio id del negozio da analizzare
     * @return lista degli ordini
     */
    public List<OrdineInterface> getAllOrdiniFromNegozio(int idNegozio) {
        return db.getAllOrdiniFromNegozio(idNegozio);
    }

    /**
     * Questo metodo cambia lo sconto di una promozione
     * @param idPromozione id della promozione da modificare
     * @param sconto nuovo sconto
     */
    public void updateScontoPromozione(int idPromozione, int sconto) {
        db.updateScontoPromozione(idPromozione, sconto);
    }

    /**
     * Questo metodo aggiorna lo stato di una promozione (attiva , non attiva)
     * @param idPromozione id della promozione da aggiornare
     * @param stato stato da inserire
     */
    public void updateStatoPromozione(int idPromozione, boolean stato) {
        db.updateStatoPromozione(idPromozione, stato);
    }

    /**
     * Questo metodo imposta una promozione ad un prodotto
     * @param sconto sconto della promozione
     * @param stato stato promozione
     * @param iDpromozione id dalla promozione
     * @param idProdotto id del prodotto a cui aggiungere la promozione
     */
    public void addPromozioneToProdotto(int sconto, boolean stato, int iDpromozione, int idProdotto) {
         db.addPromozioneToProdotto(sconto, stato, iDpromozione, idProdotto);
    }

    /**
     * restituisce tutte le promozioni
     * @param idNegozio id del negozio in cui cercare le promozioni.
     * @return una lista contenente tutte le promozioni presenti nella piattaforma.
     */
    public List<Promozioni> getAllPromozioni(int idNegozio) {
        return db.getAllPromozioni(idNegozio);
    }

    /**
     * Questo metodo elimina una promozione
     * @param idPromozione id della promozione da eliminare
     */
    public void deletePromozione(int idPromozione, int IDProdotto) {
        db.deleteIDPromFromProdotto(IDProdotto);
        db.deletePromozione(idPromozione);

    }

    /**
     * Questo metodo mostra tutti i prodotti presenti in un negozio
     * @param idNegozio id del negozio da analizzare
     * @return lista di prodotti del negozio
     */
    public List<Prodotto> getAllProdottiFromNegozio(int idNegozio) {
        return this.db.getAllProdotti().stream().filter(x->x.getIDNegozio()==idNegozio).collect(Collectors.toList());
    }
}
