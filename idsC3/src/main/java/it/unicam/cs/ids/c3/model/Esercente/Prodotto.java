package it.unicam.cs.ids.c3.model.Esercente;

/**
 * Questa classe definisce un prodotto di default.
 * @author Davide Menghini, Francesco Allevi.
 */
public class Prodotto {

    private final int IDprodotto;
    private final String nome;
    private int quantita;
    private String categoria;
    private float prezzo;
    private String descrizione;
    private Promozioni promozione;
    private final int IDNegozio;
    /**
     * Questo &egrave; un costruttore di default.
     * @param iDprodotto id del prodotto.
     * @param nome nome del prodotto.
     * @param quantita quantit&agrave; disponibile inizialmente.
     * @param categoria categoria del prodotto.
     * @param prezzo prezzo del prodotto.
     */
    public Prodotto(int iDprodotto, String nome, int quantita, String categoria, float prezzo, String descrizione, int IDNegozio) {
        IDprodotto = iDprodotto;
        this.nome = nome;
        this.quantita = quantita;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.promozione = null;
        this.IDNegozio = IDNegozio;
    }

    /**
     * Questo metodo modifica la quantit&agrave; del prodotto.
     * @param quantita nuova quantit&agrave; del prodotto.
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    /**
     * Questo metodo modifica la categoria a cui appartiene il prodotto.
     * @param categoria categoria del prodotto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Qusto meotdo serve per modificare il prezzo del prodotto.
     * @param prezzo nuovo prezzo del prodotto.
     */
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }


    /**
     * Questo metodo restituisce l'id del prodotto.
     * @return id del prodotto.
     */
    public int getIDprodotto() {
        return IDprodotto;
    }

    /**
     * Questo metodo restituisce il nome del prodotto.
     * @return nome del prodotto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Questo metodo restituisce la quantit&agrave; del prodotto.
     * @return la quantit&agrave; del prodotto.
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * Questo metodo restituisce la categoria del prodotto.
     * @return la categoria del prodotto.
     */
    public String getCategoria() {
        return categoria;
    }


    /**
     * Questo metodo restituisce il prezzo del prodotto.
     * @return il prezzo del prodotto.
     */
    public float getPrezzo(){
        return prezzo;
    }


    /**
     * Questo metodo permette di modificare la descrizione di questo prodotto.
     * @param descrizione la nuova descrizione.
     */
    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }

    /**
     * Questo metodo restituisce la descrizione di questo prodotto.
     * @return la descrizione di questo prodotto.
     */
    public String getDescrizione(){
        return this.descrizione;
    }

    /**
     * Questo metodo restituisce la promozione di questo prodotto.
     * @return la promozione del prodotto. Se non &egrave; attiva o se non c'&egrave; restituisce null.
     */
    public Promozioni getPromozione() {
        if(this.promozione!=null && this.promozione.isStato())return promozione;
        else return null;
    }

    public void setPromozione(int sconto, int IDPromozione, Prodotto prodotto){
        //TODO controllo valori null
        this.promozione = new Promozioni(sconto,IDPromozione,prodotto);
    }

    public int getIDNegozio() {
        return IDNegozio;
    }
}
