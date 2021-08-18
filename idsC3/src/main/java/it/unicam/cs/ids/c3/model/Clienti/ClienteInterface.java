package it.unicam.cs.ids.c3.model.Clienti;

import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Esercente.ListaNegozi;
import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import it.unicam.cs.ids.c3.model.Esercente.Promozioni;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;

import java.util.List;
import java.util.Set;

/**
 * Questa interfaccia serve per definire un cliente per il sistema C3.
 * @author Davide Menghini, Francesco Allevi.
 */
public interface ClienteInterface {
    /**
     * Questo metodo serve per visualiizare la lista di tutti i negozi presenti nel sistema.
     * @return
     */
    List<Negozio> visualizzaNegozi();

    /**
     * Questo metodo restituisce la lista degli ordini effettuati dal cliente, sia quelli già consegnati
     * che quelli ancora da consegnare.
     * @return una lista contentente gli ordini del cliente.
     */
    List<Ordine> visualizzaOrdini();

    /**
     * Questo metodo permette di ricevere una lista delle promozioni attive.
     * @return una lista contenente le promozioni attive.
     */
    List<Promozioni> visualizzaPromozioni();




    /**
     * Questo metodo permette di visualizzare il carrello del cliente.
     * @return l'oggetto carrello del cliente.
     */
    Carrello visualizzaCarrello();

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
     * @param nomeNegozio il nome del negozio in cui cercare il prodotto.
     * @return una lista contenente i prodotti che hanno nel nome la stringa passata.
     */
    List<Prodotto> cercaProdotto(String nomeProdotto, String nomeNegozio);


    /**
     * Questo metodo restituisce l'ID del cliente.
     * @return ID del cliente.
     */
    public int getIDcliente();



    /**
     * Questo metodo permette al cliente di ricaricare il portafloglio.
     * @param portafoglio la somma da aggiungere al portafoglio.
     */
    void aggiungiCredito(double portafoglio);


    /**
     * Questo metodo restituisce il saldo del portafoglio.
     * @return saldo del portafoglio.
     */
    double getPortafoglio();


    /**
     * Questo metodo restituisce il carrello di un cliente.
     * @return carrello del cliente.
     */
    Carrello getCarrello();






}
