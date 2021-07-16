package it.unicam.cs.ids.c3.model;

public class Promozioni {

    private double sconto;

    private String categoria;

    private boolean stato;

    private final int IDpromozione;

    private Prodotto prodotto;


    public Promozioni(double sconto, String categoria, int iDpromozione, Prodotto prodotto) {

        this.sconto = sconto;
        this.categoria = categoria;
        IDpromozione = iDpromozione;
        this.stato = false;
        this.prodotto = prodotto;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }


}

