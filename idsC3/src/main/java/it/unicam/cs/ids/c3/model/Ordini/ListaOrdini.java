package it.unicam.cs.ids.c3.model.Ordini;

import it.unicam.cs.ids.c3.model.DBLocale;

import java.util.HashSet;
import java.util.Set;

/**
 * Questa classe si occupa di gestire tutti gli ordini presenti nel database.
 * @author Davide Menghini, Francesco Allevi.
 */
public class ListaOrdini {
    private final Set<Ordine> ordini;

    private DBLocale db;
    /**
     * Questo &egrave; un costruttore di default.
     */
    public ListaOrdini() {
        ordini = new HashSet<>();
    }

    /**
     * Questo metodo permette di aggiungere un nuovo ordine nel database.
     * @param ordine ordine da aggiungere.
     */
    public void addOrdine(Ordine ordine) {
        if (ordine == null) return;
        ordini.add(ordine);
    }

    /**
     * Questo metodo permette di rimuovere un ordine nel database.
     * @param IDOrdine id dell'ordine da rimuovere.
     */
    public void removeOrdine(int IDOrdine) {
        ordini.removeIf(o -> o.getIDOrdine() == IDOrdine);
    }

    /**
     * Questo metodo restituisce l'insieme degli ordini presenti nel database, sia da consegnare che
     * gia' consegnati.
     * @return un insieme di ordini.
     */
    public Set<Ordine> getOrdini() {
        return ordini;
    }

    /**
     * Questo metodo permette di restituire un ordine ben specifico, se presente nel database.
     * @param IDOrdine id dell'ordine da restituire.
     * @return l'ordine che corrisponde all'id passato.
     * Restituisce null se non &egrave; presente nel database.
     */
    public Ordine getOrdine(int IDOrdine) {
        for (Ordine o : ordini) {
            if (o.getIDOrdine() == IDOrdine) {
                return o;
            }
        }
        return null;
    }
}




