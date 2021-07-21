package it.unicam.cs.ids.c3.model;


import java.util.ArrayList;
import java.util.List;


public class Carrello {

    private double totale;

    private int IDCarrello;

    private final List<Prodotto> prodotti;

    public Carrello(){
        totale = 0;
        prodotti = new ArrayList<>();
    }

    public double getTotale(){
        return totale;
    }

    public void aggiungiProdotto(Prodotto p, int quantita){
        if(p==null) return;
        if(quantita<0) return;
        this.prodotti.add(p);
        totale = totale + p.getPrezzo();
    }

    public void rimuoviProdotto(int IDProdotto, int quantita){
        if(IDProdotto<0) return;
        double prodottoPrezzo = this.prodotti.parallelStream().filter(x -> x.getIDprodotto()==IDProdotto).map(Prodotto::getPrezzo).findFirst().orElse(0.0);
        prodottoPrezzo = prodottoPrezzo * quantita;
        prodotti.removeIf(p -> p.getIDprodotto() == IDProdotto);
        totale = totale-prodottoPrezzo;
    }

}
