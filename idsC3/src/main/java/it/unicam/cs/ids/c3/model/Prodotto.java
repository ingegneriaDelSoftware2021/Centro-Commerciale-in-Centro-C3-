package it.unicam.cs.ids.c3.model;

public class Prodotto {

    private final int IDprodotto;
    private final String nome;
    private int quantita;
    private String categoria;

    public Prodotto(int iDprodotto, String nome, int quantita, String categoria, double prezzo) {
        IDprodotto = iDprodotto;
        this.nome = nome;
        this.quantita = quantita;
        this.categoria = categoria;
        this.prezzo = prezzo;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    private double prezzo;

    public int getIDprodotto() {
        return IDprodotto;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public String getCategoria() {
        return categoria;
    }



    public double getPrezzo(){
        return prezzo;
    }


}
