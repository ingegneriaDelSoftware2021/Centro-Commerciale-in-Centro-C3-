package it.unicam.cs.ids.c3.model.Corriere;

import it.unicam.cs.ids.c3.model.Ordini.Ordine;

import java.util.List;

/**
 *Questa interfaccia definisce il comportamento di un corriere.
 * @author Davide Menghini, Francesco Allevi.
 */
public interface CorriereInterface {


    /**
     * Questo metodo ritorna l'id del corriere.
     * @return l'id del corriere.
     */
    int getIDCorriere();

    /**
     * Questo metodo peremette di conoscere tutti gli ordini fatti o da fare del
     * corriere.
     * @return lista degli ordini.
     */
    List<Ordine> getListaOrdine();

    /**
     * Questo metodo permette di sapere se il corriere possiede l'ordine, che deve
     * essere ancora consegnato.
     * @param IDOrdine id dell'ordine
     * @return null se non ha l'ordine, altrimenti ritorna l'oggetto Ordine.
     */
    Ordine getOrdine(int IDOrdine);

    /**
     * Questo metodo permette di aggiungere un ordine da consegnare alla lista degli ordini.
     * @param IDOrdine id dell'ordine
     */
    void addOrdine(int IDOrdine);


    /**
     * Questo metodo permette di sapere se attaulmente il corriere &egrave; disponibile
     * ad accettare nuovi ordini da consegnare.
     * @return true se il corriere &egrave; disponibile, false altrimenti.
     */
    boolean getDisponibilita();

    /**
     * Questo metodo permette di cambiare la disponibilita del corriere.
     * @param nuovaDisponibilita la nuova disponibilita.
     */
    void setDisponibilita(boolean nuovaDisponibilita);


}
