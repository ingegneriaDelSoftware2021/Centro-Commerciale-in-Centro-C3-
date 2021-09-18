package it.unicam.cs.ids.c3.model.Lockers;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
import java.util.List;

/**
 * Questa interfaccia definisce il comportamento di un locker.
 * @author Davide Menghini, Francesco Allevi.
 */
public interface LockerInterface {

    /**
     * Questo metodo permette di visualizzare tutti gli armadietti presenti nel locker.
     * @return un insieme di armadietti che stanno nel locker.
     */
    List<Armadietto> visualizzaArmadietti();


    /**
     * Questo metodo permette di ricercare un ordine all'interno di un locker.
     * @param IDOrdine id dell'ordine da cercare.
     * @return id dell'armadietto.
     */
   int cercaOrdine(int IDOrdine);

    /**
     * Questo metodo aggiorna lo stato dell'ordine.
     * @param IDOrdine id dell'ordine da aggiornare.
     * @return messaggio di conferma o di rifiuto.
     */
   String aggiornaStatoOrdine(int IDOrdine);

    /**
     * Questo metodo permette di aggiungere un ordine ad un armadietto disponibile.
     * @param ordine ordine da aggiungere.
     * @param idArmadietto id dell'armadietto a cui aggiungere l'ordine.
     */
   void aggiungiOrdine(int idArmadietto,OrdineInterface ordine);

    /**
     * Questo metodo restituisce l'id del locker.
     * @return
     */
    int getID();

    /**
     * Questo metodo permette di conoscere l'indirizzo del locker.
     */
    String getIndirizzo();

    /**
     * Questo metodo restituisce il nome utente del locker.
     * @return una stringa contenente l'user del locker.
     */
    String getUser();



}
