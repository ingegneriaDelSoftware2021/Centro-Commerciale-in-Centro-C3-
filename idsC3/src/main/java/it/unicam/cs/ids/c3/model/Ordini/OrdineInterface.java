package it.unicam.cs.ids.c3.model.Ordini;

import it.unicam.cs.ids.c3.model.Clienti.Carrello;
import it.unicam.cs.ids.c3.model.Corriere.Corriere;

/**
 * Questa interfaccia definisce il comportamento di deafult di un ordine.
 * @author Davide Menghini, Francesco Allevi.
 */
public interface OrdineInterface {

    /**
     * Questo metodo restituisce l'id del cliente.
     * @return id del cliente.
     */
    int getIDCliente();

    /**
     * Questo metodo restituisce lo stato dell'ordine.
     * @return stato dell'ordine
     */
    StatoOrdine getStatoOrdine();

    /**
     * Questo metodo restituisce la destinazione dell'ordine nel caso in cui bisogna consegnarlo
     * a casa del cliente.
     * @return indirizzo del cliente. Se &egrave; ancora da definire o la destinazione &egrave; un locker
     * allora restituisce "".
     */
    String getDestinazione();

    /**
     * Questo metodo restituisce l'id dell'ordine.
     * @return id dell'ordine.
     */
    int getIDOrdine();


    /**
     * Questo metodo serve per modificare lo stato dell'ordine.
     * @param statoOrdine nuovo stato dell'ordine.
     */
    void setStatoOrdine(StatoOrdine statoOrdine);

    /**
     * Questo metodo restituisce il corriere dell'ordine, se &egrave; gia' stato definito.
     * @return id del corriere.
     */
    int getIDCorriere();

    /**
     * Questo metodo permette di impostare il corriere che deve trasportare questo ordine.
     * @param IDCorriere corriere dell'ordine.
     */
    void setIDCorriere(int IDCorriere);

    /**
     * Questo metodo permette di impostare la casa del cliente come destinazione dell'ordine.
     * @param destinazione indirizzo del cliente.
     */
    void setDestinazione(String destinazione);


    /**
     * Questo metodo peremtte di restituire il carrello che contiene la lista dei prodotti acquistati.
     * @return il carrello acquistato dal cliente.
     */
    Carrello getCarrello();


    /**
     * Questo metodo restituisce l'id del commerciante.
     * @return id del commerciante.
     */
    int getIDCommerciante();


    /**
     * Questo metodo restituisce l'id del locker di consegna.
     * @return id del locker se la consegna &egrave; un locker.
     * Se la consegna &egrave; la casa del cliente allora restituisce -1;
     */
    int getIDLocker();

    /**
     * Questo metodo permette di impostare l'id del locker dell'ordine.
     * @param IDlocker id del locker.
     */
    void setIDLocker(int IDlocker);
}
