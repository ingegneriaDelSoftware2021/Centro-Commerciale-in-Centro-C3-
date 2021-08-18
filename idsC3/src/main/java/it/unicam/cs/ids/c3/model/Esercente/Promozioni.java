package it.unicam.cs.ids.c3.model.Esercente;

/**
 * Questa classe &egravE; un'implementazione di default delle promozioni di un prodotto.
 * Un prodotto pu&ograve; contenere solo una promozione.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Promozioni {

    private int sconto;


    private boolean stato;

    private final int IDpromozione;

    private final Prodotto prodotto;


    /**
     * Questo &egrave; un costruttore di default.
     * @param sconto sconto in percentuale.
     * @param iDpromozione id della promozione.
     * @param prodotto prodotto su cui applicare la promozione
     */
    public Promozioni(int sconto, int iDpromozione, Prodotto prodotto) {

        this.sconto = sconto;
        IDpromozione = iDpromozione;
        this.stato = false;
        this.prodotto = prodotto;
    }
    public Promozioni(int iDpromozione, boolean stato, int sconto, Prodotto prodotto) {

        this.sconto = sconto;
        this.IDpromozione = iDpromozione;
        this.stato = stato;
        this.prodotto = prodotto;
    }

    /**
     * Questo metodo restituisce lo sconto della promozione.
     * @return lo sconto in percentuale.
     */
    public int getSconto() {
        return sconto;
    }

    /**
     * Questo metodo modifica lo sconto della promozione.
     * @param sconto il nuovo sconto in percentuale
     */
    public void setSconto(int sconto) {
        this.sconto = sconto;
    }



    /**
     * Questo metodo restituisce lo stato della promozione.
     * @return true se la promozione &egrave; attiva, false altrimenti.
     */
    public boolean isStato() {
        return stato;
    }

    /**
     * Questo metodo modifica lo stato della promozione.
     * @param stato indica se la promozione &egrave; attiva o no.
     *              Se viene passato true la promozione &egrave; attiva, false altrimenti.
     */
    public void setStato(boolean stato) {
        this.stato = stato;
    }

    /**
     * Questo metodo restituisce l'id della promozione.
     * @return id della promozione.
     */
    public int getIDpromozione() {
        return IDpromozione;
    }

    /**
     * Questo metodo restituisce il prodotto in cui c'&egrave; la promozione.
     * @return il prodotto che ha questa promozione.
     */
    public Prodotto getProdotto() {
        return prodotto;
    }


}

