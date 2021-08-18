package it.unicam.cs.ids.c3.model.Lockers;

import it.unicam.cs.ids.c3.model.Ordini.Ordine;

import java.util.Set;

/**
 * Questa interfaccia definisce il comportamento di un locker.
 * @author Davide Menghini, Francesco Allevi.
 */
public interface LockerInterface {

    /**
     * Questo metodo permette di visualizzare tutti gli armadietti presenti nel locker.
     * @return un insieme di armadietti che stanno nel locker.
     */
   Set<Armadietto> visualizzaArmadietti();


    /**
     * Questo metodo permette di ricercare un ordine all'interno di un locker.
     * @param IDOrdine id dell'ordine da cercare.
     * @return id dell'armadietto.
     */
   int cercaOrdine(int IDOrdine);

    /**
     * Questo metodo aggiorna lo stato dell'ordine.
     * @param IDOrdine id dell'ordine da aggiornare.
     */
   void aggiornaStatoOrdine(int IDOrdine);

    /**
     * Questo metodo permette di aggiungere un ordine ad un armadietto disponibile.
     * @param ordine ordine da aggiungere.
     */
   void aggiungiOrdine(Ordine ordine);
}
