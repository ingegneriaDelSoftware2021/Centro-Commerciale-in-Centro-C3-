package it.unicam.cs.ids.c3.model.Corriere;
import it.unicam.cs.ids.c3.model.Ordini.OrdineInterface;
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
    List<OrdineInterface> getListaOrdine();

    /**
     * Questo metodo permette di sapere se il corriere possiede l'ordine, che deve
     * essere ancora consegnato.
     * @param IDOrdine id dell'ordine
     * @return null se non ha l'ordine, altrimenti ritorna l'oggetto Ordine.
     */
    OrdineInterface getOrdine(int IDOrdine);




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

    /**
     * Questo metodo restituisce il nome del corriere.
     * @return il nome del corriere.
     */
    String getNome();

    /**
     * Questo metodo restituisce il nome utente del corriere.
     * @return il nome utente del corriere
     */
    String getUser();


    /**
     * Questo metodo permette di impostare lo stato di un ordine come IN TRANSITIO.
     * @param idOrdine id dell'ordine di cui modificare lo stato.
     */
    void setOrdineToINTRANSITO(int idOrdine);

    /**
     * Questo metodo permette di modificare lo stato di un ordine dopo che l'ordine &egrave; stato consegnato
     * ad un locker o all'abitazione del cliente.
     * @param idOrdine id dell'ordine di cui modificare lo stato.
     */
    void setOrdineToCONSEGNATO(int idOrdine);


}
