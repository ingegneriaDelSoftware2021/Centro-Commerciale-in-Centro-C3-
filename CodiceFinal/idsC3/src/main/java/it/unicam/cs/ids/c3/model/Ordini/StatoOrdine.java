package it.unicam.cs.ids.c3.model.Ordini;

/**
 * Questa Enumerazione rappresenta i vari stati dell'ordine:
 */
public enum StatoOrdine {
    /**
     * stato di un ordine appena creato dal cliente.
     */
    ORDINECREATO,
    /**
     * In questo stato il commerciante sta preparando l'ordine e sta assegnando il corriere e la destinazione.
     */
    INPREPARAZIONE,
    /**
     * In questo stato Il corriere ha con se l'ordine e lo sta trasportando al locker o all'abitazione del cliente.
     */
    INTRANSITO,
    /**
     * In questo stato l'ordine si trova in un locker.
     */
    DARITIRARE,
    /**
     * In questo stato l'ordine &egrave; stato consegnato.
     */
    RITIRATO;


}
