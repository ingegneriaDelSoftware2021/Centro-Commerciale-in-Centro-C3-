package it.unicam.cs.ids.c3.model.Clienti;


import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import it.unicam.cs.ids.c3.model.Esercente.Promozioni;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import java.util.List;

/**
 * Questa interfaccia serve per definire un cliente per il sistema C3.
 * @author Davide Menghini, Francesco Allevi.
 */
public interface ClienteInterface{
    /**
     * Questo metodo serve per visualiizare la lista di tutti i negozi presenti nel sistema.
     * @return una lista contenente tutti i negozi.
     */
    List<Negozio> visualizzaNegozi();



    /**
     * Questo metodo permette di ricevere una lista delle promozioni attive.
     * @return una lista contenente le promozioni attive.
     */
    List<Promozioni> visualizzaPromozioni();






    /**
     * Questo metodo si occupa di fare il checkout del carrello e di inviare i soldi al portafoglio
     * del commerciante.
     * @param carrello il carrello di prodotti da acquistare.
     * @return restituisce true se il pagamneto &egrave; andato a buon fine, mentre
     * se non &egrave; andato a buon fine restituisce false.
     */
    boolean inviaPagamento(Carrello carrello);

    /**
     * Questo metodo si occupa di restituire una lista di tutti i prodotti che contengono nel nome la stringa
     * passata nel negozio indicato.
     * @param nomeProdotto il nome del prodotto da cercare.
     * @param idNegozio il nome del negozio in cui cercare il prodotto.
     * @return una lista contenente i prodotti che hanno nel nome la stringa passata.
     */
    List<Prodotto> cercaProdotto(String nomeProdotto, int idNegozio);


    /**
     * Questo metodo restituisce l'ID del cliente.
     * @return ID del cliente.
     */
    public int getIDcliente();



    /**
     * Questo metodo permette al cliente di ricaricare il portafloglio.
     * @param portafoglio la somma da aggiungere al portafoglio.
     */
    void aggiungiCredito(float portafoglio);


    /**
     * Questo metodo restituisce il saldo del portafoglio.
     * @return saldo del portafoglio.
     */
    float getPortafoglio();


    /**
     * Questo metodo restituisce il carrello di un cliente.
     * @return carrello del cliente.
     */
    Carrello getCarrello();


    /**
     * Questo metodo restituisce l'username del cliente.
     * @return username del cliente.
     */
    String getUser();

    /**
     * Questo metodo restituisce il nome del cliente.
     * @return nome del cliente.
     */
    String getNome();


    /**
     * Questo metodo restituisce la lista di tutti gli ordini effettutati, anche di quelli che ancora non sono stati
     * consegnati.
     * @return una lista contenente gli ordini del cliente.
     */
    List<OrdineInterface> getAllOrdiniCliente();


    /**
     * Questo metodo serve per aggiungere un prodotto al carrello.
     * @param prodotto il prodotto da aggiungere.
     * @param quantita quantit&agrave; da aggiungere al carrello.
     */
    void aggiungiProdottoAlCarrello(Prodotto prodotto, int quantita);

    /**
     * Questo metodo permette di rimuovere un prodotto dal carrello.
     * @param prodotto il prodotto da rimuovere
     */
    void rimuoviProdottoAlCarrello(Prodotto prodotto);

    /**
     * Questo metodo permette di modificare la quantita di un prodotto presente nel carrello.
     * @param p il prodotto che si vuole modificare la quantit&agrave;
     * @param nuovaQuantita la nuova quantita del prodotto.
     */
    void modificaQuantitaAlCarrello(Prodotto p, int nuovaQuantita);


    /**
     * Questo metodo restituisce l'indirizzo della residenza del cliente.
     * @return una stringa contenente l'indirizzo.
     */
    String getIndirizzo();


}
