package it.unicam.cs.ids.c3.model;

import java.util.List;

public interface CorriereInterface {

    void aggiornaStatoOrdine(StatoOrdine statoOrdine);

    void cambiaDisponibilita(boolean disponibilita);

    String visualizzaDestinazione();

    List<Ordine> visualizzaConsegne();

}
