package it.unicam.cs.ids.c3.model;

import java.util.HashSet;
import java.util.Set;

public class ListaOrdini {
    private final Set<Ordine> ordini;

    public ListaOrdini() {
        ordini = new HashSet<>();
    }

    public void addOrdine(Ordine ordine) {
        if (ordine == null) return;
        ordini.add(ordine);
    }

    public void removeOrdine(int IDOrdine) {
        for (Ordine o : ordini) {
            if (o.getIDOrdine() == IDOrdine) {
                ordini.remove(o);
            }
        }
    }

    public Set<Ordine> getOrdini() {
        return ordini;
    }

    public Ordine getOrdine(int IDOrdine) {
        for (Ordine o : ordini) {
            if (o.getIDOrdine() == IDOrdine) {
                return o;
            }
        }
        return null;
    }
}




