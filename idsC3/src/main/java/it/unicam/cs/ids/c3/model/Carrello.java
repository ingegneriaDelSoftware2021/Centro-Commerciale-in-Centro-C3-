package it.unicam.cs.ids.c3.model;

import java.util.Set;

public class Carrello {


    public double sommaTotale(Set<Prodotto> prodotti){
        double ritorno = 0;
        for(Prodotto p : prodotti){
            ritorno = ritorno+p.getPrezzo();
        }
        return 0.0;
    }

}
