package it.unicam.cs.ids.c3.model.Clienti;

import it.unicam.cs.ids.c3.model.Esercente.ListaNegozi;
import it.unicam.cs.ids.c3.model.Esercente.Negozio;
import it.unicam.cs.ids.c3.model.Esercente.Prodotto;
import it.unicam.cs.ids.c3.model.Esercente.Promozioni;
import it.unicam.cs.ids.c3.model.Ordini.Ordine;
import it.unicam.cs.ids.c3.model.Utente.Utente;

import java.util.List;
import java.util.Set;

public class Cliente extends Utente implements ClienteInterface {

    private final int IDcliente;


    private Carrello carrello;



    private double portafoglio;


    public Cliente(int iDcliente, String nome, String indirizzo, int IDCarrello) {
        super();
        this.IDcliente = iDcliente;
        setNome(nome);
        super.setIndirizzo(indirizzo);
        this.portafoglio = 0;
    }








    @Override
    public List<Negozio> visualizzaNegozi() {
        return null;//listaNegozi.getNegozi().;

    }

    @Override
    public List<Ordine> visualizzaOrdini() {
        //TODO implementare
        return null;
    }

    @Override
    public List<Promozioni> visualizzaPromozioni() {
        //TODO implementare
        return null;
    }


    @Override
    public Carrello visualizzaCarrello() {
        return carrello;
    }

    @Override
    public String inviaPagamento(Carrello carrello) {
        //TODO implementare
        return null;
    }

    @Override
    public List<Prodotto> cercaProdotto(String nomeProdotto) {

        //TODO implementare
        return null;
    }

    @Override
    public int getIDcliente() {
        return this.IDcliente;
    }




    @Override
    public void aggiungiCredito(double portafoglio) {
        if(portafoglio<=0) return;
        this.portafoglio+=portafoglio;
    }

    /**
     * @return
     */
    @Override
    public double getPortafoglio() {
        return this.portafoglio;
    }




}
