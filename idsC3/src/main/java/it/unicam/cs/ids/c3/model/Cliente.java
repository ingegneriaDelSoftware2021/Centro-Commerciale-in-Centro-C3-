package it.unicam.cs.ids.c3.model;

import java.util.Set;

public class Cliente implements ClienteInterface{

    private final String IDcliente;

    private String nome;

    private String cognome;

    private String indirizzo;

    private Carrello carrello;

    private double portafoglio;

    private boolean centroStorico;

    public Cliente(String iDcliente, String nome, String cognome, String indirizzo, boolean centroStorico) {
        IDcliente = iDcliente;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.portafoglio = 0;
        this.centroStorico = centroStorico;
    }




    public String getIDcliente() {
        return IDcliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setPortafoglio(double portafoglio) {
        this.portafoglio = this.portafoglio+portafoglio;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public double getPortafoglio() {
        return portafoglio;
    }



    @Override
    public Set<Negozio> visualizzaNegozi(ListaNegozi listaNegozi) {
        return listaNegozi.getNegozi();

    }

    @Override
    public Set<Prodotto> visualizzaOrdini() {
        //TODO implementare
        return null;
    }

    @Override
    public Set<Promozioni> visualizzaPromozioni() {
        //TODO implementare
        return null;
    }

    @Override
    public ListaLockers visualizzaMagazzini() {
        //TODO implementare
        return null;
    }

    @Override
    public Carrello visualizzaCarrello() {
        //TODO implementare
        return carrello;
    }

    @Override
    public String checkOutCarrello(Carrello carrello) {
        //TODO implementare
        return null;
    }

    @Override
    public Prodotto cercaProdotto(String nomeProdotto) {

        //TODO implementare
        return null;
    }
}
